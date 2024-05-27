package fr.svedel.sandtetris;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Play {
	private VFrame vf = new VFrame("Sand tetris");
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private int fps = 60;
	
	private PlayPainter playP = new PlayPainter(this);
	
	private Grid grid = new Grid();
	private Piece piece;
	private Piece nextPiece;
	
	private KeyListener kl = new KeyListener() {
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				switch (keyCode) {
				case KeyEvent.VK_F11:
					vf.toggleFullScreen();
					break;
				case KeyEvent.VK_ENTER:
				case KeyEvent.VK_UP:
				case KeyEvent.VK_Z:
					piece.pressRotate();
					break;
				case KeyEvent.VK_Q:
				case KeyEvent.VK_LEFT:
					piece.pressLeft();
					break;
				case KeyEvent.VK_D:
				case KeyEvent.VK_RIGHT:
					piece.pressRight();
					break;
				case KeyEvent.VK_S:
				case KeyEvent.VK_DOWN:
					piece.pressDown();
					break;
				}
			}
			
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				switch (keyCode) {
				case KeyEvent.VK_Q:
				case KeyEvent.VK_LEFT:
					piece.unpressLeft();
					break;
				case KeyEvent.VK_D:
				case KeyEvent.VK_RIGHT:
					piece.unpressRight();
					break;
				}
			}
			
			public void keyTyped(KeyEvent e) {}
		};
	
	public Play() {
		initVf();
		initPlayP();
		initPiece();
		run();
	}
	
	private void initVf() {
		vf.setFullScreen(true);
		vf.addKeyListener(kl);
		vf.setContentPane(playP);
		vf.setVisible(true);
	}
	
	private void initPlayP() {
		playP.setHeightForNoScale(grid);
	}
	
	private void initPiece() {
		Random rand = new Random();
		piece = nextPiece;
		int iPStates = rand.nextInt(PreStates.values().length);
		int iColor = rand.nextInt(Grain.NUM_COLORS);
		nextPiece = new Piece(PreStates.values()[iPStates], iColor, grid);
		if (piece == null) initPiece();
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public Piece getNextPiece() {
		return nextPiece;
	}
	
	private void run() {
		while (true) {
			grid.update();
			if (piece.move()) {
				initPiece();
			}
			vf.repaint();
			toolkit.sync();
			try {
				Thread.sleep(1000/fps);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
