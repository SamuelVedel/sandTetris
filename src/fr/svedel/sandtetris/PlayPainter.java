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
		
		Grid grid = play.getGrid();
		int gridW = grid.getWidth();
		int gridH = grid.getHeight();
		
		Piece piece = play.getPiece();
		Piece nextPiece = play.getNextPiece();
		int pieceW = Piece.WIDTH;
		int pieceH = Piece.HEIGHT;
		int cubeW = Piece.CUBE_WIDTH;
		int cubeH = Piece.CUBE_HEIGHT;
		
		scale = getHeight()/(double)heightForNoScale;
		
		transX = getWidth()/2-gridW/2*scale;
		transY = getHeight()/2-gridH/2*scale;
		
		BufferedImage gridImg = new BufferedImage((int)(getWidth()/scale),
												  (int)(getHeight()/scale),
												   BufferedImage.TYPE_INT_RGB);
		Graphics2D g2dgrid = gridImg.createGraphics();
		g2dgrid.setColor(Color.DARK_GRAY.darker().darker());
		g2dgrid.fillRect(0, 0, gridImg.getWidth(), gridImg.getHeight());
		g2dgrid.translate(transX/scale, transY/scale);
		grid.display(g2dgrid);
		if (piece != null) piece.display(g2dgrid);
		
		int nextPieceX = gridW+2*cubeW;
		int nextPieceY = 4*cubeH;
		g2dgrid.setColor(Color.DARK_GRAY.darker());
		g2dgrid.fillRect(nextPieceX, nextPieceY,
						 pieceW, pieceH);
		if (nextPiece != null) nextPiece.display(nextPieceX, nextPieceY, g2dgrid);
		
		g2d.drawImage(gridImg, 0, 0,
					  getWidth(), getHeight(), null);
	}
}
