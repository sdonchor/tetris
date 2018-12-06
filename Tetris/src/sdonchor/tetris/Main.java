package sdonchor.tetris;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.Timer;

@SuppressWarnings("serial")
public class Main extends Canvas implements Runnable, ActionListener{

	public boolean isRunning = false;
	private static BufferStrategy buf;
	private int tileSize=25;
	private int gameAreaX=20;
	private int gameAreaY=50;
	private int gameAreaTilesX=10;
	private int gameAreaTilesY=20;
	private int score=0;
	private int gameAreaWidth=tileSize*gameAreaTilesX;
	private int gameAreaHeight=tileSize*gameAreaTilesY;
	private int sidePanelX=gameAreaWidth+gameAreaX+5;
	private int sidePanelY=gameAreaY-2;
	private int sidePanelWidth=165;
	private int sidePanelHeight=gameAreaHeight+4;
	private Color gameAreaColor = new Color(104,104,104);
	private Color borderColor = new Color(50,48,49);
	private Color gridColor = new Color(120,120,120);
	private static String musicPath="/theme.wav";
	private static File musicFile;
	private int[] levelToSpeed = new int[] {800,700,600,500,300,200,150,100};
	public static int level=0;
	private static Graphics2D g;
	private Timer timer;
	private static Window gameWindow;
	private static Image[] tiles;
	public static boolean isFirstGame=true;
	private static Leaderboard leaderboard=new Leaderboard();
	private static Matrix gameMatrix=new Matrix();
	/**
	 * Initialization of game window, graphics buffering strategy, loading textures, loading music player, initializes game thread.
	 */
	public static void main(String[] args) {
		
		gameWindow = new Window(gameMatrix,leaderboard);
		Main m = new Main();
		gameWindow.add(m);
		gameWindow.setVisible(true);
		
		m.createBufferStrategy(2);
		buf = m.getBufferStrategy();
		g = (Graphics2D) buf.getDrawGraphics();
		
		try {
			tiles = Textures.textureLoad("/tiles.png",25);
		}catch (IOException e) {
			System.out.print("Couldn't load textures.");
			System.exit(0);
		}
		
		m.threadInit();
		
		URL musicURL = Main.class.getResource(musicPath);
		try {
			musicFile = new File(musicURL.toURI());
		} catch (URISyntaxException e) {
			System.out.println("Couldn't load music file.");
		}
		@SuppressWarnings("unused")
		MusicPlayer mp=new MusicPlayer(musicFile);
	}
	/**
	 * Draws side panel.
	 * 
	 */
	private void drawSidePanel() {
		int cornerRadius=25;
		g.fillRoundRect(sidePanelX, sidePanelY, sidePanelWidth, sidePanelHeight, cornerRadius,cornerRadius);
		g.setColor(Color.WHITE);
		g.drawRoundRect(sidePanelX, sidePanelY, sidePanelWidth, sidePanelHeight,  cornerRadius,cornerRadius);
		drawScore();
		g.drawLine(sidePanelX, sidePanelY+65, sidePanelX+sidePanelWidth, sidePanelY+65);
		drawNextPiece();
		drawLeaderboard();
		drawCredits();
	}
	/**
	 * Views game manual if the game was just launched.
	 */
	private void drawStartInfo() {
		if(isFirstGame==true)
		{
			g.setColor(Color.BLACK);
			g.fillRect(gameAreaX+1,200,gameAreaWidth-1,200);
			//100 230g
			g.setColor(Color.WHITE);
			g.setFont(new Font("Impact",Font.PLAIN,20));
			g.drawString("CONTROLS", 105, 230);
			g.setFont(new Font("Impact",Font.PLAIN,15));
			g.drawString("Arrows - left, right, down", 65, 260);
			g.drawString("SPACE - rotate", 103, 280);
			g.drawString("0 - clear leaderboard     R - new game",30,300);
			g.drawLine(gameAreaX+1, 320,gameAreaX+gameAreaWidth-1, 320);
			g.setFont(new Font("Impact",Font.PLAIN,20));
			g.drawString("Press R to begin", 80, 370);
		}
	}
	/**
	 * Shows "next shape" panel, shows number of cleared lines.
	 */
	private void drawNextPiece() {
		g.drawString("Next piece:", sidePanelX+40, 140);
		int x=sidePanelX+30;
		int y=150;
		for(int i=0;i<4;i++)
		{
			x=sidePanelX+45;
			for(int j=0;j<3;j++)
			{
				if(gameMatrix.getNextBlock().getShapeMatrix()[0][j][i]&&!isFirstGame)
				{
					g.drawImage(tiles[gameMatrix.getNextBlock().getColor()-1],x,y,null);
				}
				else
				{
					g.setColor(gridColor);
					g.fillRect(x, y, 25, 25);
					g.setColor(borderColor);
					g.drawRect(x, y, 25, 25);
				}
				x+=25;
			}
			y+=25;
		}
		g.setColor(Color.WHITE);
		g.drawString("Lines cleared:",sidePanelX+5, 275);
		FontMetrics metrics=g.getFontMetrics();
		String lines=Integer.toString(gameMatrix.getLinesCleared());
		g.drawString(lines,436-metrics.stringWidth(lines),295);
	}
	/**
	 * Draws the leaderboard (5 best scores)
	 */
	private void drawLeaderboard() {
		g.setColor(Color.WHITE);
		g.drawLine(sidePanelX, 310, sidePanelX+sidePanelWidth, 310);
		g.drawString("Leaderboard:", sidePanelX+32, 340);
		int y=370;
		int nameX=sidePanelX+10;
		FontMetrics metrics=g.getFontMetrics();
		
		for(int i=0;i<5;i++)
		{
		
			g.drawString(leaderboard.getIndex(i).getName(),nameX,y);
			String points=Integer.toString(leaderboard.getIndex(i).getScore());
			
			g.drawString(points,436-metrics.stringWidth(points),y);
			y+=30;
		}
	}
	/**
	 * Shows author's name.
	 */
	private void drawCredits() {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Impact",Font.PLAIN,13));
		g.drawLine(sidePanelX, 515, sidePanelX+sidePanelWidth, 515);
		g.drawString("Author: Sebastian Donch�r", sidePanelX+10, 530);
		g.drawString("2018", sidePanelX+73, 545);
	}
	/**
	 * Shows current score.
	 */
	private void drawScore() {
		g.setFont(new Font("Impact",Font.PLAIN,20));
		g.drawString("Score:", sidePanelX+55, sidePanelY+25);
		String scoreString = String.format("%013d", score);
		g.drawString(scoreString,sidePanelX+12,sidePanelY+50);
	}
	
