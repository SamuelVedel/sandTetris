package fr.svedel.sandtetris;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Grain {
	public static final int WIDTH = 10;
	public static final int HEIGHT = WIDTH;
	
	private Grid grid;
	
	public static final int RED = 0;
	public static final int GREEN = -1;
	public static final int YELLOW = 1;
	public static final int PURPLE = 2;
	public static final int BLUE = 3;
	public static final int GRAY = 4;
	public static final int NUM_COLORS = 5;
	public static final int NUM_CURSED_COLORS = 2;
	
	private static final int NO_DIRECTION = 0;
	private static final int LEFT_DIRECTION = -1;
	private static final int RIGHT_DIRECTION = 1;
	
	private int direction = NO_DIRECTION;
	
	private int color;
	private Color displayColor;
	
	public Grain(int color, Color displayColor, Grid grid) {
		this.grid = grid;
		this.color = color;
		this.displayColor = displayColor;
	}
	
	public int getColor() {
		return color;
	}
	
	public int[] update(int ix, int iy) {
		if (color == GRAY) {
			return new int[] {ix, iy};
		}
		if (!grid.isVoid(ix, iy+1)) {
			if (grid.isEmpty(ix, iy+1)) {
				return new int[] {ix, iy+1};
			} else if (!grid.isVoid(ix-1, iy+1) && !grid.isVoid(ix+1, iy+1)
					   && grid.isEmpty(ix-1, iy+1) && grid.isEmpty(ix+1, iy+1)) {
				Random rand = new Random();
				int newX = (rand.nextBoolean()? ix+1: ix-1);
				return new int[] {newX, iy+1};
			} else if (!grid.isVoid(ix-1, iy+1) && grid.isEmpty(ix-1, iy+1)) {
				return new int[] {ix-1, iy+1};
			} else if (!grid.isVoid(ix+1, iy+1) && grid.isEmpty(ix+1, iy+1)) {
				return new int[] {ix+1, iy+1};
			}
			// this is for fun and water simulation
			else if (color == BLUE) {
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
			}
		}
		return new int[] {ix, iy};
	}
	
	public void display(int ix, int iy, Graphics2D g2d) {
		g2d.setColor(displayColor);
		g2d.fillRect(ix*WIDTH, iy*HEIGHT, WIDTH, HEIGHT);
	}
}
