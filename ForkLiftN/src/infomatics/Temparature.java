package infomatics;

public class Temparature implements Runnable {

	@Override
	public void run() {
		int flag = -1;
		while(true) {
			if(Status.temparature<50) flag = 1;
			else if(Status.temparature>100) flag = -1;
			Status.temparature += (int)(Math.random()*2)*flag;
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