	/**
	 * Shows current state of game matrix and - if the game is over - a "game over" text. 
	 */
	private void drawGameArea() {
		
		g.setColor(gameAreaColor);
		g.fillRect(gameAreaX, gameAreaY, gameAreaWidth, gameAreaHeight);
		g.setColor(borderColor);
		g.drawRect(gameAreaX-1, gameAreaY-1, gameAreaWidth+2, gameAreaHeight+2);
		g.setColor(gridColor);
		int x=0;
		int y=0;
		for(int i=0;i<gameAreaTilesY;i++){
			x=0;
			for(int j=0+gameMatrix.getInvisibleColumns();j<10+gameMatrix.getInvisibleColumns();j++){
				if(gameMatrix.getBoardMatrix()[i][j]==0)
					g.drawRect(gameAreaX+x, gameAreaY+y, 25, 25);
				else
				 g.drawImage(tiles[gameMatrix.getBoardMatrix()[i][j]-1],gameAreaX+x,gameAreaY+y,25,25,null);
				x+=25;
			}
			y+=25;
		}
		if(gameMatrix.isGameOver())
		{
			g.setColor(Color.BLACK);
			g.fillRect(gameAreaX+1, 230, gameAreaWidth-1, 30);
			g.setFont(new Font("Impact",Font.PLAIN,20));
			g.setColor(Color.WHITE);
			g.drawString("GAME OVER", 100,253);
			
		}
		drawStartInfo();
	}
	/*
	 * Draws title and it's background.
	 */
	private void drawTitle() {
		int titleX=180;
		int titleY=35;
		int bgX=0;
		int bgY=3;
		int bgW=450;
		int bgH=40;
		Color titleBackgroundColor = new Color(0,148,198);
		Color titleFontColor = new Color(0,18,66);
		g.setColor(titleBackgroundColor);
	//	g.fillRoundRect(bgX,bgY,bgW,bgH,50,50);
		g.fillRect(bgX,bgY,bgW,bgH);
		g.setFont(new Font("Impact",Font.PLAIN,30));
		g.setColor(titleFontColor);
		g.drawString("TETRIS",titleX,titleY);
	}
	/*
	 * Initializes game thread and timer.
	 */
	private void threadInit() {
		new Thread(this).start();
		isRunning=true;
		this.addKeyListener(new Controls(gameMatrix,leaderboard));
		timer=new Timer(levelToSpeed[level],this);
		timer.start();
	}
	/**
	 *If the thread is running - calls graphics refresh methods.
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		
		while(isRunning)
		{
			update();
			//Graphics2D g = (Graphics2D) buf.getDrawGraphics();
			render(g);
			buf.show();
				
		}
		
		
	}
	/**
	 * Renders game graphics.
	 */
	private void render(Graphics2D g) {

		Color backgroundColor = new Color(43,48,58);
		g.setColor(backgroundColor);
		g.fillRect(0,0,Window.WIDTH,Window.HEIGHT);
		drawGameArea();
		drawTitle();
		drawSidePanel();		
	}
	/**
	 * Calls name input dialog if the score is high, initializes start configuration if the game is restarted,
	 * updates dificulty level based on lines cleared.
	 */
	private void update() {
		if(!leaderboard.isDialogOpen)
		{
			if(gameMatrix.isGameOver())
			{
				if(leaderboard.goodEnough(score))
				{
					if(!gameMatrix.wasDialogDisplayed)
					{
						leaderboard.isDialogOpen=true;
						gameMatrix.wasDialogDisplayed=true;
						leaderboard.displayNameEntry(gameWindow,gameMatrix.getScore());
						leaderboard.isDialogOpen=false;
						
					}
				}
			}
		}
		if(gameMatrix.getPaused())
		{
			if(gameMatrix.getReadyStatus())
			{
				isFirstGame=false;
				gameMatrix.setReadyStatus(false);
				level=0;
				if(!timer.equals(null))
					timer.stop();
				timer = new Timer(levelToSpeed[level],this);
				timer.start();
			}
		}
		if((int)(gameMatrix.getLinesCleared()/10)>level)
		{
			if(level!=7)
			{
				level++;
				timer.stop();
				timer.setDelay(levelToSpeed[level]);
				timer.start();
			}
		}
	}
	/**
	 * Adds a point for every game tick, makes blocks fall, updates score.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(!gameMatrix.isGameOver()&&gameMatrix.isRunning())
		{
			gameMatrix.addPoints(1);
			gameMatrix.moveBlock("down");
		}
		score=gameMatrix.getScore();
	}
	
	

}
 