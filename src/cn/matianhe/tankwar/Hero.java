package cn.matianhe.tankwar;

import java.util.Vector;

class Hero extends Tank {

	// 子弹

	Vector<Shot> shotBox = new Vector<Shot>();
	Shot shot = null;

	public Hero(int x, int y) {
		super(x, y);
	}

	// 开火
	public void shotEnemy() {

		switch (Direct) {
		case 0:
			// 创建一颗子弹
			shot = new Shot(x + 8, y - 4, 0);
			// 把子弹加入向量
			shotBox.add(shot);
			break;
		case 1:
			shot = new Shot(x + 9, y + 32, 1);
			shotBox.add(shot);
			break;
		case 2:
			shot = new Shot(x - 8, y + 8, 2);
			shotBox.add(shot);
			break;
		case 3:
			shot = new Shot(x + 32, y + 9, 3);
			shotBox.add(shot);
			break;
		default:
			break;
		}
		Thread t = new Thread(shot);
		t.start();
	}

	public void moveUp() {
		y -= speed;
	}

	public void moveDown() {
		y += speed;
	}

	public void moveLeft() {
		x -= speed;
	}

	public void moveRight() {
		x += speed;
	}
}
