package can;

import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) throws Exception {

		Thread makeConnectionThread = new Thread(new makeConnection());
		makeConnectionThread.start();
		
	}
}

class makeConnection implements Runnable

{
	String port;
	SerialConnect serialConnect;

	@Override
	public void run() {
		try {
			System.out.println("Port Number : ");
			Scanner sc = new Scanner(System.in);
			port = sc.nextLine();
			serialConnect = new SerialConnect(port);
		} catch (Exception e) {
			while (true) {
				try {
					System.out.println("Retry to Connect Port..");
					System.out.println("Port Number : ");
					Scanner sc = new Scanner(System.in);
					port = sc.nextLine();
					serialConnect = new SerialConnect(port);
					break;
				} catch (Exception e1) {

				}
			}
		}
	}
}
