package sdonchor.tetris;

abstract class Blocks {
	protected String lineCode[]= {"0100010001000100", "0000111100000000","0010001000100010","0000000011110000"};
	protected String squareCode[] = {"0000011001100000","0000011001100000","0000011001100000","0000011001100000"};
	protected String lshapeCode[]= {"1100010001000000","0010111000000000","0100010001100000","0000111010000000"};
	protected String jshapeCode[]= {"0110010001000000","0000111000100000","0100010011000000","1000111000000000"};
	protected String tshapeCode[]= {"0100111000000000","0100011001000000","0000111001000000","0100110001000000"};
	protected String zshapeCode[]= {"0100110010000000","1100011000000000","0010011001000000","0000110001100000"};
	protected String sshapeCode[]= {"1000110001000000","0110110000000000","0100011000100000","0000011011000000"};
	protected boolean[][][] shapeMatrix = new boolean[4][4][4];
	public int color;
	/**
	 * Translates shape's binary form into a 3D 4x4x4 array (rotation x width x height)
	 * 
	 */
	public void parseShapeCode(String[] code) {
		for(int i=0;i<4;i++){
			int currentIndex=0;
			for(int y=0;y<4;y++) {
				for(int x=0;x<4;x++) {
					if((code[i].charAt(currentIndex))=='1') {
						this.shapeMatrix[i][x][y]=true;

					}
					else {
						this.shapeMatrix[i][x][y]=false;

					}
					currentIndex++;
				}

			}
		}
	}
	/**
	 * Returns color number.
	 */
	public int getColor() {
		return color;
	}
	/**
	 * Returns block's shape matrix.
	 */
	public boolean[][][] getShapeMatrix(){
		return shapeMatrix;
	}
	
}
	class LineBlock extends Blocks{
		public LineBlock() {
			shapeMatrix=new boolean[4][4][4];
			parseShapeCode(lineCode);
			color=7;
		}
		
	}
	class SquareBlock extends Blocks{
		public SquareBlock() {
			color=1;
			shapeMatrix=new boolean[4][4][4];
			parseShapeCode(squareCode);
		}
		
	}
	class LBlock extends Blocks{
		public LBlock() {
			color=2;
			shapeMatrix=new boolean[4][4][4];
			parseShapeCode(lshapeCode);
		}
		
	}
	class JBlock extends Blocks{
		public JBlock() {
			color=3;
			shapeMatrix=new boolean[4][4][4];
			parseShapeCode(jshapeCode);
		}
		
	}
	class TBlock extends Blocks{
		public TBlock() {
			color=4;
			shapeMatrix=new boolean[4][4][4];
			parseShapeCode(tshapeCode);
		}
		
	}
	class ZBlock extends Blocks{
		public ZBlock() {
			color=5;
			shapeMatrix=new boolean[4][4][4];
			parseShapeCode(zshapeCode);
		}
		
	}
	class SBlock extends Blocks{
		public SBlock() {
			color=6;
			shapeMatrix=new boolean[4][4][4];
			parseShapeCode(sshapeCode);
		}
		
	

	
}

