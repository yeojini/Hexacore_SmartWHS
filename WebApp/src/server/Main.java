package server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	
	public static ExecutorService executorService = Executors.newFixedThreadPool(5);

	public static void main(String[] args) throws Exception {
		
//		int port = 8888; //port
		
//		Runnable r = new Server (port);
//		executorService.submit(r);
//		//ECU 의 TCP/IP Server
//		//시리얼통신으로 받아오면 필요없음
	
		String address = "70.12.113.200";
		Client client = new Client(address,9999);
		//Tablet Server 의 Client

	}
}
