package server;


import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

import msg.Msg;

public class Receiver implements Runnable{
	
	InputStream is;
	ObjectInputStream ois;

	OutputStream os;
	ObjectOutputStream oos;

	Socket socket;
	
	public Receiver() {}
	
	public Receiver(Socket socket) {
		
		this.socket = socket;
		
		try {
			
			is = socket.getInputStream();
			ois = new ObjectInputStream(is);
			os = socket.getOutputStream();
			oos = new ObjectOutputStream(os);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	@Override
	public void run() {
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Main.executorService;
		int poolSize = threadPoolExecutor.getPoolSize();//스레드 풀 사이즈 얻기
		String threadName = Thread.currentThread().getName();//스레드 풀에 있는 해당 스레드 이름 얻기
		
		ActiveConnection.ipToOos.put(socket.getInetAddress().toString(),oos); //while 문 말고 여기서 put, 03/30 by yeojin
       
        
		while(ois!=null) {
			
			Msg msg = null;
			try {
				System.out.println("Receiver [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: "+threadName);
				msg = (Msg) ois.readObject();
				ActiveConnection.idToIp.put(msg.getSrcID(),socket.getInetAddress().toString());
				System.out.println("source ID : "+msg.getSrcID());				
					
				//Web 이 접속했을 경우에만 Pad 로 Send
//				if(!msg.getSrcID().contains("tab")) {
//					Runnable r= new Sender(msg);
//					Main.executorService.submit(r);
//				}
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
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
