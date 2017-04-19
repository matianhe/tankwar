package cn.matianhe.tankwar;

import java.util.Vector;

class EnemyTank extends Tank implements Runnable {
	// �з�̹��
	// ��Ҫ�����߳���

	int time = 0;
	// ����һ�����������Է���MyPanel�����е�̹��
	Vector<EnemyTank> ets = new Vector<EnemyTank>();
	// ����һ��������ŵ��˵��ӵ�
	Vector<Shot> ss = new Vector<Shot>();

	// ��������ӵ����ڸոմ���̹�˵�ʱ��
	public EnemyTank(int x, int y) {
		super(x, y);
	}

	// �õ�MyPanel��̹������
	public void setEts(Vector<EnemyTank> vv) {
		this.ets = vv;
	}

	// �ж��Ƿ��������̹��z
	public boolean isTouchOtherEnemy() {
		boolean b = false;

		switch (Direct) {
		case 0:
			// �ҵ�̹������
			// ȡ�����еĵ���̹��
			for (int i = 0; i < ets.size(); i++) {
				// ȡ����һ��̹��
				EnemyTank et = ets.get(i);
				// ��������Լ�
				if (et != this) {
					// ������˵ķ��������»�������
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
			// �ҵ�̹������
			// ȡ�����еĵ���̹��
			for (int i = 0; i < ets.size(); i++) {
				// ȡ����һ��̹��
				EnemyTank et = ets.get(i);
				// ��������Լ�
				if (et != this) {
					// ������˵ķ��������»�������
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
			// �ҵ�̹������
			// ȡ�����еĵ���̹��
			for (int i = 0; i < ets.size(); i++) {
				// ȡ����һ��̹��
				EnemyTank et = ets.get(i);
				// ��������Լ�
				if (et != this) {
					// ������˵ķ��������»�������
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
			// ̹������
			for (int i = 0; i < ets.size(); i++) {
				// ȡ����һ��̹��
				EnemyTank et = ets.get(i);
				// ��������Լ�
				if (et != this) {
					// ������˵ķ��������»�������
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
				//˵��̹�����������ƶ�

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
						// û���ӵ��� �����
						switch (Direct) {

						case 0:
							// ����һ���ӵ�
							s = new Shot(x + 8, y - 4, 0);
							// ���ӵ���������
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
						// �����ӵ��߳�
						Thread t = new Thread(s);
						t.start();
					}
				}
			}

			// ��̹����������µķ���
			Direct = (int) (Math.random() * 4);
			// �жϵ���̹���Ƿ�����
			if (isLive == false) {
				// ��̹���������˳��߳�
				break;
			}

		}
	}
}
