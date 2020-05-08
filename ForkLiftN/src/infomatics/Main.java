package infomatics;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	
	static ExecutorService executorService = Executors.newFixedThreadPool(7);
	static String num;
	static String address = "70.12.226.134";
	static int port = 8888;


	public static void main(String[] args) {
//		num = "3";
		num = args[0];
		
		if(!args[0].equals("1") && !args[0].equals("2") && !args[0].equals("3") && !args[0].equals("4")) {
			System.out.println("1, 2, 3, 4 중 하나의 값을 입력해주세요.");
			return;
		}
		
		// fockLift Initialization
		
		Status.forkLiftID = "forklift"+num;
		Status.defaultX = Status.defaultLocationArray[Integer.parseInt(num)-1][0];
		Status.defaultY = Status.defaultLocationArray[Integer.parseInt(num)-1][1];
		Status.currentX = Status.defaultX;
		Status.currentY = Status.defaultY;
		
		constructClient();
		
		Runnable battery = new Battery();
		Main.executorService.submit(battery);
		
		Runnable temparature = new Temparature();
		Main.executorService.submit(temparature);
	}
	
	public static void constructClient() {
		try {
			Client client = new Client(address,port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
