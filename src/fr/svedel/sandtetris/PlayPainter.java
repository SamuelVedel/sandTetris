package fr.svedel.sandtetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class PlayPainter extends JPanel{
	/**
	 * Gap between the top of the grid (or the bottom)
	 * and the top of the screen (or the bottom)
	 */
	public static final int GRID_GAP = 2*Piece.CUBE_HEIGHT;
	
	private Play play;
	
	private double transX, transY;
	private int heightForNoScale;
	private double scale;
	
	public PlayPainter(Play play) {
		this.play = play;
	}
	
		/**
	 * Retourne la largeur d'un texte
	 * 
	 * @param text texte en question
	 * @param g2d <code>Graphics2D</code> qui sert à connaitre
	 * le <code>Font</code> à étudier
	 * @return la largeur du texte
	 */
	public static int getTextW(String text, Graphics2D g2d) {
		FontMetrics fm = g2d.getFontMetrics();
		Rectangle2D textBounds = fm.getStringBounds(text, g2d);
		return (int) textBounds.getWidth();
	}
	
	/**
	 * <p>Retourne la hauteur d'un texte, mais à un décalge par
	 * rapport à la réalité <br>
	 * il faut avec la police Arial enlevé la taille de la police*20/50.</p>
	 * 
	 * <p>J'aimerais bien faire des test pour plein de police histoie de tout avoir
	 * facilement</p>
	 * 
	 * @param text texte en question
	 * @param g2d <code>Graphics2D</code> qui sert à connaitre
	 * le <code>Font</code> à étudier
	 * @return la hauteur du texte
	 */
	public static int getTextH(String text, Graphics2D g2d) {
		FontMetrics fm = g2d.getFontMetrics();
		Rectangle2D textBounds = fm.getStringBounds(text, g2d);
		return (int) textBounds.getHeight()-g2d.getFont().getSize()*20/50;
	}
	
	public static void drawString(String text, int x, int y, Graphics2D g2d) {
		Graphics2D g2d2 = (Graphics2D) g2d.create();
		g2d2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
							  RenderingHints.VALUE_ANTIALIAS_ON);
		g2d2.drawString(text, x, y);
		g2d2.dispose();
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
		
		String str = "Score: "+play.getScore();
		g2dgrid.setFont(new Font("ARIAL", Font.BOLD, 50));
		int strW = getTextW(str, g2dgrid);
		int strH = getTextH(str, g2dgrid);
		g2dgrid.setColor(Color.WHITE);
		drawString(str, nextPieceX+pieceW/2-strW/2, nextPieceY-10-strH, g2dgrid);
		
		g2d.drawImage(gridImg, 0, 0,
					  getWidth(), getHeight(), null);
		
		StartMenu startM = play.getStartMenu();
		startM.adjust(getWidth(), getHeight());
		startM.display(g2d);
		
		SettingsMenu settM = play.getSettingsMenu();
		settM.adjust(getWidth(), getHeight());
		settM.display(g2d);
		
		PauseMenu pauseM = play.getPauseMenu();
		pauseM.adjust(getWidth(), getHeight());
		pauseM.display(g2d);
	}
}
