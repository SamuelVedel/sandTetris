package fr.svedel.sandtetris;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Grain {
	public static final int WIDTH = 10;
	public static final int HEIGHT = WIDTH;
	
	private Grid grid;
	
	public static final int BLUE = 0;
	public static final int RED = 1;
	public static final int GREEN = 2;
	public static final int YELLOW = 3;
	public static final int PURPLE = 4;
	public static final int GRAY = 5;
	public static final int NUM_COLORS = 6;
	
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
		}
		return new int[] {ix, iy};
	}
	
	public void display(int ix, int iy, Graphics2D g2d) {
		g2d.setColor(displayColor);
		g2d.fillRect(ix*WIDTH, iy*HEIGHT, WIDTH, HEIGHT);
	}
}
