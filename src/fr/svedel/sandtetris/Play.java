package fr.svedel.sandtetris;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Play {
	private VFrame vf = new VFrame("Sand tetris");
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private int fps = 60;
	
	public static final int START_PHASE = 0;
	public static final int PLAY_PHASE = 1;
	public static final int PAUSE_PHASE = 2;
	private int phase = START_PHASE;
	
	private int score = 0;
	
	private PlayPainter playP = new PlayPainter(this);
	
	private StartMenu startM = new StartMenu(this);
	
	private Grid grid = new Grid(this);
	private Piece piece;
	private Piece nextPiece;
	
	private boolean startPressed = false;
	private boolean quitPressed = false;
	
	private KeyListener kl = new KeyListener() {
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				switch (keyCode) {
				case KeyEvent.VK_F11:
					vf.toggleFullScreen();
					break;
				case KeyEvent.VK_UP:
				case KeyEvent.VK_Z:
					if (piece != null) piece.pressRotate();
					break;
				case KeyEvent.VK_Q:
				case KeyEvent.VK_LEFT:
					if (piece != null) piece.pressLeft();
					break;
				case KeyEvent.VK_D:
				case KeyEvent.VK_RIGHT:
					if (piece != null) piece.pressRight();
					break;
				case KeyEvent.VK_S:
				case KeyEvent.VK_DOWN:
					if (piece != null) piece.pressDown();
					break;
				case KeyEvent.VK_ENTER:
					if (phase == START_PHASE) startPressed = true;
				case KeyEvent.VK_SPACE:
					if (piece != null) piece.pressFall();
					break;
				case KeyEvent.VK_ESCAPE:
					if (phase == START_PHASE) quitPressed = true;
					break;
				}
			}
			
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				switch (keyCode) {
				case KeyEvent.VK_Q:
				case KeyEvent.VK_LEFT:
					if (piece != null) piece.unpressLeft();
					break;
				case KeyEvent.VK_D:
				case KeyEvent.VK_RIGHT:
					if (piece != null) piece.unpressRight();
					break;
				case KeyEvent.VK_ENTER:
					if (startPressed) {
						if (phase == START_PHASE) start();
						startPressed = false;
					}
					break;
				case KeyEvent.VK_ESCAPE:
					if(quitPressed) {
						if (phase == START_PHASE) quit();
						quitPressed = false;
					}
					break;
				}
			}
			
			public void keyTyped(KeyEvent e) {}
		};
	
	public Play() {
		initVf();
		initStartM();
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
	
	private void initStartM() {
		startM.setActive(true);
		startM.setFullyOnScreen();
		startM.addMlToAComponent(playP);
		startM.addMmlToAComponent(playP);
	}
	
	private void initPlayP() {
		playP.setHeightForNoScale(grid);
	}
	
	private void initPiece() {
		Random rand = new Random();
		piece = nextPiece;
		int iPStates = rand.nextInt(PreStates.values().length);
		int iColor = rand.nextInt(Grain.NUM_COLORS-1);
		if (rand.nextInt(50) == 0) iColor = Grain.GRAY;
		nextPiece = new Piece(PreStates.values()[iPStates], iColor, grid);
		//if (piece == null) initPiece();
	}
	
	public int getScore() {
		return score;
	}
	
	public void increaseScore(int points) {
		score += points;
	}
	
	public int getPhase() {
		return phase;
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
	
	public StartMenu getStartMenu() {
		return startM;
	}
	
	public void start() {
		phase = PLAY_PHASE;
		startM.setActive(false);
		initPiece();
		grid.clear();
	}
	
	public void lose() {
		phase = START_PHASE;
		startM.setActive(true);
	}
	
	public void quit() {
		System.exit(0);
	}
	
	private void run() {
		while (true) {
			startM.move();
			if (phase == PLAY_PHASE) {
				grid.update();
				int moveResult = piece.move();
				if (moveResult == Piece.CORRECTLY_POSED) {
					initPiece();
				} else if (moveResult == Piece.INCORRECTLY_POSED) {
					lose();
				}
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
