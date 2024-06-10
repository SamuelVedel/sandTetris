package fr.svedel.sandtetris;

import java.awt.event.MouseEvent;

import fr.svedel.vcomponent.VActionListener;
import fr.svedel.vcomponent.VComponent;
import fr.svedel.vcomponent.VButton;

public class StartMenu extends Menu {
	
	private Play play;
	
	private static final int BUTTON_GAP = 10;
	
	private SandButton startButton = new SandButton("Start");
	private SandButton quitButton = new SandButton("Quit");
	
	private VActionListener val = new VActionListener() {
			public void action(VComponent source, MouseEvent e) {
				if (e.getButton() == 1) {
					if (source == startButton) play.start();
					else if (source == quitButton) play.quit();
				}
			}
		};
	
	public StartMenu(Play play) {
		super(800, 600);
		this.play = play;
		initVComponents();
	}
	
	private void initVComponents() {
		initStartButton();
		initQuitButton();
	}
	
	private void initStartButton() {
		int vbW = startButton.getWidth().getValue();
		int vbH = startButton.getHeight().getValue();
		int jpW = getWidth().getValue();
		int jpH = getHeight().getValue();
		startButton.getX().setValue(jpW/2-vbW/2);
		startButton.getY().setValue(jpH/2-vbH-BUTTON_GAP);
		startButton.addVActionListener(val);
		add(startButton);
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
