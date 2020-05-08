package can;

public class MySerialWriteThread implements Runnable {

	boolean stopped;
	boolean suspended;
	String id = "14000004";
	String data = "0000000000000000";
	String msg = id + data;
	int forkliftLocX = 11;
	int forkliftLocY = 13;
	String forkliftLocXStr;
	String forkliftLocYStr;
	boolean back = false;
	boolean flag = false;
	boolean done = false;
	int stockLocX;
	int stockLocY;

	public MySerialWriteThread() {

	}
	
	void setStock(int stockLocX,int stockLocY) {
		this.stockLocX = stockLocX;
		this.stockLocY = stockLocY;
		
	}

	public void stop() {
		stopped = true;
	}

	public void suspend() {
		suspended = true;
	}

	@Override
	public void run() {

		while (!stopped) {
			if (!suspended) {

				// ������ stock �� x ��ǥ�� y��ǥ�� ���� forklift �� ��ġ�� ����Ǵ� ���� ����
				if (stockLocX > 0 && stockLocX % 4 == 2 && !done) { // ���� ���ʿ� ��ġ

					if (!back) {
						if (forkliftLocX != stockLocX - 2) {
							forkliftLocX--;

						} else if (forkliftLocY != stockLocY) {
							forkliftLocY--;

						} else if (forkliftLocY == stockLocY
								&& forkliftLocX ==stockLocX - 2) {
							forkliftLocX++;

							back = true;
						}
					}

					else {

						if (forkliftLocY != 12) {
							forkliftLocY++;
						} else if (forkliftLocX != 11) {
							forkliftLocX++;
						} else if (forkliftLocY == 12 && forkliftLocX == 11) {
							forkliftLocY = 13;
							done = true;
						}

					}

				} else if (stockLocY > 0 && stockLocX % 4 == 3 && !done) { // ���� �����ʿ� ��ġ
					if (!back) {
						if (forkliftLocX <stockLocX + 1) {
							if (!flag) {
								forkliftLocY--;
								flag = true;
							} else
								forkliftLocX++;

						} else if (forkliftLocX > stockLocX + 1) {
							forkliftLocX--;
						}

						else if (forkliftLocY != stockLocY) {
							forkliftLocY--;

						} else if (forkliftLocY ==stockLocY
								&& forkliftLocX == stockLocX + 1) {
							back = true;
						}
					}

					else {

						if (forkliftLocY != 13) {
							forkliftLocY++;
						} else if (forkliftLocX < 11) {
							forkliftLocX++;
						} else if (forkliftLocY > 11) {
							forkliftLocX--;
						}

						if (forkliftLocY == 13 && forkliftLocX == 11) {
							done = true;
						}

					}
				}
				if (done) {
					back = false;
					flag = false;
					done = false;
					stockLocX = 0;
					stockLocY = 0;
				}

				System.out.println("Current Forklift Location (X,Y) : "+forkliftLocX+" , "+forkliftLocY);

				// x,y ��ġ ���̰� 4���ڰ� �ǰ�
				if (forkliftLocX < 10) {
					forkliftLocXStr = "0" + forkliftLocX;
				} else {
					forkliftLocXStr = forkliftLocX + "";
				}

				if (forkliftLocY < 10) {
					forkliftLocYStr = "0" + forkliftLocY;
				} else {
					forkliftLocYStr = forkliftLocY + "";
				}

				String locationStr = forkliftLocXStr + forkliftLocYStr;
				int locationStrlen = locationStr.length();

				msg = msg.substring(0, msg.length() - locationStrlen) + locationStr;

				new Thread(new SerialWrite(msg)).start();

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		}

	}

}
