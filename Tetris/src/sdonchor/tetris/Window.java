package sdonchor.tetris;

import java.awt.Dimension;
import java.awt.Toolkit;


import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame {
	public static final int WIDTH = 450;
	public static final int HEIGHT = 600;
	public  Matrix gameBoard;
	public int locationX,locationY;
	Controls keyListener;
	Leaderboard leaderboard;
	/**
	 * Creates the game window and adds a KeyListener to it.
	 */
	public Window(Matrix gb,Leaderboard lb) {
		super("Tetris");
		this.gameBoard=gb;
		this.leaderboard=lb;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(WIDTH,HEIGHT);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		locationX=(int) (screenSize.getWidth()/2)-(WIDTH/2);
		locationY=(int) (screenSize.getHeight()/2)-(HEIGHT/2);
		setLocation(locationX,locationY);
		
		this.addKeyListener(new Controls(gameBoard,leaderboard));
		requestFocus();
		
	}
}
