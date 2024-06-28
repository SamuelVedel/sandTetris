package fr.svedel.sandtetris;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Grain {
	public static final int WIDTH = 10;
	public static final int HEIGHT = WIDTH;
	
	private Grid grid;

	public static final int GRAY = 0;
	public static final int BLUE = 1;
	public static final int RED = 2;
	public static final int YELLOW = 3;
	public static final int PURPLE = 4;
	public static final int GREEN = 5;
	
	public static final int MAX_NUM_COLORS = 5;
	//public static final int MAX_NUM_CURSED_COLORS = 2;
	
	public static final int SAND_TYPE = 0;
	public static final int STONE_TYPE = 1;
	public static final int WATER_TYPE = 2;
	
	// for water
	private static final int NO_DIRECTION = 0;
	private static final int LEFT_DIRECTION = -1;
	private static final int RIGHT_DIRECTION = 1;
	
	/** Direction for water grain */
	private int direction = NO_DIRECTION;
	
	/** Delay before a sand grain go in diagonal */
	private static final int DIAG_DELAY = 1;
	/** Time the sand gain has wated for going in diagonal */
	private int diagTime = 1;
	
	private int color;
	private int type;
	private Color displayColor;
	
	public Grain(int type, int color, Color displayColor, Grid grid) {
		this.type = type;
		this.grid = grid;
		this.color = color;
		this.displayColor = displayColor;
	}
	
	public Grain(int color, Color displayColor, Grid grid) {
		this(SAND_TYPE, color, displayColor, grid);
	}
	
	public int getColor() {
		return color;
	}
	
	public int[] update(int ix, int iy) {
		switch (type) {
		case STONE_TYPE:
			return new int[] {ix, iy};
		case WATER_TYPE:
			return waterUpdate(ix, iy);
		default:
			return sandUpdate(ix, iy);
		}
	}
	
	private int[] sandUpdate(int ix, int iy) {
		if (!grid.isVoid(ix, iy+1)) {
			if (grid.isEmpty(ix, iy+1)) {
				return new int[] {ix, iy+1};
			}
			
			// at least one diag
			if ((!grid.isVoid(ix-1, iy+1) && grid.isEmpty(ix-1, iy+1)) // left diag
				|| (!grid.isVoid(ix+1, iy+1) && grid.isEmpty(ix+1, iy+1))) { // right diag
				if (diagTime%DIAG_DELAY != 0) {
					++diagTime;
					return new int[] {ix, iy};
				}
				diagTime = 1;
				// not left diag
				if (grid.isVoid(ix-1, iy+1) || !grid.isEmpty(ix-1, iy+1)) {
					return new int[] {ix+1, iy+1};
				}
				// not right diag
				if (grid.isVoid(ix+1, iy+1) || !grid.isEmpty(ix+1, iy+1)) {
					return new int[] {ix-1, iy+1};
				}
				// both diag
				Random rand = new Random();
				int newX = (rand.nextBoolean()? ix+1: ix-1);
				return new int[] {newX, iy+1};
			} else diagTime = 1;
			
			/*if (!grid.isVoid(ix-1, iy+1) && !grid.isVoid(ix+1, iy+1)
				&& grid.isEmpty(ix-1, iy+1) && grid.isEmpty(ix+1, iy+1)) {
				Random rand = new Random();
				int newX = (rand.nextBoolean()? ix+1: ix-1);
				return new int[] {newX, iy+1};
			}
			if (!grid.isVoid(ix-1, iy+1) && grid.isEmpty(ix-1, iy+1)) {
				return new int[] {ix-1, iy+1};
			}
			if (!grid.isVoid(ix+1, iy+1) && grid.isEmpty(ix+1, iy+1)) {
				return new int[] {ix+1, iy+1};
				}*/
		}
		return new int[] {ix, iy};
	}
	
	private int[] waterUpdate(int ix, int iy) {
		if (!grid.isVoid(ix, iy+1)) {
			if (grid.isEmpty(ix, iy+1)) {
				return new int[] {ix, iy+1};
			}
			if (!grid.isVoid(ix-1, iy+1) && !grid.isVoid(ix+1, iy+1)
				&& grid.isEmpty(ix-1, iy+1) && grid.isEmpty(ix+1, iy+1)) {
				Random rand = new Random();
				int newX = (rand.nextBoolean()? ix+1: ix-1);
				return new int[] {newX, iy+1};
			}
			if (!grid.isVoid(ix-1, iy+1) && grid.isEmpty(ix-1, iy+1)) {
				return new int[] {ix-1, iy+1};
			}
			if (!grid.isVoid(ix+1, iy+1) && grid.isEmpty(ix+1, iy+1)) {
				return new int[] {ix+1, iy+1};
			}
		}
		if (!grid.isVoid(ix-1, iy) && !grid.isVoid(ix+1, iy)
			&& grid.isEmpty(ix-1, iy) && grid.isEmpty(ix+1, iy)) {
			Random rand = new Random();
			int newX = ix+direction;
			if (direction == NO_DIRECTION) {
				if (rand.nextBoolean()) {
					newX = ix+1;
					direction = RIGHT_DIRECTION;
				} else {
					newX = ix-1;
					direction = LEFT_DIRECTION;
				}
			}
			return new int[] {newX, iy};
		} else if (!grid.isVoid(ix-1, iy) && grid.isEmpty(ix-1, iy)) {
			direction = LEFT_DIRECTION;
			return new int[] {ix-1, iy};
		} else if (!grid.isVoid(ix+1, iy) && grid.isEmpty(ix+1, iy)) {
			direction = RIGHT_DIRECTION;
			return new int[] {ix+1, iy};
		}
		return new int[] {ix, iy};
	}
	
	public void display(int ix, int iy, Graphics2D g2d) {
		g2d.setColor(displayColor);
		g2d.fillRect(ix*WIDTH, iy*HEIGHT, WIDTH, HEIGHT);
	}
}
