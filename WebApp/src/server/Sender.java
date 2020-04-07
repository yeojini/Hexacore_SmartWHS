package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.concurrent.ThreadPoolExecutor;
import msg.Msg;

public class Sender implements Runnable{

	/*
	 * Msg 객체의 srcID = web으로 통일!
	 */
	
	OutputStream os;
    ObjectOutputStream oos;
    Msg msg;
    
    
    public Sender () {
		
	}
	
	public Sender(Msg msg) {
		this.msg = msg;
	}
	
	@Override
	public void run() {
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Main.executorService;
		int poolSize = threadPoolExecutor.getPoolSize();//스레드 풀 사이즈 얻기
		String threadName = Thread.currentThread().getName();//스레드 풀에 있는 해당 스레드 이름 얻기
	
		System.out.println("Sender [총 스레드 개수:" + poolSize + "] 작업 스레드 이름: "+threadName);
          
		
		System.out.println("srcip : "+msg.getSrcIP()+", srcid : "+msg.getSrcID()+", dstnip : "+msg.getDstnIP()
		+", dstnid : "+msg.getDstnID()+", content : " + msg.getForkLift().getBattery());
		
		if(ActiveConnection.idToIp.containsKey(msg.getDstnID())) {
			String ip = ActiveConnection.idToIp.get(msg.getDstnID());	
			try {
				ActiveConnection.ipToOos.get(ip).writeObject(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}

}
