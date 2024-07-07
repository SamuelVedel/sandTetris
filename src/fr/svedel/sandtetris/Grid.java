package fr.svedel.sandtetris;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class Grid {
	private int nRow = 20*Piece.CUBE_N_ROW;
	private int nCol = 12*Piece.CUBE_N_COL;
	
	private Play play;
	
	public Grain[][] grid;
	public ArrayList<int[]> updatedCoords = new ArrayList<>();
	
	public Grid(Play play) {
		this.play = play;
		grid = new Grain[nRow][nCol];
	}
	
	public int getNCol() {
		return nCol;
	}
	
	public int getNRow() {
		return nRow;
	}
	
	public int getWidth() {
		return getNCol()*Grain.WIDTH;
	}
	
	public int getHeight() {
		return getNRow()*Grain.HEIGHT;
	}
	
	public void addGrain(Grain grain, int ix, int iy) {
		grid[iy][ix] = grain;
	}
	
	public boolean isVoid(int ix, int iy) {
		return ix < 0 || iy < 0 || ix >= nCol || iy >= nRow;
	}
	
	public boolean isEmpty(int ix, int iy) {
		return grid[iy][ix] == null;
	}
	
	/**
	 * Put a piece in the Grid
	 *
	 * @param piece The piece we want to put
	 * @return false if the piece don't fit in the grid,
	 * and true otherwise
	 */
	public boolean putPiece(Piece piece) {
		Grain[][] state = piece.getState();
		int ix = piece.getIx();
		int iy = piece.getIy();
		for (int iy2 = 0; iy2 < state.length; ++iy2) {
			for (int ix2 = 0; ix2 < state.length; ++ix2) {
				int gix = ix+ix2;
				int giy = iy+iy2;
				if (state[iy2][ix2] != null) {
					if (isVoid(gix, giy)) return false;
					grid[giy][gix] = state[iy2][ix2];
					updatedCoords.add(new int[] {gix, giy});
				}
			}
		}
		return true;
	}
	
	private int[] getRandomIxArray() {
		int[] ixArray = new int[nCol];
		Random rand = new Random();
		ArrayList<Integer> values = new ArrayList<>();
		for (int i = 0; i < ixArray.length; ++i) {
			values.add(i);
		}
		for (int i = 0; i < ixArray.length; ++i) {
			int i2 = rand.nextInt(values.size());
			ixArray[i] = values.get(i2);
			values.remove(i2);
		}
		return ixArray;
	}
	
	public void update() {
		for (int iy = grid.length-1; iy >= 0; --iy) {
			int[] ixArray = getRandomIxArray();
			for (int ix: ixArray) {
				if (!isEmpty(ix, iy)) {
					int[] newCoords = grid[iy][ix].update(ix, iy);
					if (!coordsEqual(newCoords, new int[] {ix, iy})) {
						grid[newCoords[1]][newCoords[0]] = grid[iy][ix];
						grid[iy][ix] = null;
						updatedCoords.add(newCoords);
					}
				}
			}
		}
		deletLines();
	}
	
	public void deletLines() {
		/*int oldColor = -1;
		for (int iy = 0; iy < nRow; ++iy) {
			if (!isEmpty(0, iy))  {
				int color = grid[iy][0].getColor();
				if (oldColor != color) {
					ArrayList<int[]> connexComponent = new ArrayList<>();
					if (getConnexComponent(0, iy, color, connexComponent) == 3) {
						play.increaseScore(connexComponent.size());
						for (int[] coords: connexComponent) {
							grid[coords[1]][coords[0]] = null;
						}
					}
					oldColor = color;
				}
			} else {
				oldColor = -1;
			}
		}*/
		
		while (updatedCoords.size() > 0) {
			int[] startCoords = updatedCoords.get(0);
			int ix = startCoords[0];
			int iy = startCoords[1];
			if (isEmpty(ix, iy)) {
				updatedCoords.remove(0);
				continue;
			}
			int color = grid[iy][ix].getColor();
			ArrayList<int[]> connexComponent = new ArrayList<>();
			int result = getConnexComponent(ix, iy, color, connexComponent);
			if (result == 3) play.increaseScore(/*connexComponent.size()*/1);
			for (int[] coords: connexComponent) {
				if (result == 3) grid[coords[1]][coords[0]] = null;
				int index = indexInAlOfCoords(updatedCoords, coords);
				if (index >= 0) {
					updatedCoords.remove(index);
				}
			}
		}
	}
	
	private boolean coordsEqual(int[] coords1, int[] coords2) {
		return coords1[0] == coords2[0] && coords1[1] == coords2[1];
	}
	
	private int indexInAlOfCoords(ArrayList<int[]> al, int[] coords) {
		for (int i = 0; i < al.size(); ++i) {
			if (al.get(i)[0] == coords[0] && al.get(i)[1] == coords[1]) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Find a connex component and say if the component cross the grid
	 *
	 * @param ix the abscissa of the point where we start to get the component
	 * @param iy the ordinates of the point where we start to get the component
	 * @param color the color of the component
	 * @param connexComponent the ArrayList where the component is stocked
	 * @return
	 * <ul>
	 * <li> 1: the component reach the left side </li>
	 * <li> 2: the component reach the right side </li>
	 * <li> 3: the component reach both sides </li>
	 * <li> 0: the component don't reach any side </li>
	 * </ul>
	 */
	private int getConnexComponent(int ix, int iy, int color,
								   ArrayList<int[]> connexComponent) {
		if (!isVoid(ix, iy) && !isEmpty(ix, iy)
			&& grid[iy][ix].getColor() == color
			&& indexInAlOfCoords(connexComponent, new int[] {ix, iy}) < 0) {
			connexComponent.add(new int[] {ix, iy});
		} else {
			return 0;
		}
		int crosses = 0;
		for (int iy2 = iy-1; iy2 <= iy+1; ++iy2) {
			for (int ix2 = ix-1; ix2 <= ix+1; ++ix2) {
				if (ix != ix2 || iy != iy2) {
					int result = getConnexComponent(ix2, iy2, color, connexComponent);
					crosses |= result&1; // if it reach left side
					crosses |= result&2; // if it reach right side
				}
			}
		}
		if (ix == 0) {
			return crosses | 1;
		} else if (ix == nCol-1) {
			return crosses | 2;
		} else {
			return crosses;
		}
	}
	
	public void clear() {
		for (int iy = 0; iy < grid.length; ++iy) {
			for (int ix = 0; ix < grid[iy].length; ++ix) {
				grid[iy][ix] = null;
			}
		}
	}
	
	public void display(Graphics2D g2d) {
		int w = nCol*Grain.WIDTH;
		int h = nRow*Grain.HEIGHT;
		
		g2d.setColor(Color.DARK_GRAY.darker());
		g2d.fillRect(0, 0, w, h);
		
		for (int iy = 0; iy < grid.length; ++iy) {
			for (int ix = 0; ix < grid[iy].length; ++ix) {
				if (!isEmpty(ix, iy)) {
					grid[iy][ix].display(ix, iy, g2d);
				}
			}
		}
	}
}
