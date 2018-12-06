package sdonchor.tetris;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Matrix {
	private int boardMatrix[][] = new int[][] { //y x
				   //start: x=3
							 //end: x=12
		//init done this way for debug purposes
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		/****************************************/
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0}
	};
	private int matrixCopy[][] = new int[][] {
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		/****************************************/
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0},
		{0,0,0,/**/0,0,0,0,0,0,0,0,0,0/**/,0,0,0}
		};
	private int fallingX;
	private int fallingY;
	private int spawnX=6;
	private int spawnY=0;
	private int score=0;
	private boolean runs=false;
	private boolean paused=true;
	private final int forbiddenY=20;
	private final int forbiddenXleft=2;
	public boolean wasDialogDisplayed=false;
	private final int forbiddenXright=13;
	public int linesCleared=0;
	private int rotation=0;
	private Blocks[] blockTypes= new Blocks[] {
			new LineBlock(), new SquareBlock(),
			new LBlock(), new JBlock(),
			new TBlock(), new ZBlock(),
			new SBlock()
	};
	private Blocks nextBlock;
	private Blocks fallingBlock;
	private int invisibleRows=3;
	private boolean gameOver=false;
	private boolean readyForInit=false;
	private int invisibleColumns=3;
	public Matrix() {
		fillBorders();
		nextBlock=pickNext();
		//spawnNew();

	}
	/**
	 * Picks next random block.
	 */
	private Blocks pickNext() {
		int rand = ThreadLocalRandom.current().nextInt(0,7);
		return blockTypes[rand]; 
	}
	/**
	 * Returns game matrix.
	 */
	public int[][] getBoardMatrix() {
		return boardMatrix;
	}
	/**
	 * Returns if the game is ready to start.
	 */
	public boolean getReadyStatus() {
		return readyForInit;
	}
	/**
	 * Returns the number of cleared lines.
	 */
	public int getLinesCleared() {
		return linesCleared;
	}
	/*
	 * Fills in non-rendered game board edges with blocks so that currently moving block can't go outside the rendering zone.
	 */
	private void fillBorders() {
		for(int y=0;y<23;y++)
		{
			for(int x=0;x<16;x++)
			{
				if(x==forbiddenXleft||x==forbiddenXright)
					boardMatrix[y][x]=1;
			}
		}
	}
	/**
	 * Returns if the game is paused.
	 */
	public boolean getPaused() {
		return paused;
	}
	/**
	 * Returns how many columns are not rendered.
	 */
	public int getInvisibleColumns() {
		return invisibleColumns;
	}
	/*
	 * debug
	 * Prints matrix' state
	 */
	public void debugPrint2DMatrix(int[][] matrix,int width, int height)
	{
		for(int y=0; y<height; y++)
		{
			for(int x=0;x<width;x++)
			{
				System.out.print(matrix[y][x]);//xy
			}
			System.out.print("\n");
		}
	}
	/**
	 * debug
	 * Prints block shape matrix.
	 */
	public void debugPrint2DMatrix(boolean[][][] matrix,int rot, int width, int height)
	{
		for(int y=0; y<height; y++)
		{
			for(int x=0;x<width;x++)
			{
				if(matrix[rot][x][y])
				System.out.print(1);
				else System.out.print(0);
			}
			System.out.print("\n");
		}
	}
	/**
	 * Returns next block
	 */
	public Blocks getNextBlock() {
		return nextBlock;
	}
	/*
	 * Returns the number of unrendered rows.
	 */
	public int getInvisibleRows() {
		return invisibleRows;
	}
	/**
	 * Prepares for a new game - clears the game matrix, restarts difficulty level, score and number of cleared lines,
	 * creates a new block.
	 */
	public void newGame() {
		for(int y=0;y<23;y++)
		{
			for(int x=0;x<16;x++)
			{
				boardMatrix[y][x]=0;
			}
		}
		runs=true;
		fillBorders();
		readyForInit=true;
		gameOver=false;
		linesCleared=0;
		wasDialogDisplayed=false;
		Main.level=0;
		spawnNew();
		nextBlock=pickNext();
		score=0;
		
	}
	/**
	 * Sets ready status for new game.
	 */
	public void setReadyStatus(boolean x) {
		this.readyForInit=x;
	}
	/**
	 * If it's possible - creates a new block on the map. If not - signals that the game is over.
	 */
	public void spawnNew() {
		int shapeX=0,shapeY=0;
		rotation=0;
		//check if spawning possible
		for(int y=spawnY;y<spawnY+4;y++)
		{
			shapeX=0;
			for(int x=spawnX;x<spawnX+4;x++)
			{
				if(nextBlock.getShapeMatrix()[rotation][shapeX][shapeY])
					if(boardMatrix[y][x]!=0){
						gameOver=true;
						fallingBlock=nextBlock;
						return;
					}	
				shapeX++;
			}
			shapeY++;
		}
		//if possible then spawn
		shapeX=0;shapeY=0;
		if(!gameOver)
		for(int y=spawnY;y<spawnY+4;y++)
		{
			shapeX=0;
			for(int x=spawnX;x<spawnX+4;x++)
			{
				if(nextBlock.getShapeMatrix()[0][shapeX][shapeY])
					boardMatrix[y][x]=nextBlock.color;
				shapeX++;
			}
			shapeY++;
		}
		fallingX=spawnX;
		fallingY=spawnY;
		fallingBlock=nextBlock;
		nextBlock=pickNext();
	}
	/**
	 * Moves the block left, right or down if possible. If not and the direction was down - adds a score for landing
	 * the piece and spawns a new block.
	 */
	public void moveBlock(String dir) {
		if(!Main.isFirstGame && !gameOver)
		{
			if(canMove(dir))
			{
				int shapeX=0,shapeY=0;
				for(int y=fallingY;y<fallingY+4;y++)
					{
						shapeX=0;
						for(int x=fallingX;x<fallingX+4;x++)
						{
							if(fallingBlock.getShapeMatrix()[rotation][shapeX][shapeY])
								boardMatrix[y][x]=0;
								shapeX++;
						}
						shapeY++;
					}
				if(dir=="down")
					fallingY++;
				if(dir=="left")
					fallingX--;
				if(dir=="right")
					fallingX++;
				shapeY=0;
				for(int y=fallingY;y<fallingY+4;y++)
				{
					shapeX=0;
					for(int x=fallingX;x<fallingX+4;x++)
					{
						if(fallingBlock.getShapeMatrix()[rotation][shapeX][shapeY])
							boardMatrix[y][x]=fallingBlock.getColor();
						shapeX++;
					}
					shapeY++;
				}
			}
			else
			{
				if(dir=="down")
				{
					checkLines();
					spawnNew();
					if(!gameOver)
					addPoints(100);
				}
			}
		}
	}
	/*
	 * Returns current score.
	 */
	public int getScore() {
		return score;
	}
	/*
	 * Adds points to the score.
	 */
	public void addPoints(int x) {
		score+=x;
	}
	/*
	 * Checks if movement in given direction is possible.
	 */
	private boolean canMove(String dir) {
		int shapeX=0, shapeY=0;
		int newPosX=0, newPosY=0;
		int lastY=0,lastX=0;
		if(dir=="down")
		{
			newPosX=fallingX;
			newPosY=fallingY+1;
			for(int y=0;y<4;y++)
			{
				for(int x=0;x<4;x++)
				{
					if(fallingBlock.getShapeMatrix()[rotation][x][y])
						lastY=y;
				}
			}
			if(fallingY+lastY>=forbiddenY-1) return false;
		}
		if(dir=="left")
		{
			newPosX=fallingX-1;
			newPosY=fallingY;
			for(int y=0;y<4;y++)
			{
				for(int x=3;x>=0;x--)
				{
					if(fallingBlock.getShapeMatrix()[rotation][x][y])
						lastX=x;
				}
			}
			if(fallingX+lastX-1<=forbiddenXleft) return false;
		}
		if(dir=="right")
		{
			newPosX=fallingX+1;
			newPosY=fallingY;
			for(int y=0;y<4;y++)
			{
				for(int x=0;x<4;x++)
				{
					if(fallingBlock.getShapeMatrix()[rotation][x][y])
						lastX=x;
				}
			}
			if(fallingX+lastX+1>=forbiddenXright) return false;
		}
		clearCopy();
		//moving the block into the copy
		for(int y=fallingY;y<fallingY+4;y++)
		{
			shapeX=0;
			for(int x=fallingX;x<fallingX+4;x++)
			{
				if(fallingBlock.getShapeMatrix()[rotation][shapeX][shapeY])
					matrixCopy[y][x]=1;
				shapeX++;
			}
			shapeY++;
		}
		
		//check
		shapeY=0;
		for(int y=newPosY;y<newPosY+4;y++)
		{
			shapeX=0;
			for(int x=newPosX;x<newPosX+4;x++)
			{
				boolean selfCollide=false;
				if(boardMatrix[y][x]!=0 && matrixCopy[y][x]!=0) {selfCollide=true;}
				if(!selfCollide)
				{
					if(fallingBlock.getShapeMatrix()[rotation][shapeX][shapeY] && boardMatrix[y][x]!=0)
						return false;
				}
				shapeX++;
			}
			shapeY++;
		}
		return true;
	}
	/**
	 * Clears matrix' copy.
	 */
	private void clearCopy() {
	//czyszczenie kopii
		for(int y=0;y<23;y++)
		{
			for(int x=0;x<16;x++)
			{
				matrixCopy[y][x]=0;
			}
		}
	}
	/**
	 * If possible - rotates the block.
	 */
	public void rotateBlock() {
		if(!Main.isFirstGame && !gameOver)
		{
			if(canRotate())
			{
				int shapeX=0, shapeY=0;
				int oldRotation=rotation;
				if(rotation==3) 
				{
					rotation=0;
				}
				else
				{
					rotation++;
				}
				//removal of current block
				for(int y=fallingY;y<fallingY+4;y++)
				{
					shapeX=0;
					for(int x=fallingX;x<fallingX+4;x++)
					{
						if(fallingBlock.getShapeMatrix()[oldRotation][shapeX][shapeY])
							boardMatrix[y][x]=0;
						shapeX++;
					}
					shapeY++;
				}
				//added new block (new rotation)
				shapeY=0;
				for(int y=fallingY;y<fallingY+4;y++)
				{
					shapeX=0;
					for(int x=fallingX;x<fallingX+4;x++)
					{
						if(fallingBlock.getShapeMatrix()[rotation][shapeX][shapeY])
							boardMatrix[y][x]=fallingBlock.getColor();
						shapeX++;
					}
					shapeY++;
				}
			}
		}
		
	}
	/**
	 * Checks if block can rotate in its current position.
	 */
	private boolean canRotate() {
		clearCopy();
		//to copy
		int shapeX=0,shapeY=0;
		for(int y=fallingY;y<fallingY+4;y++)
		{
			shapeX=0;
			for(int x=fallingX;x<fallingX+4;x++)
			{
				if(fallingBlock.getShapeMatrix()[rotation][shapeX][shapeY])
					matrixCopy[y][x]=1;
				shapeX++;
			}
			shapeY++;
		}
		
		//check
		shapeY=0;
		int nextRotation;
		if(rotation==3)
			nextRotation=0;
		else
			nextRotation=rotation+1;
		for(int y=fallingY;y<fallingY+4;y++)
		{
			shapeX=0;
			for(int x=fallingX;x<fallingX+4;x++)
			{
				boolean selfCollide=false;
				if(boardMatrix[y][x]!=0 && matrixCopy[y][x]!=0) {selfCollide=true;}
				if(!selfCollide)
				{
					if(fallingBlock.getShapeMatrix()[nextRotation][shapeX][shapeY] && boardMatrix[y][x]!=0)
					{
						return false;
					}
				}
				if(fallingBlock.getShapeMatrix()[nextRotation][shapeX][shapeY] && (x<=forbiddenXleft || x>=forbiddenXright || y>=forbiddenY))
				{
					return false;
				}
				shapeX++;
			}
			shapeY++;
		}
		return true;
	}
	/**
	 * Returns game over state.
	 */
	public boolean isGameOver() {
		return gameOver;
	}
	/**
	 * Checks if there are completed lines, clears them and adds points for each.
	 */
	private void checkLines() {
		boolean fullLine=true;
		int rows=0;
		for(int y=0;y<20;y++)
		{
			fullLine=true;
			for(int x=3;x<13;x++)
			{
				if(boardMatrix[y][x]==0)
				{
					
					fullLine=false;
					break;
				}

			}
			if(fullLine)
			{
				clearRow(y);
				rows++;
			}
		}
		linesCleared+=rows;
		addPoints(rows*1000);
		fillBorders();
	}
	/*
	 * Returns game running state.
	 */
	public boolean isRunning()
	{
		return runs;
	}
	/*
	 * Clears row with a given index.
	 */
	private void clearRow(int row)
	{
		List<int[]>list = new ArrayList<int[]>();
		int[] emptyRow=new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		for(int y=0;y<23;y++)
			list.add(boardMatrix[y]);
		list.remove(row);
		list.add(0,emptyRow);
		for(int y=0;y<23;y++)
			boardMatrix[y]=list.get(y);
	}

}
