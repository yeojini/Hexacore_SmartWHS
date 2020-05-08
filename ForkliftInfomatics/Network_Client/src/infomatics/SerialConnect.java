package infomatics;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import msg.Msg;

public class SerialConnect implements SerialPortEventListener {

	CommPortIdentifier commPortIdentifier;
	CommPort commPort;
	InputStream in;
	BufferedInputStream bin;
	static OutputStream out;
	static String receiveStr;

	String receiveId;
	String receiveData;
	Msg msg;
	int status = 1;
	int battery = 999;
	int locX = 11;
	int locY = 13;
	int temperature = 50;
	static String tmpId=" ";
	String sendId="10000001";

	boolean flag = false;

	Sender r = new Sender(msg);

	public SerialConnect() {
	}

	public SerialConnect(String portName) throws Exception {
		commPortIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		System.out.println("Identified Com Port!");
		connect();
		System.out.println("Connect Com Port!");
		Thread SerialStart = new Thread(new SerialWrite());
		SerialStart.start();
		System.out.println("Start CAN Network!!!");
		SerialStart.join();
		Runnable r = new SerialWrite();
		Main.executorService.submit(r);

	}

	public void connect() throws Exception {
		if (commPortIdentifier.isCurrentlyOwned()) {
			System.out.println("Port is currently in use....");
		} else {
			commPort = commPortIdentifier.open(this.getClass().getName(), 5000);
			if (commPort instanceof SerialPort) {
				SerialPort serialPort = (SerialPort) commPort;
				serialPort.addEventListener(this);
				serialPort.notifyOnDataAvailable(true);
				serialPort.setSerialPortParams(921600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
						SerialPort.PARITY_NONE);
				in = serialPort.getInputStream();
				bin = new BufferedInputStream(in);
				out = serialPort.getOutputStream();
			} else {
				System.out.println("this port is not serial ");
			}

		}

	}

	@Override
	public synchronized void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
		case SerialPortEvent.BI:
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE:
			byte[] readBuffer = new byte[30];
			try {
				while (bin.available() > 0) {
					int numBytes = bin.read(readBuffer);
				}

				receiveStr = new String(readBuffer);
				System.out.println("Receive Data:" + receiveStr);

				if (receiveStr.substring(1, 4).equals("U28")) {

					receiveId = receiveStr.substring(4, 12);
					receiveData = receiveStr.substring(24, 28);
					
					if (receiveId.equals("13000003")) { // battery
						battery = Integer.parseInt(receiveData);
						System.out.println("battery : " + battery);

					} else if (receiveId.equals("14000004")) { // location
						locX = Integer.parseInt(receiveData.substring(0, 2));
						locY = Integer.parseInt(receiveData.substring(2, 4));
						System.out.println("Location (x,y) : " + locX + "," + locY);

					} else if (receiveId.equals("15000005")) { // temperature
						temperature = Integer.parseInt(receiveData);
						System.out.println("temperature : " + temperature);
					}

					//
					if (locX == 11 && locY == 13 && battery <= 990) {
						sendId = "10000002";
						System.out.println("Charging");
					}

					if (battery == 999 && locX == 11 && locY == 13 || receiveId.equals("14000005")) {
						sendId = "10000001";
						System.out.println("Waiting");
					}
											
//					if(!tmpId.equals(sendId)) {
						Runnable r = new SerialWrite(sendId,"0000000000000000");
						Main.executorService.execute(r);
//					}
					
					tmpId = sendId;

					if (sendId.equals("10000000")) { // working
						status = 0;
					}

					else if (sendId.equals("10000001")) { // waiting
						status = 1;
						Receiver.task = null;
					}

					else if (sendId.equals("10000002")) { // charging
						status = 2;
						Receiver.task = null;
					}
				}

				msg = new Msg("forklift1", "tabletServer");
				msg.setForkLift(status, locX, locY, battery, temperature, 0);

				if (Receiver.task != null) {
					msg.setTask(Receiver.task.getIo(), Receiver.task.getName(), Receiver.task.getQty(),
							Receiver.task.getLocX(), Receiver.task.getLocY());
				}

				r.setMsg(msg);
				Main.executorService.submit(r);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}// serialEvent method

}
