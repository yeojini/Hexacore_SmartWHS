package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

public class Server implements Runnable {

	ServerSocket serverSocket;
	boolean SERVER_RUNNING = true;
	
	public Server() {
		
	}
	
	public Server(int port) {
		
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("TCP/IP Server Start");
		
	}

	@Override
	public void run() {
		while (SERVER_RUNNING) {
            
			Socket socket = null;
			try {
				
				socket = serverSocket.accept();
				System.out.println("new Client Accepted : "+socket.getInetAddress());
				
				Runnable r = new Receiver(socket);
				Main.executorService.execute(r);
				
				
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
	}
	
}


