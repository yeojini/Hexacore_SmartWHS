package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.concurrent.ThreadPoolExecutor;

import msg.Msg;

public class Sender implements Runnable {

	OutputStream os;
	ObjectOutputStream oos;
	Msg msg;

	public Sender() {

	}

	public Sender(Msg msg) {
		this.msg = msg;
	}

	@Override
	public void run() {

		System.out.println("Send Task (IO,x,y,qty): " + msg.getTask().getIo() + "," + msg.getTask().getLocX() + ","
				+ msg.getTask().getLocY() + "," + msg.getTask().getQty());

		String dstn = null;

		dstn = ActiveConnection.idToIp.get("tabletServer");
		System.out.println(dstn);

		try {
			ActiveConnection.ipToOos.get(dstn).writeObject(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
