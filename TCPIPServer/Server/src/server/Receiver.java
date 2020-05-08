package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

import msg.Msg;
import msg.Task;

public class Receiver implements Runnable {
	
	InputStream is;
	ObjectInputStream ois;

	OutputStream os;
	ObjectOutputStream oos;

	Socket socket;
	
	//TEST
	Task[] tasks = new Task[4];
	int i=0;
	
	public Receiver() {}
	
	public Receiver(Socket socket) {
		
		this.socket = socket;
		
		try {
			
			is = socket.getInputStream();
			ois = new ObjectInputStream(is);
			os = socket.getOutputStream();
			oos = new ObjectOutputStream(os);
		
		} catch (IOException e) {
			//e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void run() {
		
		//TEST
		tasks[0] = new Task(0,"PC",3,3,5);
		tasks[1] = new Task(1,"TV",5,7,7);
		tasks[2] = new Task(0,"Monitor",2,10,9);
		tasks[3] = new Task(1,"PHONE",6,14,5);
		
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Main.executorService;
		int poolSize = threadPoolExecutor.getPoolSize();//스레드 풀 사이즈 얻기
		String threadName = Thread.currentThread().getName();//스레드 풀에 있는 해당 스레드 이름 얻기
		
		ActiveConnection.ipToOos.put(socket.getInetAddress().toString(),oos);
       
        
		while(ois!=null) {
			
			Msg msg = null;
			try {
				System.out.println("Receiver [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: "+threadName);
				//msg = (Msg) ois.readObject();
				//TEST
//				msg = new Msg("Web","tabletServer");
//				msg.setTask(tasks[i%4].getIo(), tasks[i%4].getName(), tasks[i%4].getQty(), tasks[i%4].getLocX(), tasks[i%4].getLocY());
//				ActiveConnection.idToIp.put("tabletServer","/70.12.226.134");
				
				//
				
				//System.out.println("접속 : "+msg.getSrcID());				
				
				//tabletServer 접속할때는 Sender 할 필요 없다
				
				ActiveConnection.idToIp.put(msg.getSrcID(),socket.getInetAddress().toString());
				
				if(msg.getTask()!=null) {
					System.out.println("Receive Task (IO,name,qty,x,y): " + msg.getTask().getIo() + ","+msg.getTask().getName()+","+msg.getTask().getQty()+","+msg.getTask().getLocX()+","+msg.getTask().getLocY());
					Runnable r= new Sender(msg);
					Main.executorService.submit(r);
				}
				
				//TEST
//				i++;
//				Thread.sleep(10000);
				
				
				
				
			} catch (Exception e) {
				//e.printStackTrace();
				//ActiveConnection.executorService.shutdown();
				ActiveConnection.ipToOos.remove(socket.getInetAddress().toString());
					
				//value 값으로 key 값 찾기
				for(String id : ActiveConnection.idToIp.keySet()) {
					if(socket.getInetAddress().toString().equals(ActiveConnection.idToIp.get(id))) {
						ActiveConnection.idToIp.remove(id);
					}		
				}
				System.out.println("Disconnected : " + socket.getInetAddress().toString());
				System.out.println("접속 수 : " + ActiveConnection.ipToOos.size());
				
				break;
			}//catch	
		}//while
		
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

}
