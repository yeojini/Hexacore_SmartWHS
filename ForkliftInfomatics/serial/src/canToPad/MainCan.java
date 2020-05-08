package canToPad;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import msg.Msg;

public class MainCan implements SerialPortEventListener {
	CommPortIdentifier commPortIdentifier;
	CommPort commPort;
	InputStream in;
	BufferedInputStream bin;
	OutputStream out;

	Socket socket;
	String cid;
	Sender sender;
	Thread temp;

	boolean flag = false;

	public MainCan() {	}
	public MainCan(String portName) throws Exception {
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
		System.out.println(event);
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
				System.out.println("Receive Low Data:" + ss + "||");
				ss = ss.substring(27,29 ); 
				System.out.println(ss);
				
				if (flag) {
					try {
						Msg msg = new Msg(cid, ss, null);
						sender.setMsg(msg);
						Thread.sleep(1000);
						new Thread(sender).start();
						System.out.println(msg.getTxt());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}// serialEvent method

	class SerialWrite implements Runnable {
		String data;
		// 모든 데이터는 String
		public SerialWrite() {
			this.data = ":G11A9\r";
		}
		@Override
		public void run() {
			byte[] outData = data.getBytes();
			try {
				out.write(outData);// 이렇게 data를 CAN Network Area에 쏜다.
			} catch (IOException e) {
				e.printStackTrace();
			}
		}// run method
	}// SerialWrite class
		

	public  void connectPad(String address, int port){
		cid = "Device3";
		try {
			socket = new Socket(address, port);

		} catch (Exception e) {
			while (true) {
				System.out.println("Retry..");
				try {
					Thread.sleep(1000);
					socket = new Socket(address, port);
					break;
				} catch (Exception e1) {
					// e1.printStackTrace();
				}
			}
		}
		System.out.println("Connected Server:" + address);
		try {
			sender = new Sender(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Msg msg = new Msg(cid, null, null);
		sender.setMsg(msg);
		new Thread(sender).start();
		try {
			new Receiver(socket).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class Sender implements Runnable {
		OutputStream os;
		ObjectOutputStream oos;
		Msg msg;
		public Sender(Socket socket) throws IOException {
			os = socket.getOutputStream();
			oos = new ObjectOutputStream(os);
		}
		public void setMsg(Msg msg) {
			this.msg = msg;
		}

		@Override
		public void run() {
			if (oos != null) {
				try {
					oos.writeObject(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	
	class Receiver extends Thread {
		InputStream is;
		ObjectInputStream ois;

		public Receiver(Socket socket) throws IOException {
			is = socket.getInputStream();
			ois = new ObjectInputStream(is);
		}

		@Override
		public void run() {
			while (ois != null) {
				Msg msg = null;
				try {
					msg = (Msg) ois.readObject();
					System.out.println(msg.getTxt());
					if (msg.getTxt().equals("1")) {
//						sendData.setFlag(true);
//						if (temp == null) {
//							temp = new Thread(sendData);
//							temp.start();
							
							flag = true;
							System.out.println("새로운 스레드 생성");
//						}

					} else if (msg.getTxt().equals("0")) {
//						sendData.setFlag(false);
//						temp = null;
						flag = false;
					}

				} catch (Exception e) {
					System.out.println("Server Die");
					break;
				}
			}

			try {
				if (ois != null) {
					ois.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		MainCan mc = null;
		try {
			mc = new MainCan("COM12");
			mc.connectPad("70.12.227.232",8888);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// main method
}// SerialConnect class
