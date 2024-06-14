package fr.svedel.sandtetris;

import java.awt.Color;

import fr.svedel.vcomponent.VButton;

public class SandButton extends VButton {
	
	public static final int WIDTH = 100;
	public static final int HEIGHT = 50;
	
	public SandButton(int w, int h, String text) {
		super(w, h, text);
		initStyle();
	}
	
	public SandButton(String text) {
		this(WIDTH, HEIGHT, text);
	}
	
	private void initStyle() {
		getRound().setValue(0);
		getFontSize().setValue(20);
		getBorderWidth().setValue(2);
		setBackground(new Color(255, 255, 255, 200));
		setForeground(new Color(0, 0, 0, 200));
		setBorder(new Color(0, 0, 0, 200));
		setSurvolBackground(new Color(0, 0, 0, 200));
		setSurvolForeground(new Color(255, 255, 255, 200));
		setSurvolBorder(new Color(255, 255, 255, 200));
	}
}
