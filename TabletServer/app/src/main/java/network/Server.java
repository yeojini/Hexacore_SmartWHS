package network;

import com.pentacore.tabletserver.MainActivity;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

public class Server implements Runnable {

	public ServerSocket serverSocket;
	public Socket socket;
	public int port;
	boolean SERVER_RUNNING = true;

	public Server() {

	}

	public Server(int port) {
		this.port = port;



	}

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Server Start (Server 객체 생성)");

		while (SERVER_RUNNING) {


			try {
				socket = serverSocket.accept();
				System.out.println("new Client Accepted : "+socket.getInetAddress());

				ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) MainActivity.executorService;
				int poolSize = threadPoolExecutor.getPoolSize();//스레드 풀 사이즈 얻기
				String threadName = Thread.currentThread().getName();//스레드 풀에 있는 해당 스레드 이름 얻기

				System.out.println(" Server [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: "+threadName);

				Runnable serverReceiver = new ServerReceiver(socket);
				MainActivity.executorService.submit(serverReceiver);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
