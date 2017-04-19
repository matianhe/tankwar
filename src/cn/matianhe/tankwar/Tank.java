package cn.matianhe.tankwar;

public class Tank {

	int x = 0;
	int y = 0;
	int speed = 8;
	int Direct = 0;
	int color;
	boolean isLive = true;

	//set tank property
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getDirect() {
		return Direct;
	}

	public void setDirect(int direct) {
		Direct = direct;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
