package serial;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
	OutputStream out;

	public SerialConnect() {}
	public SerialConnect(String portName) throws Exception {
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
			// ��Ʈ��ũ�� ���
			if (commPort instanceof SerialPort) {
				SerialPort serialPort = (SerialPort) commPort;
				serialPort.addEventListener(this);
				serialPort.notifyOnDataAvailable(true);
				serialPort.setSerialPortParams(921600, // ��żӵ�
						SerialPort.DATABITS_8, // ������ ��Ʈ
						SerialPort.STOPBITS_1, // stop ��Ʈ
						SerialPort.PARITY_NONE); // �и�Ƽ
				in = serialPort.getInputStream();
				bin = new BufferedInputStream(in);
				out = serialPort.getOutputStream();
			} else {
				System.out.println("this port is not serial ");
			} // serial������ ������ ���ؼ�
				// serial�� �ܿ��� �ٸ� ���� ����� �����ϱ� ������
		} // commPortIdentifier�� ����� �� �ִ��� �ϵ����� ���ÿ� ��� ���� �ʴ���.
			// ������ �Ǿ��ִ� ���������� Ȯ��

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
		    System.out.println("Receive Low Data:" + ss + "||");
		    
		   } catch (Exception e) {
		    e.printStackTrace();
		   }
		   break;
		  }
	}//serialEvent method
	//serial����� �̺�Ʈ�� ���ؼ� �ְ� �޴� ����!
	
	
	class SerialWrite implements Runnable{
		String data;
		//��� �����ʹ� String
		
		public SerialWrite(){
			this.data = ":G11A9\r";
		}
		
		@Override
		public void run() {
			byte[] outData = data.getBytes();
			try {
				out.write(outData);//�̷��� data�� CAN Network Area�� ���.
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//run method
		
	}//SerialWrite class 
	//�����ϴ� ���� �غ��� ��
	//CAN Network�� �����ϴ� ���� �˷��ִ� ��
	
	
	
	public static void main(String[] args) {
		SerialConnect sc = null;
		try {
			sc = new SerialConnect("COM11");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// main method
}// SerialConnect class
