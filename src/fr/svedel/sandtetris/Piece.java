package fr.svedel.sandtetris;

import java.awt.Color;

public class Piece {
	public static final int CUBE_N_ROW = 8;
	public static final int CUBE_N_COL = CUBE_N_ROW;
	
	public static final int N_CUBES_IN_WIDTH = 4;
	public static final int N_CUBES_IN_HEIGHT = N_CUBES_IN_WIDTH;
	
	public static final int PIECE_N_ROW = N_CUBES_IN_WIDTH*CUBE_N_ROW;
	public static final int PIECE_N_COL= N_CUBES_IN_HEIGHT*CUBE_N_COL;
	
	private Grid grid;
	
	private int ix, iy;
	private int rotation = 0;
	private Grain[][][] states;
	
	public Piece(Grain[][][] states, Grid grid) {
		this.grid = grid;
		this.states = states;
	}
	
	public Piece(int[][][] preStates, int color, Grid grid) {
		this(generateStates(preStates, color, grid), grid);
	}
	
	private static Grain[][][] generateStates(int [][][] preStates,
											  int color, Grid grid) {
		Color displayColor;
		switch (color) {
		case Grain.BLUE:
			displayColor = Color.BLUE;
		case Grain.RED:
			displayColor = Color.RED;
		case Grain.GREEN:
			displayColor = Color.GREEN;
		case Grain.YELLOW:
			displayColor = Color.YELLOW;
		default:
			displayColor = Color.GRAY;
		}
		
		Grain[][][] states = new Grain[4][PIECE_N_ROW][PIECE_N_COL];
		for (int ir = 0; ir < states.length; ++ir) {
			for (int iy = 0; iy < states[ir].length; ++iy) {
				for (int ix = 0; ix < states[ir][iy].length; ++ix) {
					int ix2 = ix/CUBE_N_COL;
					int iy2 = iy/CUBE_N_ROW;
					if (preStates[ir][iy2][ix2] == 1) {
						states[ir][iy][ix] = new Grain(color, displayColor, grid);
					}
				}
			}
		}
		
		return states;
	}
}
