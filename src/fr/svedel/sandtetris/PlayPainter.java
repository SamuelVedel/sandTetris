package fr.svedel.sandtetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class PlayPainter extends JPanel{
	/**
	 * Gap between the top of the grid (or the bottom)
	 * and the top of the screen (or the bottom)
	 */
	public static final int GRID_GAP = 100;
	
	private Play play;
	
	private double transX, transY;
	private int heightForNoScale;
	private double scale;
	
	public PlayPainter(Play play) {
		this.play = play;
	}
	
	public double getTransX() {
		return transX;
	}
	
	public double getTransY() {
		return transY;
	}
	
	public void setHeightForNoScale(Grid grid) {
		heightForNoScale = grid.getHeight()+2*GRID_GAP;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.DARK_GRAY.darker().darker());
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		Grid grid = play.getGrid();
		int gridW = grid.getWidth();
		int gridH = grid.getHeight();
		
		scale = getHeight()/(double)heightForNoScale;
		
		transX = getWidth()/2-gridW/2*scale;
		transY = getHeight()/2-gridH/2*scale;
		
		BufferedImage gridImg = new BufferedImage(gridW, gridH,
												   BufferedImage.TYPE_INT_RGB);
		Graphics2D g2dgrid = gridImg.createGraphics();
		grid.display(g2dgrid);
		
		g2d.drawImage(gridImg, (int)transX, (int)transY,
					  (int)(gridW*scale), (int)(gridH*scale), null);
	}
}
