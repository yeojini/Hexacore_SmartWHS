package infomatics;

import msg.Task;

public class Move implements Runnable {

	public Move() {
	}
	@Override
	public void run() {
		System.out.println("move run");
		if (Status.stockX%4==2) {
			System.out.println("Status.stockX%4==2");
			if (Status.stockX-2 < Status.currentX ) {
				while(!(Status.currentX == Status.stockX-2)) {
					--Status.currentX;
					waitOneSecond();
				}
				while(!(Status.currentY == Status.stockY)) {
					--Status.currentY;
					waitOneSecond();
				}
				++Status.currentX;
				waitOneSecond();
				while(!(Status.currentY == Status.defaultY-1)) {
					++Status.currentY;
					waitOneSecond();
				}
				while(!(Status.currentX == Status.defaultX)) {
					if(Status.currentX < Status.defaultX) ++Status.currentX;
					else --Status.currentX;
					waitOneSecond();
				}
				++Status.currentY;
				taskDoneChangeStatus();

				
				
			} else if (Status.currentX <= Status.stockX-2) {
				--Status.currentY;
				waitOneSecond();
				while(!(Status.currentX == Status.stockX-2)) {
					++Status.currentX;
					waitOneSecond();
				}
				while(!(Status.currentY == Status.stockY)) {
					--Status.currentY;
					waitOneSecond();
				}
				++Status.currentX;
				waitOneSecond();
				while(!(Status.currentY == Status.defaultY)) {
					++Status.currentY;
					waitOneSecond();
				}
				while(!(Status.currentX == Status.defaultX)) {
					if(Status.currentX < Status.defaultX) ++Status.currentX;
					else --Status.currentX;
					waitOneSecond();
				}
				taskDoneChangeStatus();
				
				
			}
			
			
			
			
		} else if (Status.stockX%4==3) {
			System.out.println("Status.stockX%4==3");
			if (Status.stockX+1 < Status.currentX ) {
				while(!(Status.currentX == Status.stockX+1)) {
					--Status.currentX;
					waitOneSecond();
				}
				while(!(Status.currentY == Status.stockY)) {
					--Status.currentY;
					waitOneSecond();
				}
				++Status.currentX;
				waitOneSecond();
				while(!(Status.currentY == Status.defaultY)) {
					++Status.currentY;
					waitOneSecond();
				}
				while(!(Status.currentX == Status.defaultX)) {
					if(Status.currentX < Status.defaultX) ++Status.currentX;
					else --Status.currentX;
					waitOneSecond();
				}
				taskDoneChangeStatus();

				
			} else if (Status.currentX <= Status.stockX+1) {
				--Status.currentY;
				waitOneSecond();
				while(!(Status.currentX == Status.stockX+1)) {
					++Status.currentX;
					waitOneSecond();
				}
				while(!(Status.currentY == Status.stockY)) {
					--Status.currentY;
					waitOneSecond();
				}
				++Status.currentX;
				waitOneSecond();
				while(!(Status.currentY == Status.defaultY)) {
					++Status.currentY;
					waitOneSecond();
				}
				while(!(Status.currentX == Status.defaultX)) {
					if(Status.currentX < Status.defaultX) ++Status.currentX;
					else --Status.currentX;
					waitOneSecond();
				}
				taskDoneChangeStatus();
				
				
				
			}
			
			
		}
		
	}
	
	public synchronized void waitOneSecond() {
		try {
			this.wait(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void taskDoneChangeStatus() {
		Status.task = null;
		if(Status.battery<300) Status.status = 2;
		else Status.status = 1;
	}

}
