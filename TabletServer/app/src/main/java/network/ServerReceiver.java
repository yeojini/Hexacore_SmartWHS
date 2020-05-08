package network;

import com.pentacore.tabletserver.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

import msg.ForkLift;
import msg.Msg;

public class ServerReceiver implements Runnable {

    InputStream is;
    ObjectInputStream ois;

    OutputStream os;
    ObjectOutputStream oos;

    Socket socket;

    int WORKING = 0;
    int WAITING = 1;
    int CHARGING = 2;

    public ServerReceiver() {

    }

    public ServerReceiver(Socket socket) {
        MainActivity.printConsole("Client("+socket.getInetAddress().toString()+")가 연결되었습니다.");
        System.out.println("ServerReceiver 객체 생성");
        this.socket = socket;

        try {
            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            is = socket.getInputStream();
            ois = new ObjectInputStream(is);
            ActiveConnection.ipToOos.put(socket.getInetAddress().toString(), oos);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) MainActivity.executorService;
        int poolSize = threadPoolExecutor.getPoolSize();//스레드 풀 사이즈 얻기
        String threadName = Thread.currentThread().getName();//스레드 풀에 있는 해당 스레드 이름 얻기
        System.out.println("ServerReceiver [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: " + threadName);

        Msg msg = null;
        while(ois!=null) {
            try {
                msg = (Msg) ois.readObject();
                ActiveConnection.idToIp.put(msg.getSrcID(), socket.getInetAddress().toString());

                msg.ForkLift forkLiftFromMsg = msg.getForkLift();
                msg.Task taskFromMsg = msg.getTask();
                StringBuilder sb = new StringBuilder();
                if(taskFromMsg != null) {
                    if(taskFromMsg.getIo()==0) sb.append("[출고] ");
                    else if(taskFromMsg.getIo()==1) sb.append("[입고] ");
                    sb.append(taskFromMsg.getName()).append(" ")
                            .append(taskFromMsg.getQty()).append("개 ")
                            .append("(").append(taskFromMsg.getLocX())
                            .append(", ").append(taskFromMsg.getLocY()).append(")");
                } else {
                    sb.append("-");
                }


//                MainActivity.printConsole(msg.getSrcID()+"로부터 수신, Status:"+forkLiftFromMsg.getStatus()+", Battery"+forkLiftFromMsg.getBattery()+", XY:"+forkLiftFromMsg.getLocX()+","+forkLiftFromMsg.getLocY());

                // HTTP로 msg객체의 내용 전달하기
                String forkLiftID = msg.getSrcID();
                logistics.ForkLift forkLift = MainActivity.forkLiftMap.get(forkLiftID);

                if(forkLiftFromMsg.getStatus()==WAITING && !MainActivity.waitingForkLiftQueue.contains(forkLiftID)) {
                    MainActivity.waitingForkLiftQueue.offer(forkLiftID);
                } else if(forkLiftFromMsg.getStatus()!=WAITING && MainActivity.waitingForkLiftQueue.contains(forkLiftID)) {
                    MainActivity.waitingForkLiftQueue.remove(forkLiftID);
                }

			if(forkLift.getStatus() != forkLiftFromMsg.getStatus()) {
			    String status = "WAITING";
			    if(forkLiftFromMsg.getStatus() == 0) {
			        status = "WORKING";
                } else if (forkLiftFromMsg.getStatus() == 2) {
			        status = "CHARGING";
                }
				MainActivity.printConsole("지게차" + forkLift.getName() + "의 상태가 " + status + "로 변경되었습니다.");

				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.accumulate("forkliftid", msg.getSrcID());
					jsonObject.accumulate("status", forkLiftFromMsg.getStatus());
					jsonObject.accumulate("battery", forkLiftFromMsg.getBattery());
                    jsonObject.accumulate("temparature", forkLiftFromMsg.getTemperature());

                    // To-dos Distance Driven은 default가 0, 있을때는 0아닌 숫자.
                    if(forkLiftFromMsg.getStatus() != 0) {
                        System.out.println("currentTask"+forkLift.getCurrentTask());
                        jsonObject.accumulate("distanceDriven", getDistanceDriven(forkLift.getCurrentTask(), forkLiftFromMsg));
                    }
				} catch (JSONException e) {
					e.printStackTrace();
				}

				Runnable sendInHttp = new SendInHttp(jsonObject);
				MainActivity.executorService.submit(sendInHttp);





			}


            // forklift 상태 정보 업데이트
            forkLift.setCurrentTask(sb.toString());
            forkLift.setTemparature(forkLiftFromMsg.getTemperature());
            forkLift.setBattery(forkLiftFromMsg.getBattery());
            forkLift.setCurrentX(forkLiftFromMsg.getLocX());
            forkLift.setCurrentY(forkLiftFromMsg.getLocY());
            forkLift.setStatus(forkLiftFromMsg.getStatus());

            // 바뀐 상태 정보 UI에 반영
            MainActivity.updateForkLiftUI(msg.getSrcID());


            } catch (Exception e) {
//			} catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
                ActiveConnection.ipToOos.remove(socket.getInetAddress().toString());

                //value 값으로 key 값 찾기
                for (String id : ActiveConnection.idToIp.keySet()) {
                    if (socket.getInetAddress().toString().equals(ActiveConnection.idToIp.get(id))) {
                        ActiveConnection.idToIp.remove(id);
                    }
                }

                MainActivity.printConsole("Disconnected : " + socket.getInetAddress().toString());
                MainActivity.printConsole("접속 수 : " + ActiveConnection.ipToOos.size());
                break;
            }
        }

        try {
            if (ois != null) {
                ois.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getDistanceDriven(String targetTask, msg.ForkLift forkLiftFromMsg) {
        System.out.println("---targetTask"+targetTask);
        if(forkLiftFromMsg.getStatus()==0) return 0;
        if(!targetTask.contains("(")) return 0;
        int a = targetTask.indexOf('(');
        int b = targetTask.indexOf(')');
        String task = targetTask.substring(a+1, b);
        String[] xy = task.split(",");
        if(xy[0].startsWith(" ")) xy[0] = xy[0].substring(1);
        if(xy[1].startsWith(" ")) xy[1] = xy[1].substring(1);
        int stockX = Integer.parseInt(xy[0]);
        int stockY = Integer.parseInt(xy[1]);

        int forkLiftX = forkLiftFromMsg.getLocX();
        int forkLiftY = forkLiftFromMsg.getLocY();

        if(stockX % 4 == 2) {
            if(stockX<forkLiftX) return (forkLiftX-stockX)*2+(forkLiftY-stockY)*2+4;
            else return (stockX-forkLiftX)*2+(forkLiftY-stockY)*2-2;
        } else if (stockX % 4 == 3) {
            if(stockX<forkLiftX) return (forkLiftX-stockX)*2+(forkLiftY-stockY)*2-2;
            else return (stockX-forkLiftX)*2+(forkLiftY-stockY)*2+4;
        }
        return 0;
    }

}