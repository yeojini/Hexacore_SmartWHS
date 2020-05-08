package infomatics;

public class Battery implements Runnable {
	int status;
	
	public Battery() {
		this.status=1;
	}

	@Override
	public void run() {
		while(true) {
			if(Status.status==0) {
				Status.battery-=2;
			} else if(Status.status==1) {
				if(Status.battery<300) Status.status=2;
				Status.battery-=1;
			} else if(Status.status==2) {
				if(Status.battery>980) Status.status=1;
				Status.battery+=10;
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
