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
		int poolSize = threadPoolExecutor.getPoolSize();// ������ Ǯ ������ ���
		String threadName = Thread.currentThread().getName();// ������ Ǯ�� �ִ� �ش� ������ �̸� ���

		System.out.println("Receiver [�� ������ ����:" + poolSize + "] �۾� ������ �̸�: " + threadName);
		
		while (ois != null) {
			Msg msg = null;
			try {
				msg = (Msg) ois.readObject();
				System.out.println("srcip : "+msg.getSrcIP()+", srcid : "+msg.getSrcID()+", dstnip : "+msg.getDstnIP()
				+", dstnid : "+msg.getDstnID()+", content : " + msg.getForkLift().getBattery());
				//Task �� �Ҵ��� �Ǿ�������
				if (msg.getTask()==null) {
					
				} else { //Task �Ҵ��� �ȵǾ�������
					

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
