package fr.svedel.sandtetris;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class Grid {
	public int nRow = 20*Piece.CUBE_N_ROW;
	public int nCol = 10*Piece.CUBE_N_COL;
	
	public Grain[][] grid;
	
	public Grid() {
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
	
	public void putPiece(Piece piece) {
		Grain[][] state = piece.getState();
		int ix = piece.getIx();
		int iy = piece.getIy();
		for (int iy2 = 0; iy2 < state.length; ++iy2) {
			for (int ix2 = 0; ix2 < state.length; ++ix2) {
				int gix = ix+ix2;
				int giy = iy+iy2;
				if (state[iy2][ix2] != null) {
					grid[giy][gix] = state[iy2][ix2];
				}
			}
		}
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
					Grain gr = grid[iy][ix];
					grid[iy][ix] = null;
					grid[newCoords[1]][newCoords[0]] = gr;
				}
			}
		}
		deletLines();
	}
	
	public void deletLines() {
		int oldColor = -1;
		for (int iy = 0; iy < nRow; ++iy) {
			if (!isEmpty(0, iy))  {
				int color = grid[iy][0].getColor();
				if (oldColor != color) {
					ArrayList<Integer[]> connexComponent = new ArrayList<>();
					if (getConnexComponent(0, iy, color, connexComponent)) {
						for (Integer[] coords: connexComponent) {
							grid[coords[1]][coords[0]] = null;
						}
					}
					oldColor = color;
				}
			} else {
				oldColor = -1;
			}
		}
	}
	
	private int indexInAlOfCoords(ArrayList<Integer[]> al, int[] coords) {
		for (int i = 0; i < al.size(); ++i) {
			if (al.get(i)[0] == coords[0] && al.get(i)[1] == coords[1]) {
				return i;
			}
		}
		return -1;
	}
	
	private boolean getConnexComponent(int ix, int iy, int color,
									   ArrayList<Integer[]> connexComponent) {
		if (!isVoid(ix, iy) && !isEmpty(ix, iy)
			&& grid[iy][ix].getColor() == color
			&& indexInAlOfCoords(connexComponent, new int[] {ix, iy}) < 0) {
			connexComponent.add(new Integer[] {ix, iy});
		} else {
			return false;
		}
		boolean crosses = false;
		crosses = getConnexComponent(ix-1, iy, color, connexComponent) || crosses;
		crosses = getConnexComponent(ix, iy-1, color, connexComponent) || crosses;
		crosses = getConnexComponent(ix+1, iy, color, connexComponent) || crosses;
		crosses = getConnexComponent(ix, iy+1, color, connexComponent) || crosses;
		return crosses || ix == nCol-1;
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
