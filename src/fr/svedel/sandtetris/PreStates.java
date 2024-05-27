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
	
	private int[][][] pstates;
	
	PreStates(int[][][] pstates) {
		this.pstates = pstates;
	}
	
	public Grain[][][] generateStates(int color, Grid grid) {
		Random rand = new Random();
		
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
						int nDarker = rand.nextInt(2);
						Color thisDisplayColor = displayColor;
						for (int i = 0; i < nDarker; ++i) {
							thisDisplayColor = thisDisplayColor.darker();
						}
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
