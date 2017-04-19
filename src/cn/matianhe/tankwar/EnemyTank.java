package cn.matianhe.tankwar;

import java.util.Vector;

class EnemyTank extends Tank implements Runnable {
	// 敌方坦克
	// 需要做成线程类

	int time = 0;
	// 定义一个向量，可以访问MyPanel上所有的坦克
	Vector<EnemyTank> ets = new Vector<EnemyTank>();
	// 定义一个向量存放敌人的子弹
	Vector<Shot> ss = new Vector<Shot>();

	// 敌人添加子弹，在刚刚创建坦克的时候
	public EnemyTank(int x, int y) {
		super(x, y);
	}

	// 得到MyPanel的坦克向量
	public void setEts(Vector<EnemyTank> vv) {
		this.ets = vv;
	}

	// 判断是否碰到别的坦克z
	public boolean isTouchOtherEnemy() {
		boolean b = false;

		switch (Direct) {
		case 0:
			// 我的坦克向上
			// 取出所有的敌人坦克
			for (int i = 0; i < ets.size(); i++) {
				// 取出第一个坦克
				EnemyTank et = ets.get(i);
				// 如果不是自己
				if (et != this) {
					// 如果敌人的方向是向下或者向上
					if (et.Direct == 0 || et.Direct == 1) {
						if (x >= et.x && x <= et.x + 20 && y >= et.y && y <= et.y + 30) {
							return true;
						}
						if (x + 20 >= et.x && x + 20 <= et.x + 20 && y >= et.y && y <= et.y + 30) {
							return true;
						}
					}
					if (et.Direct == 3 || et.Direct == 2) {
						if (x >= et.x && x <= et.x + 30 && y >= et.y && y <= et.y + 20) {
							return true;
						}
						if (x + 20 >= et.x && x + 20 <= et.x + 30 && y >= et.y && y <= et.y + 20) {
							return true;
						}
					}
				}
			}
			break;
		case 1:
			// 我的坦克向下
			// 取出所有的敌人坦克
			for (int i = 0; i < ets.size(); i++) {
				// 取出第一个坦克
				EnemyTank et = ets.get(i);
				// 如果不是自己
				if (et != this) {
					// 如果敌人的方向是向下或者向上
					if (et.Direct == 0 || et.Direct == 1) {
						if (x >= et.x && x <= et.x + 20 && y + 30 >= et.y && y + 30 <= et.y + 30) {
							return true;
						}
						if (x + 20 >= et.x && x + 20 <= et.x + 20 && y + 30 >= et.y && y + 30 <= et.y + 30) {
							return true;
						}
					}
					if (et.Direct == 3 || et.Direct == 2) {
						if (x >= et.x && x <= et.x + 30 && y + 30 >= et.y && y + 30 <= et.y + 20) {
							return true;
						}
						if (x + 20 >= et.x && x + 20 <= et.x + 30 && y + 30 >= et.y && y + 30 <= et.y + 20) {
							return true;
						}
					}
				}
			}

			break;
		case 2:
			// 我的坦克向左
			// 取出所有的敌人坦克
			for (int i = 0; i < ets.size(); i++) {
				// 取出第一个坦克
				EnemyTank et = ets.get(i);
				// 如果不是自己
				if (et != this) {
					// 如果敌人的方向是向下或者向上
					if (et.Direct == 0 || et.Direct == 1) {
						if (x >= et.x && x <= et.x + 20 && y >= et.y && y <= et.y + 30) {
							return true;
						}
						if (x >= et.x && x <= et.x + 20 && y + 20 >= et.y && y + 20 <= et.y + 30) {
							return true;
						}
					}
					if (et.Direct == 3 || et.Direct == 2) {
						if (x >= et.x && x <= et.x + 30 && y >= et.y && y <= et.y + 20) {
							return true;
						}
						if (x >= et.x && x <= et.x + 30 && y + 20 >= et.y && y + 20 <= et.y + 20) {
							return true;
						}
					}
				}
			}

			break;
		case 3:
			// 坦克向右
			for (int i = 0; i < ets.size(); i++) {
				// 取出第一个坦克
				EnemyTank et = ets.get(i);
				// 如果不是自己
				if (et != this) {
					// 如果敌人的方向是向下或者向上
					if (et.Direct == 0 || et.Direct == 1) {
						if (x + 30 >= et.x && x + 30 <= et.x + 20 && y >= et.y && y <= et.y + 30) {
							return true;
						}
						if (x + 30 >= et.x && x + 30 <= et.x + 20 && y + 20 >= et.y && y + 20 <= et.y + 30) {
							return true;
						}
					}
					if (et.Direct == 2 || et.Direct == 3) {
						if (x + 30 >= et.x && x + 30 <= et.x + 30 && y >= et.y && y <= et.y + 20) {
							return true;
						}
						if (x + 30 >= et.x && x + 30 <= et.x + 30 && y + 20 >= et.y && y + 20 <= et.y + 20) {
							return true;
						}
					}
				}
			}
			break;

		default:
			break;
		}
		return b;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
			switch (Direct) {
			case 0:
				for (int i = 0; i < 3; i++) {
					if (y > 0 && !isTouchOtherEnemy())
						y -= speed;
					try {
						Thread.sleep(500);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				//说明坦克正在向上移动

				break;
			case 1:
				for (int i = 0; i < 3; i++) {
					if (y < 300 && !isTouchOtherEnemy())
						y += speed;
					try {
						Thread.sleep(500);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
			case 2:
				for (int i = 0; i < 3; i++) {
					if (x > 0 && !isTouchOtherEnemy())
						x -= speed;
					try {
						Thread.sleep(500);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
			case 3:
				for (int i = 0; i < 3; i++) {
					if (x < 400 && !isTouchOtherEnemy())
						x += speed;
					try {
						Thread.sleep(500);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
			default:
				break;
			}

			time++;

			if (time % 2 == 0) {
				if (isLive) {
					if (ss.size() < 8) {
						Shot s = null;
						// 没有子弹了 就添加
						switch (Direct) {

						case 0:
							// 创建一颗子弹
							s = new Shot(x + 8, y - 4, 0);
							// 把子弹加入向量
							ss.add(s);
							break;
						case 1:
							s = new Shot(x + 9, y + 32, 1);
							ss.add(s);
							break;
						case 2:
							s = new Shot(x - 8, y + 8, 2);
							ss.add(s);
							break;
						case 3:
							s = new Shot(x + 32, y + 9, 3);
							ss.add(s);
							break;
						default:
							break;
						}
						// 启动子单线程
						Thread t = new Thread(s);
						t.start();
					}
				}
			}

			// 让坦克随机产生新的方向
			Direct = (int) (Math.random() * 4);
			// 判断敌人坦克是否死亡
			if (isLive == false) {
				// 让坦克死亡，退出线程
				break;
			}

		}
	}
}
