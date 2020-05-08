package infomatics;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

import msg.Msg;

public class Sender implements Runnable{
	
	 OutputStream os;
     ObjectOutputStream oos;
     Msg msg;
     
	
	public Sender () {
		
	}
	
	public Sender(Socket socket) throws IOException {
		os = socket.getOutputStream();
		oos = new ObjectOutputStream(os);
	}
	
	public void setMsg(Msg msg) {
		this.msg = msg;
	}

	@Override
	public void run() {
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Main.executorService;
		int poolSize = threadPoolExecutor.getPoolSize();//������ Ǯ ������ ���
		String threadName = Thread.currentThread().getName();//������ Ǯ�� �ִ� �ش� ������ �̸� ���
     
		System.out.println("Sender [�� ������ ����:" + poolSize + "] �۾� ������ �̸�: "+threadName);
		boolean flag = true;
		while(flag) {
			if(oos!=null) {			
				try {
					Msg msg = new Msg(Status.forkLiftID, "tabletserver");
					msg.setForkLift(Status.status, Status.currentX, Status.currentY, Status.battery, Status.temparature, 0);
					msg.setTask(Status.task);
					oos.writeObject(msg);
					System.out.println("TabletServer�� Msg����  �� "+"ID:"+Status.forkLiftID+", ����:"+Status.status
							+", ��ġ:("+Status.currentX+","+Status.currentY
							+"), ���͸�:"+Status.battery
							+", �µ�:"+Status.temparature+", �½�ũ:"+Status.task);
				} catch (IOException e) {
					System.out.println("Exception at Sender.java : "+e.getMessage());
					flag = false;
					Main.constructClient();
				}
			}
			waitOneSecond();
		}
		
	}
	
	public synchronized void waitOneSecond() {
		try {
			this.wait(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
