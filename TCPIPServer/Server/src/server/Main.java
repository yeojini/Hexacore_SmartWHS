package server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
	
	
	static ExecutorService executorService = Executors.newFixedThreadPool(5);

	public static void main(String[] args) {
		
		int port=9999; //port ÇÊ¿ä
		
		Runnable r = new Server(9999);
		executorService.submit(r);
	}

}
