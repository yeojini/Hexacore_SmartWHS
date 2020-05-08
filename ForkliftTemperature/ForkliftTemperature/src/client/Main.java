package client;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	static ExecutorService executorService = Executors.newFixedThreadPool(5);
	static String id = "15000005";
	static String data = "0000000000000000";
	static String msg = id + data;
	static int temperature = 50;
	static String temperatureStr;
	static boolean flag = false;
	static int flag2 = -1;

	

	public static void main(String[] args) throws Exception {

		String port;
		System.out.println("Port Number : ");
		Scanner sc = new Scanner(System.in);
		port = sc.nextLine();

		SerialClient serialClient = new SerialClient(port);

		while (true) {

			if (temperature < 50) {
				flag2 = 1;
			} else if (temperature > 100)
				flag2 = -1;
			
			temperature += (int) (Math.random() * 2) * flag2;

			if (temperature < 10) {
				temperatureStr = "0" + temperature;
			} else {
				temperatureStr = temperature + "";
			}

			int temperatureStrlen = temperatureStr.length();

			msg = msg.substring(0, msg.length() - temperatureStrlen) + temperatureStr;

			new Thread(new SerialWrite(msg)).start();


			Thread.sleep(1000);
		}

	}

}
