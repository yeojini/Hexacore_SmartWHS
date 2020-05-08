package client;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	static ExecutorService executorService = Executors.newFixedThreadPool(5);
	static int battery = 999;
	static String id = "13000003";
	static String data = "0000000000000000";
	static String msg = id + data;
	static boolean start = false;

	public static void main(String[] args) throws Exception {

		String port;
		System.out.println("Port Number : ");
		Scanner sc = new Scanner(System.in);
		port = sc.nextLine();

		SerialConnect serialConnect = new SerialConnect(port);

		while (true) {
			
			if(start) {
				
				if (battery >= 999) {
					battery = 999;
				} else if (battery <= 0) {
					battery = 0;
				}

				String batteryStr = battery + "";
				int batteryLen = batteryStr.length();

				msg = msg.substring(0, msg.length() - batteryLen) + batteryStr;

				Runnable r = new SerialWrite(msg);
				executorService.execute(r);
				
				Thread a = new Thread(r);
				a.join();
				
				Thread.sleep(1000);
				
				battery += SerialConnect.d;
				
				if(SerialConnect.error) {
					break;
				}
			}
			
			
		}
	}
}
