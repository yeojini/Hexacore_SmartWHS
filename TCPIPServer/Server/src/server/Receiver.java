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
	
	public Receiver() {}
	
	public Receiver(Socket socket) {
		
		this.socket = socket;
		
		try {
			
			is = socket.getInputStream();
			ois = new ObjectInputStream(is);
			os = socket.getOutputStream();
			oos = new ObjectOutputStream(os);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void run() {
		
		ActiveConnection.ipToOos.put(socket.getInetAddress().toString(),oos);
       
		while(ois!=null) {
			
			Msg msg = null;
			try {
				System.out.println("Receiver [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: "+threadName);
				msg = (Msg) ois.readObject();
				
				ActiveConnection.idToIp.put(msg.getSrcID(),socket.getInetAddress().toString());
				
				if(msg.getTask()!=null) {
					System.out.println("Receive Task (IO,name,qty,x,y): " + msg.getTask().getIo() + ","+msg.getTask().getName()+","+msg.getTask().getQty()+","+msg.getTask().getLocX()+","+msg.getTask().getLocY());
					Runnable r= new Sender(msg);
					Main.executorService.submit(r);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				ActiveConnection.executorService.shutdown();
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

}
