package cn.matianhe.tankwar;

import javax.swing.JFrame;

/**
 * @author matianhe
 *
 */
@SuppressWarnings("serial")
public class Game extends JFrame {

	/**
	 * @param args
	 */
	MyPanel mainPanel = null;
	
	/*
	 * 4.10 update 
	 * set size to static
	 * separate Tank class
	 * */
	protected static int width=500,height=500;
    public Game() {
        mainPanel = new MyPanel();
        Thread t = new Thread(mainPanel);
        t.start();
        add(mainPanel);
        setSize(width, height);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(mainPanel);// ×¢²á¼àÌý

    }

    public static void main(String[] args) {
        new Game();
    }
}
