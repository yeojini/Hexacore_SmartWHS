package client;

import java.io.IOException;

public class SerialWrite implements Runnable {

	String data;

	public SerialWrite() {
		this.data = ":G11A9\r";
	}

	public  SerialWrite(String msg) {
		this.data = convertData(msg);
	}

	public String convertData(String msg) {
		msg = msg.toUpperCase();
		msg = "W28" + msg;
		char[] c = msg.toCharArray();
		int checkSum = 0;
		for (char ch : c) {
			checkSum += ch;
		}
		checkSum = (checkSum & 0xFF);
		String result = ":";
		result += msg + Integer.toHexString(checkSum).toUpperCase() + "\r";
		return result;
	}

	@Override
	public void run() {
		
		System.out.println("Send Data : " + data);
		
		byte[] outData = data.getBytes();
		try {
			SerialConnect.out.write(outData);
		} catch (IOException e) {
			e.printStackTrace();
			try {
				SerialConnect.out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
