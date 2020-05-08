package network;

import com.pentacore.tabletserver.MainActivity;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.concurrent.ThreadPoolExecutor;

import msg.Msg;

public class SendInTcpip implements Runnable {

	Msg msg;

	public SendInTcpip() {

	}

	public SendInTcpip(Msg msg) {
		this.msg = msg;
	}

	String dstnID;
	String dstnIP;
	@Override
	public void run() {

		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) MainActivity.executorService;
		int poolSize = threadPoolExecutor.getPoolSize();//스레드 풀 사이즈 얻기
		String threadName = Thread.currentThread().getName();//스레드 풀에 있는 해당 스레드 이름 얻기

		//int value = Integer.parseInt("예외");


		dstnID = msg.getDstnID();
		dstnIP = ActiveConnection.idToIp.get(dstnID);
		msg.setDstnIP(dstnIP);
		ObjectOutputStream oos = ActiveConnection.ipToOos.get(dstnIP);

		System.out.println("SendInTcpip [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: "+threadName);
		System.out.println("srcip : "+msg.getSrcIP()+", srcid : "+msg.getSrcID()+", dstnip : "+msg.getDstnIP()
				+", dstnid : "+msg.getDstnID());

		if(oos != null) {
			try {
				oos.writeObject(msg);
				MainActivity.forkLiftMap.get(msg.getDstnID()).setTask(msg.getTask());
				MainActivity.printConsole("지게차"+msg.getDstnID()+"에 Task를 할당했습니다.");
			} catch (IOException e) {
				ActiveConnection.ipToOos.remove(dstnIP);
				ActiveConnection.idToIp.remove(dstnID);
				e.printStackTrace();
			}
		} else {
			try {
				if(oos!=null) oos.close();
			} catch (Exception e){
				e.printStackTrace();
			}
			// oos==null
			ActiveConnection.ipToOos.remove(dstnIP);
			ActiveConnection.idToIp.remove(dstnID);
		}


	}


}