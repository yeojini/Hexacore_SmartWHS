package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

import msg.Msg;

public class Receiver implements Runnable {

	InputStream is;
	ObjectInputStream ois;

	public Receiver(Socket socket) throws IOException {
		is = socket.getInputStream();
		ois = new ObjectInputStream(is);
	}

	@Override
	public void run() {
		
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Main.executorService;
		int poolSize = threadPoolExecutor.getPoolSize();// 스레드 풀 사이즈 얻기
		String threadName = Thread.currentThread().getName();// 스레드 풀에 있는 해당 스레드 이름 얻기

		System.out.println("Receiver [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: " + threadName);
		
		while (ois != null) {
			Msg msg = null;
			try {
				msg = (Msg) ois.readObject();
				System.out.println("srcip : "+msg.getSrcIP()+", srcid : "+msg.getSrcID()+", dstnip : "+msg.getDstnIP()
				+", dstnid : "+msg.getDstnID()+", content : " + msg.getForkLift().getBattery());
				//Task 가 할당이 되어있으면
				if (msg.getTask()==null) {
					
				} else { //Task 할당이 안되어있으면
					

				}

			} catch (Exception e) {
				System.out.println("Server Die");
				break;
			}
		}

		try {
			if (ois != null) {
				ois.close();
			}
			if (Client.socket != null) {
				Client.socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
