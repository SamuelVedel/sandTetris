package fr.svedel.sandtetris;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Play {
	private VFrame vf = new VFrame("Sand tetris");
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private int fps = 60;
	
	private PlayPainter playP = new PlayPainter(this);
	
	private Grid grid = new Grid();
	
	private KeyListener kl = new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F11) {
					vf.toggleFullScreen();
				}
			}
			
			public void keyReleased(KeyEvent e) {}
			
			public void keyTyped(KeyEvent e) {}
		};
	
	public Play() {
		initVf();
		initPlayP();
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
	
	public Grid getGrid() {
		return grid;
	}
	
	private void run() {
		while (true) {
			grid.update();
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
