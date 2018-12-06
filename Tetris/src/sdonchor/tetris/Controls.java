package sdonchor.tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controls implements KeyListener{
	private Matrix gameBoard;
	private Leaderboard leaderboard;
	public Controls(Matrix gb,Leaderboard lb) {
		this.gameBoard=gb;
		this.leaderboard=lb;
		
	}
	/**
	 * Key press handler.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		switch(c) {
			case KeyEvent.VK_LEFT:
				gameBoard.moveBlock("left");
				break;
			case KeyEvent.VK_RIGHT:
				gameBoard.moveBlock("right");
				break;
			case KeyEvent.VK_DOWN:
				gameBoard.moveBlock("down");
				break;
			case KeyEvent.VK_SPACE:
				gameBoard.rotateBlock();
				break;
			case KeyEvent.VK_R:
				gameBoard.newGame();
				break;
			case KeyEvent.VK_0:
				leaderboard.restartLeaderboard();
				break;
			case KeyEvent.VK_9:
				//debug - add lines cleared
				//gameBoard.linesCleared++;
				break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
