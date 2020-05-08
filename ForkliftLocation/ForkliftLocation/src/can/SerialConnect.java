package can;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SerialConnect implements SerialPortEventListener {

	CommPortIdentifier commPortIdentifier;
	CommPort commPort;
	InputStream in;
	BufferedInputStream bin;
	String receiveStr;
	int stockLocX;
	int stockLocY;
	static OutputStream out;
	Runnable rSerialWrite;
	MySerialWriteThread r;

	public SerialConnect() {
	}

	public SerialConnect(String portName) throws Exception {
		commPortIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		System.out.println("Identified Com Port!");
		connect();
		System.out.println("Connect Com Port!");
		Thread SerialStart = new Thread(new SerialWrite());
		SerialStart.start();
		System.out.println("SerialWrite Thread Run");
		SerialStart.join();
		System.out.println("Start CAN Network!!!");
		
		r = new MySerialWriteThread();
		Thread th = new Thread(r);
		th.start();
		
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
				serialPort.setSerialPortParams(921600, // 통신속도
						SerialPort.DATABITS_8, // 데이터 비트
						SerialPort.STOPBITS_1, // stop 비트
						SerialPort.PARITY_NONE); // 패리티
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
		
				if (receiveStr.substring(1, 4).equals("U28")) {
				
					if(receiveStr.substring(4,12).equals("10000000")||
							receiveStr.substring(4,12).equals("10000001")||
							receiveStr.substring(4,12).equals("10000002")
							) System.out.println("Recieve Data : " + receiveStr);

					if (receiveStr.substring(4, 12).equals("10000000")) {
						stockLocX = Integer.parseInt(receiveStr.substring(24, 26));
						stockLocY = Integer.parseInt(receiveStr.substring(26, 28));
						System.out.println("stock LocX : "+stockLocX+", stock LocY : "+stockLocY);
						r.setStock(stockLocX,stockLocY);
						
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}// serialEvent method

}
