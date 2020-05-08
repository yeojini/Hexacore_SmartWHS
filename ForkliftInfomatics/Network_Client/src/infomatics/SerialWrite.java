package infomatics;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

public class SerialWrite implements Runnable {

	String data;
	String sendId = "10000001";
	String sendData = "0000000000000000";
	String msg = sendId + sendData;

	public SerialWrite() {
		this.data = ":G11A9\r";
	}

	public SerialWrite(String sendId, String sendData) {
		this.data = convertData(sendId+sendData);
	}

	
	public String convertData(String msg) {
		msg = msg.toUpperCase();
		msg = "W28" + msg;
		//W28 00000000 0000000000000000
		char[] c = msg.toCharArray();
		int checkSum = 0;
		for(char ch:c) {
			checkSum+=ch;
		}
		checkSum = (checkSum & 0xFF);
		String result = ":";
		result += msg + 
				Integer.toHexString(checkSum).toUpperCase()+
				"\r";
		System.out.println("Send Data : "+result);
		return result;
	}

	@Override
	public void run() {

		byte[] outData = data.getBytes();
		try {
			
			SerialConnect.out.write(outData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}// run method
