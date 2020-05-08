package canToCan;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SerialClient implements SerialPortEventListener {
	CommPortIdentifier commPortIdentifier;
	CommPort commPort;
	InputStream in;
	BufferedInputStream bin;
	OutputStream out;
	TimerTask task;
	Timer timer;
	boolean flag = false;

	public SerialClient() {
	}

	public SerialClient(String portName) throws Exception {
		commPortIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		System.out.println("Identified Com Port!");
		connect();
		System.out.println("Connect Com Port!");
		new Thread(new SerialWrite()).start();
		System.out.println("Start CAN Network!!!");
	}// SerialConnect constructor

	public void connect() throws Exception {
		if (commPortIdentifier.isCurrentlyOwned()) {
			System.out.println("Port is currently in use....");
		} else {
			commPort = commPortIdentifier.open(this.getClass().getName(), 5000);
			// 네트워크랑 비슷
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
			} // serial에서만 연결을 위해서
				// serial이 외에도 다른 연결 방식이 존재하기 때문에
		} // commPortIdentifier가 사용할 수 있는지 하드웨어는 동시에 제어가 되지 않느다.
			// 점유가 되어있는 상태인지를 확인
	}// connect method

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
				String ss = new String(readBuffer);
				System.out.println("**************Receive Low Data:" + ss + "||");
				if (!ss.substring(0, 6).equals(":G01A8") && !ss.substring(0, 6).equals(":W2810")) { // ':G01A8' -> 처음
																									// 연결될 때 받는 메시지 (데이터
																									// 값 아님)
					String controller = ss.substring(12, 13);
					System.out.println("controller : " + controller);
					if (ss.substring(4, 12).equals("10000000") || ss.substring(4, 12).equals("10000002")) { // 전체 알림이거나
																											// 메시지일 때만
						if (controller.equals("1")) {
							flag = false;
							System.out.println("Stop Client...");
							timer.cancel();
						} else if (controller.equals("0")) {
							flag = true;
							String id = "10000002";
							String data = "30000000000000";
							String msg = id + data;
							System.out.println("Restart Client...");
							try {
								task = new TimerTask() {
									@Override
									public void run() {
										new Thread(new SerialWrite(msg)).start();
									}
								};
								timer = new Timer();
								timer.scheduleAtFixedRate(task, 1234, 3232);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}// serialEvent method
		// serial통신은 이벤트를 통해서 주고 받는 구나!
// public void send(String msg) {
//
//
//   try {
//
//    System.out.println("flag : " + flag);
//    new Thread(new SerialWrite(msg)).start();
//    Thread.sleep(5000);
//
//   } catch (Exception e) {
//    e.printStackTrace();
//   }
//
//
// }// send method

	class SerialWrite implements Runnable {
		String data;

		// 모든 데이터는 String
		public SerialWrite() {
			this.data = ":G11A9\r";
		}

		public SerialWrite(String msg) {
			this.data = convertData(msg);
		}

		public String convertData(String msg) {
			int num = (int) (Math.random() * 100);
			if (num < 10) {
				msg += ("0" + num);
			} else {
				msg += num;
			}
			msg = msg.toUpperCase();
			msg = "W28" + msg;
			// W28 00000000 0000000000000000
			char[] c = msg.toCharArray();
			int checkSum = 0;
			for (char ch : c) {
				checkSum += ch;
			}
			checkSum = (checkSum & 0xFF);
			String result = ":";
			result += msg + Integer.toHexString(checkSum).toUpperCase() + "\r";
			System.out.println("result : " + result);
			return result;
		}

		@Override
		public void run() {
			byte[] outData = data.getBytes();
			try {
				System.out.println(outData);
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				out.write(outData);// 이렇게 data를 CAN Network Area에 쏜다.
			} catch (IOException e) {
				e.printStackTrace();
			}
		}// run method
	}// SerialWrite class
		// 전송하는 것을 해보는 중
		// CAN Network에 참여하는 것을 알려주는 것

	public static void main(String[] args) {
		SerialClient sc = null;
		String id = "10000002";
		String data = "30000000000000";
		String msg = id + data;
		try {
			sc = new SerialClient("COM13");
//   sc.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// main method`
}// SerialConnect class