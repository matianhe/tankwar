package cn.matianhe.tankwar;

public class Shot implements Runnable {
	int x;
	int y;
	int Direct;
	int speed = 8;
	boolean isLive = true;

	public Shot(int x, int y, int Direct) {
		this.x = x;
		this.y = y;
		this.Direct = Direct;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(50);
			} catch (Exception e) {
			}
			// 0 up  2 left
			switch (Direct) {
			case 0:
				y -= speed;
				break;
			case 1:
				y += speed;
				break;
			case 2:
				x -= speed;
				break;
			case 3:
				x += speed;
				break;
			default:
				break;
			}

			// ×Óµ¯ºÎÊ±ËÀÍö
			if (x < 0 || x > Game.width || y < 0 || y > Game.height) {
				this.isLive = false;
				break;
			}
		}
	}
}
