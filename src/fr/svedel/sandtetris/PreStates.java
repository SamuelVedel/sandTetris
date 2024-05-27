package fr.svedel.sandtetris;

import java.awt.Color;
import java.util.Random;

public enum PreStates {
	SHAPE_1(new int[][][] {
			{
				{0, 0, 0, 0}, //
				{0, 0, 0, 0}, //
				{1, 1, 1, 1}, // [][][][]
				{0, 0, 0, 0}  //
			},
			{
				{0, 0, 1, 0}, //     []
				{0, 0, 1, 0}, //     []
				{0, 0, 1, 0}, //     []
				{0, 0, 1, 0}  //     []
			},
			{
				{0, 0, 0, 0}, //
				{0, 0, 0, 0}, //
				{1, 1, 1, 1}, // [][][][]
				{0, 0, 0, 0}  //
			},
			{
				{0, 0, 1, 0}, //     []
				{0, 0, 1, 0}, //     []
				{0, 0, 1, 0}, //     []
				{0, 0, 1, 0}  //     []
			}
		}),
	
	SHAPE_2(new int[][][] {
			{
				{0, 0, 0, 0}, //
				{1, 1, 1, 0}, // [][][]
				{0, 0, 1, 0}, //     []
				{0, 0, 0, 0}  //
			},
			{
				{0, 1, 0, 0}, //   []
				{0, 1, 0, 0}, //   []
				{1, 1, 0, 0}, // [][]
				{0, 0, 0, 0}, //
			},
			{
				{1, 0, 0, 0}, // []
				{1, 1, 1, 0}, // [][][]
				{0, 0, 0, 0}, //
				{0, 0, 0, 0}  //
			},
			{
				{0, 1, 1, 0}, //   [][]
				{0, 1, 0, 0}, //   []
				{0, 1, 0, 0}, //   []
				{0, 0, 0, 0}  //
			}
		}),
	
	SHAPE_3(new int[][][] {
			{
				{0, 0, 0, 0}, //
				{1, 1, 1, 0}, // [][][]
				{1, 0, 0, 0}, // []
				{0, 0, 0, 0}  //
			},
			{
				{1, 1, 0, 0}, // [][]
				{0, 1, 0, 0}, //   []
				{0, 1, 0, 0}, //   []
				{0, 0, 0, 0}  //
			},
			{
				{0, 0, 1, 0}, //     []
				{1, 1, 1, 0}, // [][][]
				{0, 0, 0, 0}, //
				{0, 0, 0, 0}  //
			},
			{
				{0, 1, 0, 0}, //   []
				{0, 1, 0, 0}, //   []
				{0, 1, 1, 0}, //   [][]
				{0, 0, 0, 0}  //
			}
		}),
	
	SHAPE_4(new int[][][] {
			{
				{0, 0, 0, 0}, //
				{0, 1, 1, 0}, //   [][]
				{0, 1, 1, 0}, //   [][]
				{0, 0, 0, 0}  //
			},
			{
				{0, 0, 0, 0}, //
				{0, 1, 1, 0}, //   [][]
				{0, 1, 1, 0}, //   [][]
				{0, 0, 0, 0}  //
			},
			{
				{0, 0, 0, 0}, //
				{0, 1, 1, 0}, //   [][]
				{0, 1, 1, 0}, //   [][]
				{0, 0, 0, 0}  //
			},
			{
				{0, 0, 0, 0}, //
				{0, 1, 1, 0}, //   [][]
				{0, 1, 1, 0}, //   [][]
				{0, 0, 0, 0}  //
			}
		}),
	
	SHAPE_5(new int[][][] {
			{
				{0, 0, 0, 0}, //
				{0, 1, 1, 0}, //   [][]
				{1, 1, 0, 0}, // [][]
				{0, 0, 0, 0}  //
			},
			{
				{0, 1, 0, 0}, //   []
				{0, 1, 1, 0}, //   [][]
				{0, 0, 1, 0}, //     []
				{0, 0, 0, 0}  //
			},
			{
				{0, 0, 0, 0}, //
				{0, 1, 1, 0}, //   [][]
				{1, 1, 0, 0}, // [][]
				{0, 0, 0, 0}  //
			},
			{
				{0, 1, 0, 0}, //   []
				{0, 1, 1, 0}, //   [][]
				{0, 0, 1, 0}, //     []
				{0, 0, 0, 0}  //
			}
		}),
	
