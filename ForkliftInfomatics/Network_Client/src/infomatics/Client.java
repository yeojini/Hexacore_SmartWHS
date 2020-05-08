package infomatics;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Client{

	static Socket socket;
	static OutputStream os;
	static ObjectOutputStream oos;
	
	public Client() {
	}

	public Client(String address, int port) throws IOException {
		
		try {
			socket = new Socket(address, port);
		} catch (Exception e) {
			while (true) {
				System.out.println("Retry to Connect Tablet Server");
				try {
					Thread.sleep(1000);
					socket = new Socket(address, port);
					break;
				} catch (Exception e1) {
					// e1.printStackTrace();
				}
			}
		}

		System.out.println("Connected Tablet Server : " + address);
		os = socket.getOutputStream();
		oos = new ObjectOutputStream(os);
	
		Runnable r = new Receiver(socket);
		Main.executorService.submit(r);
	
	}
}


