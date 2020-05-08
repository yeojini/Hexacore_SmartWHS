package infomatics;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

import msg.Msg;

public class Sender implements Runnable{
	
	 OutputStream os;
     ObjectOutputStream oos;
     Msg msg;
     
	
	public Sender () {
		
	}
	
	public Sender(Socket socket) throws IOException {
		os = socket.getOutputStream();
		oos = new ObjectOutputStream(os);
	}
	
	public void setMsg(Msg msg) {
		this.msg = msg;
	}

	@Override
	public void run() {
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Main.executorService;
		int poolSize = threadPoolExecutor.getPoolSize();//스레드 풀 사이즈 얻기
		String threadName = Thread.currentThread().getName();//스레드 풀에 있는 해당 스레드 이름 얻기
     
		System.out.println("Sender [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: "+threadName);
		boolean flag = true;
		while(flag) {
			if(oos!=null) {			
				try {
					Msg msg = new Msg(Status.forkLiftID, "tabletserver");
					msg.setForkLift(Status.status, Status.currentX, Status.currentY, Status.battery, Status.temparature, 0);
					msg.setTask(Status.task);
					oos.writeObject(msg);
					System.out.println("TabletServer로 Msg전송  → "+"ID:"+Status.forkLiftID+", 상태:"+Status.status
							+", 위치:("+Status.currentX+","+Status.currentY
							+"), 배터리:"+Status.battery
							+", 온도:"+Status.temparature+", 태스크:"+Status.task);
				} catch (IOException e) {
					System.out.println("Exception at Sender.java : "+e.getMessage());
					flag = false;
					Main.constructClient();
				}
			}
			waitOneSecond();
		}
		
	}
	
	public synchronized void waitOneSecond() {
		try {
			this.wait(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
