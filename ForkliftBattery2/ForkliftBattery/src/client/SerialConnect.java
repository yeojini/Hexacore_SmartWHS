package client;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SerialConnect implements SerialPortEventListener{
	
	CommPortIdentifier commPortIdentifier;
	CommPort commPort;
	InputStream in;
	BufferedInputStream bin;
	static String receiveStr;
	static String receiveId;
	static String receiveData;
	static int d = -1;
	static OutputStream out;
	static boolean error = false;
	
	public SerialConnect() {
	}

	public SerialConnect(String portName) throws Exception {
		commPortIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		System.out.println("Identified Com Port!");
		connect();
		System.out.println("Connect Com Port!");
		Runnable r = new SerialWrite();
		Main.executorService.submit(r);
		System.out.println("Start CAN Network!!!");
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
				serialPort.setSerialPortParams(921600,
						SerialPort.DATABITS_8,
						SerialPort.STOPBITS_1,
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
	public void serialEvent(SerialPortEvent event) {
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
			byte[] readBuffer = new byte[128];
			try {
				while (bin.available() > 0) {
					int numBytes = bin.read(readBuffer);
				}
				receiveStr = new String(readBuffer);
				if(receiveStr.contains(":G01A8")) Main.start = true;
				
				System.out.println("Recieve Data : "+receiveStr);
				System.out.println(Main.start);
				if(receiveStr.substring(1,4).equals("U28")) {
					receiveId=receiveStr.substring(4,12);
					setBattery(receiveId);
				}

			} catch (Exception e) {
				e.printStackTrace();
				error=true;
			}
			break;
		}
	}
	
	public void setBattery(String receiveId){
		if (receiveId.equals("10000000")) { // working
			d = -2;
			System.out.println("Working");
		} else if (receiveId.equals("10000001")) { // waiting
			d = -1;
			System.out.println("Waiting");
		} else if (receiveId.equals("10000002")) { // charging
			d = 10;
			System.out.println("Charging");
		}
	}
}
