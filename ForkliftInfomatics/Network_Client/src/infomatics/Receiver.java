package infomatics;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

import msg.Msg;
import msg.Task;

class Receiver implements Runnable {

	InputStream is;
	ObjectInputStream ois;

	OutputStream os;
	ObjectOutputStream oos;

	Socket socket;
	static int status = 2; // waiting
	static Task task;
	String loc;
	String sendData;

	public Receiver() {
	}

	public Receiver(Socket socket) throws IOException {
		this.socket = socket;
		is = socket.getInputStream();
		ois = new ObjectInputStream(is);
	}

	@Override
	public void run() {

		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Main.executorService;
		int poolSize = threadPoolExecutor.getPoolSize();// ������ Ǯ ������ ���
		String threadName = Thread.currentThread().getName();// ������ Ǯ�� �ִ� �ش� ������ �̸� ���

		while (ois != null) {
			Msg msg = null;
			try {

				System.out.println("Receiver [�� ������ ����:" + poolSize + "] �۾� ������ �̸�: " + threadName);

				msg = (Msg) ois.readObject();

				if (msg != null) {
					System.out.println("Received Task - " + "srcid: " + msg.getSrcID() + " dstnid : " + msg.getDstnID()
							+ " IO : " + msg.getTask().getIo() + " LocX : " + msg.getTask().getLocX() + " LocY : "
							+ msg.getTask().getLocY() + " itemName : " + msg.getTask().getName() + " Qty : "
							+ msg.getTask().getQty());

					if (msg.getTask().getLocX() < 10) {
						loc = "0" + msg.getTask().getLocX();
					} else {
						loc = msg.getTask().getLocX() + "";
					}

					if (msg.getTask().getLocY() < 10) {
						loc += "0" + msg.getTask().getLocY();
					} else {
						loc += msg.getTask().getLocX() + "";
					}
					
					SerialConnect.tmpId = "10000000";

					sendData = sendData.substring(0,sendData.length()-loc.length())+loc;
					Runnable r = new SerialWrite("10000000",sendData);
					Main.executorService.submit(r);
					
					
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Server Die");
				ActiveConnection.ipToOos.remove(socket.getInetAddress().toString());
				// value ������ key �� ã��
				for (String id : ActiveConnection.idToIp.keySet()) {
					if (socket.getInetAddress().toString().equals(ActiveConnection.idToIp.get(id))) {
						ActiveConnection.idToIp.remove(id);
					}
				}
				System.out.println("Disconnected : " + socket.getInetAddress().toString());
				System.out.println("���� �� : " + ActiveConnection.ipToOos.size());
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
