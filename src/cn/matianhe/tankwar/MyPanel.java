package cn.matianhe.tankwar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MyPanel extends JPanel implements KeyListener, Runnable {
	int enSize = 10;
    Hero hero = null;

    // ����о�
    Vector<EnemyTank> enemyTanks = new Vector<EnemyTank>();

    // ����ը������
    Vector<Bomb> bombs = new Vector<Bomb>();

    // ����8��ͼƬ,8��ͼƬ���һ��ը��
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;
    Image image4 = null;
    Image image5 = null;
    Image image6 = null;
    Image image7 = null;
    Image image8 = null;

    public MyPanel() {
        hero = new Hero(100, 100);// ����̹�˳��ֵ�λ�ã�10��10��

        // ��ʼ�����˵�̹��
        for (int i = 0; i < enSize; i++) {
            // �������˵�̹�˶���
            EnemyTank et = new EnemyTank((i + 1) * 40, 0);
            et.setColor(1);
            et.setDirect(1);
            //��MyPanel�ĵ���̹�����������õ���̹��
            et.setEts(enemyTanks);

            // �������˵�̹��
            Thread t = new Thread(et);
            t.start();

            // ����������ӵ�
            Shot s = new Shot(et.x + 9, et.y + 28, 1);
            // ���������̹��
            et.ss.add(s);
            Thread t2 = new Thread(s);
            t2.start();

            enemyTanks.add(et);
        }

        // ��ʼ��ͼƬ

        image1 = Toolkit.getDefaultToolkit().getImage(
                Panel.class.getResource("/img/blast1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(
                Panel.class.getResource("/img/blast2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(
                Panel.class.getResource("/img/blast3.gif"));
        image4 = Toolkit.getDefaultToolkit().getImage(
                Panel.class.getResource("/img/blast4.gif"));
        image5 = Toolkit.getDefaultToolkit().getImage(
                Panel.class.getResource("/img/blast5.gif"));
        image6 = Toolkit.getDefaultToolkit().getImage(
                Panel.class.getResource("/img/blast6.gif"));
        image7 = Toolkit.getDefaultToolkit().getImage(
                Panel.class.getResource("/img/blast7.gif"));
        image8 = Toolkit.getDefaultToolkit().getImage(
                Panel.class.getResource("/img/blast8.gif"));
    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.fillRect(0, 0, Game.width, Game.height);// �������

        // �����ҷ�̹��

        if(hero.isLive){
        drawTank(hero.getX(), hero.getY(), g, hero.Direct, 0);// һ��Ҫ���뻭��g
        }
        // ��ss��ȡ��ÿһ���ӵ���������
        for (int i = 0; i < hero.shotBox.size(); i++) {
            Shot myShot = hero.shotBox.get(i);
            // �����ӵ�
            if (myShot != null && myShot.isLive == true) {
                // System.out.format("%d %d  ", hero.s.x,hero.s.y);
                g.setColor(Color.red);
                g.draw3DRect(myShot.x, myShot.y, 1, 1, false);
            }
            if (myShot.isLive == false) {
                hero.shotBox.remove(myShot);// ��ס��myShot ����i
            }
        }

        // ����ը��

        for (int i = 0; i < bombs.size(); i++) {
            // ȡ��ը��
            Bomb b = bombs.get(i);
            // System.out.format("%d",++num);
            if (b.life > 8) {
                g.drawImage(image1, b.x, b.y, 30, 30, this);
            } else if (b.life > 7) {
                g.drawImage(image2, b.x, b.y, 30, 30, this);
            } else if (b.life > 6) {
                g.drawImage(image3, b.x, b.y, 30, 30, this);
            } else if (b.life > 5) {
                g.drawImage(image4, b.x, b.y, 30, 30, this);
            } else if (b.life > 4) {
                g.drawImage(image5, b.x, b.y, 30, 30, this);
            } else if (b.life > 3) {
                g.drawImage(image6, b.x, b.y, 30, 30, this);
            } else if (b.life > 2) {
                g.drawImage(image7, b.x, b.y, 30, 30, this);
            } else if (b.life > 1) {
                g.drawImage(image8, b.x, b.y, 30, 30, this);
            }

            b.lifeDown();
            // ���lifeΪ 0 �Ͱ�ը����bombs����ȥ��
            if (b.life == 0) {
                bombs.remove(b);
            }
        }

        // �����з�̹��
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank et = enemyTanks.get(i);
            if (et.isLive) {
                drawTank(et.getX(), et.getY(), g, et.getDirect(), enemyTanks.get(i)
                        .getColor());
                // ��������̹���ӵ�
                for (int j = 0; j < et.ss.size(); j++) {
                    Shot enemyShot = et.ss.get(j);// jд���i
                    if (enemyShot.isLive) {
                        g.draw3DRect(enemyShot.x, enemyShot.y, 1, 1, false);
                    } else {
                        // ������˵�̹���������ʹ�Vectorȥ��
                        et.ss.remove(enemyShot);
                    }
                }
            }
        }
    }

    public void hitMe() {
        // ȡ��ÿ�����˵�̹��
        for (int i = 0; i < enemyTanks.size(); i++) {
            // ȡ��̹��
            EnemyTank et = enemyTanks.get(i);
            // ȡ��ÿ���ӵ�
            for (int j = 0; j < et.ss.size(); j++) {
                Shot enemyShot = et.ss.get(j);
                if(hero.isLive)
                isHit(enemyShot, hero);
            }
        }
    }

    public void hitEnemyTank() {
        // �ж��Ƿ���е���̹��
        for (int i = 0; i < hero.shotBox.size(); i++) {
            Shot myShot = hero.shotBox.get(i);
            // �ж��ӵ��Ƿ���Ч
            if (myShot.isLive) { 
                // ȡ��ÿ��̹�ˣ������ж�
                for (int j = 0; j < enemyTanks.size(); j++) {
                    EnemyTank et = enemyTanks.get(j);
                    if (et.isLive) {
                        isHit(myShot, et);
                    }
                }
            }
        }
    }

    // дһ������ר���ж�: �ӵ��Ƿ���е���̹��
    public void isHit(Shot s, Tank et) {
        // �жϸ�̹�˵ķ���
        switch (et.Direct) {
        // �����ϻ����£�����ͬ��
        case 0:
        case 1:
            if (s.x > et.x && s.x < et.x + 20 && s.y > et.y && s.y < et.y + 30) {
                // ���У��ӵ�����
                s.isLive = false;
                // ̹������
                et.isLive = false;
                // ������ը������vector
                Bomb b = new Bomb(et.x, et.y);
                bombs.add(b);
            }
            break;
        // �������ң���ͬ
        case 2:
        case 3:
            if (s.x > et.x && s.x < et.x + 30 && s.y > et.y && s.y <= et.y + 20) {
                s.isLive = false;
                et.isLive = false;
                Bomb b = new Bomb(et.x, et.y);
                bombs.add(b);
            }
            break;

        default:
            break;

        }
    }

    /*
     * drawTank (̹������x,y,����g,����̹������) �������ܣ� ��������-->̹�˵���ɫ�����ͣ��з�̹�ˣ��ҷ�̹�ˣ������򣬳��ֵ�����
     * 
     * ���type��default ��Ĭ����ɫΪ������ɫ̹��
     * 
     * ��װ�ԣ���̹�˷�װ�������С�
     */

    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        switch (type) {
        case 0:
            g.setColor(Color.cyan);
            break;
        case 1:
            g.setColor(Color.yellow);
        default:
            break;
        }
        switch (direct) {
        case 0:
            // ����
            g.fill3DRect(x, y, 5, 30, false);
            g.fill3DRect(x + 15, y, 5, 30, false);
            g.fill3DRect(x + 5, y + 5, 10, 20, false);
            g.fillOval(x + 4, y + 10, 10, 10);
            g.drawLine(x + 9, y + 15, x + 9, y);
            break;
        case 1:
            // ����
            g.fill3DRect(x, y, 5, 30, false);
            g.fill3DRect(x + 15, y, 5, 30, false);
            g.fill3DRect(x + 5, y + 5, 10, 20, false);
            g.fillOval(x + 4, y + 10, 10, 10);
            g.drawLine(x + 9, y + 15, x + 9, y + 30);
            break;
        case 2:
            // ����
            g.fill3DRect(x, y, 30, 5, false);
            g.fill3DRect(x, y + 15, 30, 5, false);
            g.fill3DRect(x + 5, y + 5, 20, 10, false);
            g.fillOval(x + 9, y + 4, 10, 10);
            g.drawLine(x + 5, y + 9, x - 4, y + 9);
            break;
        case 3:
            // ����
            g.fill3DRect(x, y, 30, 5, false);
            g.fill3DRect(x, y + 15, 30, 5, false);
            g.fill3DRect(x + 5, y + 5, 20, 10, false);
            g.fillOval(x + 9, y + 4, 10, 10);
            g.drawLine(x + 15, y + 9, x + 30, y + 9);
            break;
        default:
            break;
        }
        // repaint(); ��Ϊ������������repaint() ���Բ����ڼ�repaint()��
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {// ʵ�ֽӿ� ���ݰ������������ƶ� ���Կ����ٶȺ��ƶ�
        if (e.getKeyCode() == KeyEvent.VK_W) {
            hero.setDirect(0);
            hero.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(1);
            hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(2);
            hero.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(3);
            hero.moveRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            // ����
            if (hero.shotBox.size() <= 4) {
                hero.shotEnemy();
            }
        }
        // �ж�����Ƿ���J������
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void run() {
        // ÿ��100���� ���»�ͼ
        while (true) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            hitEnemyTank();
            hitMe();
            repaint();
        }
    }
}
