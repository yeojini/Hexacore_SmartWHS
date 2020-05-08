package infomatics;

public class Status {

	static int[][] defaultLocationArray = {{11,13},{12,13},{13,13},{14,13}};
	
	// forklift
	static String forkLiftID;
	static int defaultX;
	static int defaultY;
	
	
	static int status = 1; // 0 Working, 1 Waiting, 2 Charging
	static int currentX = 1;
	static int currentY = 1;
	static int battery = 999;
	static int temparature = 50;
	
	
	// task
	static int stockX = 0;
	static int stockY = 0;
	
	static msg.Task task;
}


