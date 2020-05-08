package client;

import java.io.IOException;
import java.net.Socket;

public class Client {

	static Socket socket;
	
	public Client() {
	}

	public Client(String address, int port) {

		try {
			socket = new Socket(address, port);
		} catch (Exception e) {
			while (true) {
				System.out.println("Retry..");
				try {
					Thread.sleep(1000);
					socket = new Socket(address, port);
					break;
				} catch (Exception e1) {
					// e1.printStackTrace();
				}
			}
		}

		System.out.println("Connected ForkLift Infomatics : " + address);

		Runnable r = null;
		Runnable r2 = null;
		try {
			r = new Sender(socket);
			r2 = new Receiver(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.executorService.submit(r);
		Main.executorService.submit(r2);
	}
}
