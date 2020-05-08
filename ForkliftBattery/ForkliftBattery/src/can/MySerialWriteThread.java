package can;

public class MySerialWriteThread implements Runnable {

	boolean stopped;
	boolean suspended;
	String id = "13000003";
	String data = "0000000000000000";
	String msg = id + data;
	int battery = 1000;
	String batteryStr;
	int batteryLen;
	
	public MySerialWriteThread() {

	}

	public void stop() {
		stopped = true;
	}

	public void suspend() {
		suspended = true;
	}

	@Override
	public void run() {

		while (!stopped) {
			if (!suspended) {

				battery += SerialConnect.D.getD();

				if (battery >= 999) {
					battery = 999;
				} else if (battery <= 0) {
					battery = 0;
				}

				batteryStr = battery + "";
				batteryLen = batteryStr.length();

				msg = msg.substring(0, msg.length() - batteryLen) + batteryStr;

			
				Runnable r = new SerialWrite(msg);
				//Main.executorService.execute(r);
				Thread th = new Thread(r);
				th.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				
				if (SerialConnect.error) {
					break;
				}
			}
		}

	}

}