	SHAPE_6(new int[][][] {
			{
				{0, 0, 0, 0}, //
				{1, 1, 1, 0}, // [][][]
				{0, 1, 0, 0}, //   []
				{0, 0, 0, 0}  //
			},
			{
				{0, 1, 0, 0}, //   []
				{1, 1, 0, 0}, // [][]
				{0, 1, 0, 0}, //   []
				{0, 0, 0, 0}  //
			},
			{
				{0, 1, 0, 0}, //   []
				{1, 1, 1, 0}, // [][][]
				{0, 0, 0, 0}, //
				{0, 0, 0, 0}  //
			},
			{
				{0, 1, 0, 0}, //   []
				{0, 1, 1, 0}, //   [][]
				{0, 1, 0, 0}, //   []
				{0, 0, 0, 0}  //
			}
		}),
	
	SHAPE_7(new int[][][] {
			{
				{0, 0, 0, 0}, //
				{1, 1, 0, 0}, // [][]
				{0, 1, 1, 0}, //   [][]
				{0, 0, 0, 0}  //
			},
			{
				{0, 0, 1, 0}, //     []
				{0, 1, 1, 0}, //   [][]
				{0, 1, 0, 0}, //   []
				{0, 0, 0, 0}  //
			},
			{
				{0, 0, 0, 0}, //
				{1, 1, 0, 0}, // [][]
				{0, 1, 1, 0}, //   [][]
				{0, 0, 0, 0}  //
			},
			{
				{0, 0, 1, 0}, //     []
				{0, 1, 1, 0}, //   [][]
				{0, 1, 0, 0}, //   []
				{0, 0, 0, 0}  //
			}
		});
	
	private static final int NORMAL = 0;
	private static final int RANDOM_MOTIF = 1;
	private static final int CHECKERBOARD_MOTIF = 2;
	private static final int MINI_SQUARE_MOTIF = 3;
	private static final int NUM_MOTIF = 4;
	
	private int[][][] pstates;
	
	PreStates(int[][][] pstates) {
		this.pstates = pstates;
	}
	
	private Color generateDispalyColor(int ix, int iy, Color displayColor, int motif) {
		switch (motif) {
		case NORMAL:
			return displayColor;
		case RANDOM_MOTIF:
			Random rand = new Random();
			int nDarker = rand.nextInt(2);
			Color thisDisplayColor = displayColor;
			for (int i = 0; i < nDarker; ++i) {
				thisDisplayColor = thisDisplayColor.darker();
			}
			return thisDisplayColor;
		case CHECKERBOARD_MOTIF:
			if ((ix/(Piece.CUBE_N_COL/2)+iy/(Piece.CUBE_N_ROW/2))%2 == 0) {
				return displayColor.darker();
			} else {
				return displayColor;
			}
		case MINI_SQUARE_MOTIF:
			int ix2 = ix%Piece.CUBE_N_COL;
			int iy2 = iy%Piece.CUBE_N_ROW;
			if (((ix2 >= 2 && ix2 <= 5) && (iy2 == 2 || iy2 == 5))
				|| ((ix2 == 2 || ix2 == 5) && (iy2 >= 2 && iy2 <= 5))
				|| ix2 == 0 || iy2 == 0 || ix2 == Piece.CUBE_N_COL-1
				|| iy2 == Piece.CUBE_N_ROW-1) {
				return displayColor.darker();
			} else {
				return displayColor;
			}
		default:
			return displayColor;
		}
	}
	
	public Grain[][][] generateStates(int color, Grid grid) {
		Random rand = new Random();
		int motif = rand.nextInt(NUM_MOTIF);
		
		Color displayColor;
		switch (color) {
		case Grain.BLUE:
			displayColor = Color.BLUE.darker();
			break;
		case Grain.RED:
			displayColor = Color.RED.darker();
			break;
		case Grain.GREEN:
			displayColor = Color.GREEN.darker();
			break;
		case Grain.YELLOW:
			displayColor = Color.YELLOW.darker();
			break;
		default:
			displayColor = Color.GRAY.darker();
		}
		
		Grain[][][] states = new Grain[4][Piece.PIECE_N_ROW][Piece.PIECE_N_COL];
		for (int ir = 0; ir < states.length; ++ir) {
			for (int iy = 0; iy < states[ir].length; ++iy) {
				for (int ix = 0; ix < states[ir][iy].length; ++ix) {
					int ix2 = ix/Piece.CUBE_N_COL;
					int iy2 = iy/Piece.CUBE_N_ROW;
					if (pstates[ir][iy2][ix2] == 1) {
						Color thisDisplayColor =
							generateDispalyColor(ix, iy, displayColor, motif);
						states[ir][iy][ix] = new Grain(color, thisDisplayColor, grid);
					} else {
						states[ir][iy][ix] = null;
					}
				}
			}
		}
		
		return states;
	}
}
