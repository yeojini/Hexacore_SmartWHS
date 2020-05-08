package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

import msg.Msg;

public class Sender implements Runnable {

	OutputStream os;
	ObjectOutputStream oos;
	Msg msg;

	public Sender() {

	}

	public Sender(Socket socket) throws IOException {
		os = socket.getOutputStream();
		oos = new ObjectOutputStream(os);
	}

	@Override
	public void run() {

		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Main.executorService;
		int poolSize = threadPoolExecutor.getPoolSize();// ������ Ǯ ������ ���
		String threadName = Thread.currentThread().getName();// ������ Ǯ�� �ִ� �ش� ������ �̸� ���

		System.out.println("Sender [�� ������ ����:" + poolSize + "] �۾� ������ �̸�: " + threadName);

		int num = 1000;
		while (oos != null) {
			try {
				 
				Msg msg = new Msg ("ecu01","ecu01");
				Thread.sleep(1000);
				if(num<300) {
					msg.setForkLift(0, 0, 0,num,0,0); //charging
					num++;
				}
				else {
					msg.setForkLift(0, 0,0, num,0,0); //working
					num--;
				}
				System.out.println("Send : " + num);
				oos.writeObject(msg);
			} catch (Exception e) {

				if (Client.socket != null) {
					try {
						Client.socket.close();
						break;
					} catch (IOException e1) {
						
					}
				}
			}

		}
	}

}
