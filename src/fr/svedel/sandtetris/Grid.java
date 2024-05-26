package fr.svedel.sandtetris;

import java.awt.Color;
import java.awt.Graphics2D;

public class Grid {
	public int nRow = 20*Piece.CUBE_ROW;
	public int nCol = 10*Piece.CUBE_COL;
	
	public Grain[][] grid;
	
	public Grid() {
		grid = new Grain[nRow][nCol];
		for (int iy = 0; iy < 10; ++iy) {
			for (int ix = 0; ix < 10; ++ix) {
				addGrain(new Grain(Grain.BLUE, Color.BLUE, this), ix, iy);
			}
		}
	}
	
	public int getWidth() {
		return nCol*Grain.WIDTH;
	}
	
	public int getHeight() {
		return nRow*Grain.HEIGHT;
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
	
	public void update() {
		Grain[][] newGrid = new Grain[nRow][nCol];
		for (int iy = 0; iy < grid.length; ++iy) {
			for (int ix = 0; ix < grid[iy].length; ++ix) {
				if (!isEmpty(ix, iy)) {
					int[] newCoords = grid[iy][ix].update(ix, iy);
					newGrid[newCoords[1]][newCoords[0]] = grid[iy][ix];
				}
			}
		}
		grid = newGrid;
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
