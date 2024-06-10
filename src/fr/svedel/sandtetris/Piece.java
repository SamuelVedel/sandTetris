package fr.svedel.sandtetris;

import java.awt.Color;
import java.awt.Graphics2D;

public class Piece {
	public static final int CUBE_N_ROW = 8;
	public static final int CUBE_N_COL = CUBE_N_ROW;
	
	public static final int N_CUBES_IN_WIDTH = 4;
	public static final int N_CUBES_IN_HEIGHT = N_CUBES_IN_WIDTH;
	
	public static final int PIECE_N_ROW = N_CUBES_IN_WIDTH*CUBE_N_ROW;
	public static final int PIECE_N_COL= N_CUBES_IN_HEIGHT*CUBE_N_COL;
	
	public static final int CUBE_WIDTH = CUBE_N_COL*Grain.WIDTH;
	public static final int CUBE_HEIGHT = CUBE_N_ROW*Grain.HEIGHT;
	
	public static final int WIDTH = PIECE_N_COL*Grain.WIDTH;
	public static final int HEIGHT = PIECE_N_ROW*Grain.HEIGHT;
	
	/** Returned by the move fct when the piece has just move */
	public static final int JUST_MOVED = 0;
	/** Returned by the move fct when the piece has been correctly posed in the grid*/
	public static final int CORRECTLY_POSED = 1;
	/** Returned by the move fct when the piece has been incorrectly posed in the grid*/
	public static final int INCORRECTLY_POSED = 2;
	
	public static final int VY = 2;
	
	private Grid grid;
	
	private int ix, iy;
	private int rotation = 0;
	private Grain[][][] states;
	
	private boolean rotatePressed = false;
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	private boolean downPressed = false;
	private boolean fallPressed = false;
	
	public Piece(Grain[][][] states, Grid grid) {
		this.grid = grid;
		this.states = states;
		ix = grid.getNCol()/2-PIECE_N_COL/2;
		iy = -PIECE_N_ROW-CUBE_N_ROW;
	}
	
	public Piece(PreStates preStates, int color, Grid grid) {
		this(preStates.generateStates(color, grid), grid);
	}
	
	public int getIx() {
		return ix;
	}
	
	public int getIy() {
		return iy;
	}
	
	public Grain[][] getState() {
		return states[rotation];
	}
	
	public void pressRotate() {
		rotatePressed = true;
	}
	
	public void pressLeft() {
		leftPressed = true;
	}
	
	public void unpressLeft() {
		leftPressed = false;
	}
	
	public void pressRight() {
		rightPressed = true;
	}
	
	public void unpressRight() {
		rightPressed = false;
	}
	
	public void pressDown() {
		downPressed = true;
	}
	
	public void pressFall() {
		fallPressed = true;
	}
	
	public int move() {
		if (rotatePressed) {
			int rot = (rotation+1)%states.length;
			if (isPosOk(rot)) {
				rotation = rot;
			}
			rotatePressed = false;
		}
		if (leftPressed) {
			int newIx;
			for (newIx = ix-VY; newIx < ix; newIx++) {
				if (isPosOk(newIx, iy)) break;
			}
			ix = newIx;
		}
		if (rightPressed) {
			int newIx;
			for (newIx = ix+VY; newIx > ix; newIx--) {
				if (isPosOk(newIx, iy)) break;
			}
			ix = newIx;
		}
		if (downPressed) {
			int newIy = iy+Piece.CUBE_N_ROW;
			if (isPosOk(ix, newIy)) {
				iy = newIy;
			}
			downPressed = false;
		}
		if (fallPressed) {
			while (isPosOk(ix, iy+1)) {
				iy += 1;
			}
		}
		
		// go down
		if (isPosOk(ix, iy+1)) {
			iy += 1;
		} else {
			if (grid.putPiece(this)) {
				return CORRECTLY_POSED;
			} else {
				return INCORRECTLY_POSED;
			}
		}
		return JUST_MOVED;
	}
	
	private boolean isPosOk(int rotation) {
		return isPosOk(rotation, ix, iy);
	}
	
	private boolean isPosOk(int ix, int iy) {
		return isPosOk(rotation, ix, iy);
	}
	
	private boolean isPosOk(int rotation, int ix, int iy) {
		for (int iy2 = 0; iy2 < states[rotation].length; ++iy2) {
			for (int ix2 = 0; ix2 < states[rotation][iy2].length; ++ix2) {
				// the coords of this grain at this pos
				int gix = ix+ix2;
				int giy = iy+iy2;
				if (states[rotation][iy2][ix2] != null) {
					if (giy >= grid.getNRow() || gix < 0 || gix >= grid.getNCol()) {
						return false;
					} else if (giy >= 0) {
						if (!grid.isEmpty(gix, giy)) return false;
					}
				}
			}
		}
		return true;
	}
	
	public void display(Graphics2D g2d) {
		display(ix*Grain.WIDTH, iy*Grain.HEIGHT, g2d);
	}
	
	public void display(int x, int y, Graphics2D g2d) {
		for (int iy2 = 0; iy2 < states[rotation].length; ++iy2) {
			for (int ix2 = 0; ix2 < states[rotation][iy2].length; ++ix2) {
				// the coords of this grain at this pos
				//int gix = ix+ix2;
				//int giy = iy+iy2;
				if (states[rotation][iy2][ix2] != null) {
					states[rotation][iy2][ix2].display(x/Grain.WIDTH+ix2,
													   y/Grain.HEIGHT+iy2, g2d);
				}
			}
		}
	}
}
