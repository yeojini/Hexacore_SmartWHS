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

public class SerialServer implements SerialPortEventListener {
	static String[] devices = { "10000000", "10000001", "10000002", "10000003", "10000004" };
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

	public SerialServer() {
	}

	public SerialServer(String portName) throws Exception {
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
			// ³×Æ®¿öÅ©¶û ºñ½Á
			if (commPort instanceof SerialPort) {
				SerialPort serialPort = (SerialPort) commPort;
				serialPort.addEventListener(this);
				serialPort.notifyOnDataAvailable(true);
				serialPort.setSerialPortParams(921600, // Åë½Å¼Óµµ
						SerialPort.DATABITS_8, // µ¥ÀÌÅÍ ºñÆ®
						SerialPort.STOPBITS_1, // stop ºñÆ®
						SerialPort.PARITY_NONE); // ÆÐ¸®Æ¼
				in = serialPort.getInputStream();
				bin = new BufferedInputStream(in);
				out = serialPort.getOutputStream();
			} else {
				System.out.println("this port is not serial ");
			} // serial¿¡¼ ¸¸ ¿¬°áÀ» À§ÇØ¼
				// serialÀÌ ¿Ü¿¡µµ ´Ù¸¥ ¿¬°á ¹æ½ÄÀÌ Á¸ÀçÇÏ±â ¶§¹®¿¡
		} // commPortIdentifier°¡ »ç¿ëÇÒ ¼ö ÀÖ´ÂÁö ÇÏµå¿þ¾î´Â µ¿½Ã¿¡ Á¦¾î°¡ µÇÁö ¾Ê´À´Ù.
			// Á¡À¯°¡ µÇ¾îÀÖ´Â »óÅÂÀÎÁö¸¦ È®ÀÎ
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
			byte[] readBuffer = new byte[30];
			try {
				while (bin.available() > 0) {
					int numBytes = bin.read(readBuffer);
				}

				String ss = new String(readBuffer);
				System.out.println("Receive Low Data:" + ss + "||");
//				if (flag) {
				try {

					System.out.println(ss);
					System.out.println(ss.substring(28, 30));
					if (!ss.substring(0, 6).equals(":G01A8") && !ss.substring(0, 6).equals(":W2810")
							&& !ss.substring(0, 5).equals("W2810")) {
						String data = ss.substring(26, 28);
						if (!data.equals("00")) {
							System.out.println("data : " + data);
							cid = setCID(ss.substring(4, 12));
							Msg msg = new Msg(cid, data, null);
							sender.setMsg(msg);
							Thread.sleep(1000);
							new Thread(sender).start();
							System.out.println("txt " + msg.getTxt());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
//				}
//				}
			} catch (Exception e) {
				e.printStackTrace();
			}
//			break;
		}
	}// serialEvent method

	public String setCID(String id) {
		String cid = null;
		if (id.equals(devices[1])) {
			cid = "Device1";
		} else if (id.equals(devices[2])) {
			cid = "Device2";
		} else if (id.equals(devices[3])) {
			cid = "Device3";
		} else if (id.equals(devices[4])) {
			cid = "Device4";
		}else {
			cid = "No Device";
		}
		return cid;
	}

	public void send(String msg) {
		new Thread(new SerialWrite(msg)).start();
	}// send method

	class SerialWrite implements Runnable {
		String data;

		public SerialWrite() {
			this.data = ":G11A9\r";
		}

		public SerialWrite(String msg) {
			this.data = convertData(msg);
		}

		public String convertData(String msg) {
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
			System.out.println(result);
			return result;
		}

		@Override
		public void run() {
			byte[] outData = data.getBytes();
			try {
				out.write(outData);// ÀÌ·¸°Ô data¸¦ CAN Network Area¿¡ ½ð´Ù.
			} catch (IOException e) {
				e.printStackTrace();
			}
		}// run method
	}// SerialWrite class

	public void connectPad(String address, int port) {
		cid = "IoT";
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

		public String setID(String tid) {
			String id = "10000009";
			System.out.println("setID start, tid : " + tid);
			if (tid.equals("null.") || tid == null || tid.equals(".")) {
				id = devices[0];
			} else if (tid.equals("Device1.")) {
				id = devices[1];
			} else if (tid.equals("Device2.")) {
				id = devices[2];
			} else if (tid.equals("Device3.")) {
				id = devices[3];
			} else if (tid.equals("Device4.")) {
				id = devices[4];
			}
			System.out.println("setID end, ID : " + id);
			return id;
		}

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
					System.out.println("tid : " + msg.getTid() + " txt : " + msg.getTxt());
					if (msg.getTxt().equals("1")) {
//							flag = true;
						String tid = msg.getTid() + ".";
						System.out.println("tid : " + tid);
						String id = setID(tid);
//							String id  = "10000000";
						String data = "0000000000000000";
						System.out.println(id + data);
						try {
							send(id + data);
						} catch (Exception e) {
							e.printStackTrace();
						}

					} else if (msg.getTxt().equals("0")) {
						flag = false;
						String tid = msg.getTid() + ".";
						String id = setID(tid);
						// String id = "10000003";
						String data = "1000000000000000";
						System.out.println(id + data);
						try {
							send(id + data);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				} catch (Exception e) {
					System.out.println("Server Die");
					e.printStackTrace();
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
		SerialServer sc = null;
		try {
			sc = new SerialServer("COM12");
			sc.connectPad("70.12.226.134", 8888);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// main method
}// SerialConnect class