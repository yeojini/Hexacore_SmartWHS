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
		int poolSize = threadPoolExecutor.getPoolSize();//������ Ǯ ������ ���
		String threadName = Thread.currentThread().getName();//������ Ǯ�� �ִ� �ش� ������ �̸� ���
		
		ActiveConnection.ipToOos.put(socket.getInetAddress().toString(),oos); //while �� ���� ���⼭ put, 03/30 by yeojin
       
        
		while(ois!=null) {
			
			Msg msg = null;
			try {
				System.out.println("Receiver [�� ������ ����:" + poolSize + "] �۾� ������ �̸�: "+threadName);
				msg = (Msg) ois.readObject();
				ActiveConnection.idToIp.put(msg.getSrcID(),socket.getInetAddress().toString());
				System.out.println("source ID : "+msg.getSrcID());				
					
				//Web �� �������� ��쿡�� Pad �� Send
//				if(!msg.getSrcID().contains("tab")) {
//					Runnable r= new Sender(msg);
//					Main.executorService.submit(r);
//				}
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
				//ActiveConnection.executorService.shutdown();
				ActiveConnection.ipToOos.remove(socket.getInetAddress().toString());
					
				//value ������ key �� ã��
				for(String id : ActiveConnection.idToIp.keySet()) {
					if(socket.getInetAddress().toString().equals(ActiveConnection.idToIp.get(id))) {
						ActiveConnection.idToIp.remove(id);
					}		
				}
				System.out.println("Disconnected : " + socket.getInetAddress().toString());
				System.out.println("���� �� : " + ActiveConnection.ipToOos.size());
				
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
