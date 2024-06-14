package fr.svedel.sandtetris;

import java.awt.event.MouseEvent;

import fr.svedel.vcomponent.VActionListener;
import fr.svedel.vcomponent.VComponent;
import fr.svedel.vcomponent.VButton;

public class PauseMenu extends Menu {
	Play play;
	
	private static final int BUTTON_GAP = 10;
	
	private SandButton restartButton = new SandButton("Restart");
	private SandButton quitButton = new SandButton("Quit");
	
	private VActionListener val = new VActionListener() {
			public void action(VComponent source, MouseEvent e) {
				if (e.getButton() == 1) {
					if (source == restartButton) play.restart();
					else if (source == quitButton) play.quit();
				}
			}
		};
	
	public PauseMenu(Play play) {
		super(800, 600);
		this.play = play;
		initVComponents();
	}
	
	private void initVComponents() {
		initRestartButton();
		initQuitButton();
	}
	
	private void initRestartButton() {
		int vbW = restartButton.getWidth().getValue();
		int vbH = restartButton.getHeight().getValue();
		int jpW = getWidth().getValue();
		int jpH = getHeight().getValue();
		restartButton.getX().setValue(jpW/2-vbW/2);
		restartButton.getY().setValue(jpH/2-vbH-BUTTON_GAP);
		restartButton.addVActionListener(val);
		add(restartButton);
	}
	
	private void initQuitButton() {
		int vbW = quitButton.getWidth().getValue();
		int vbH = quitButton.getHeight().getValue();
		int jpW = getWidth().getValue();
		int jpH = getHeight().getValue();
		quitButton.getX().setValue(jpW/2-vbW/2);
		quitButton.getY().setValue(jpH/2+BUTTON_GAP);
		quitButton.addVActionListener(val);
		add(quitButton);
	}
}
