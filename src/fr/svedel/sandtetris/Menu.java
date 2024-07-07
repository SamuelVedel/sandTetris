package fr.svedel.sandtetris;

import java.awt.Color;
import java.awt.Graphics2D;

import fr.svedel.vcomponent.VAdjustInt;
import fr.svedel.vcomponent.VComponent;
import fr.svedel.vcomponent.VPanel;

public abstract class Menu extends VPanel {
	
	private boolean active = false;
	
	//private VPanel vp;
	
	private double percentOnScreen = 0;
	private double v = 0;
	private static final double a = 0.5;
	
	public Menu(int widthReference, int heightReference) {
		super(0, -heightReference, widthReference, heightReference,
			 widthReference, heightReference);
		setAdjustment(VPanel.ADJUSTMENT_BY_HEIGHT);
		setAlignment(VPanel.CENTER_ALIGNMENT);
		updateY();
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
		v = 0;
	}
	
	public void setFullyOnScreen() {
		percentOnScreen = 100;
		updateY();
	}
	
	public void setFullyOffScreen() {
		percentOnScreen = 0;
		updateY();
	}
	
	public void move() {
		percentOnScreen += v;
		if (isActive()) {
			if (percentOnScreen < 100) {
				v += a;
			} else if (percentOnScreen > 100){
				percentOnScreen = 100;
			}
		} else {
			if (percentOnScreen > 0) {
				v -= a;
			} else if (percentOnScreen < 0) {
				percentOnScreen = 0;
			}
		}
		updateY();
	}
	
	public void updateY() {
		int newY = (int)(-(100-percentOnScreen)*getHeight().getValue()/100);
		getY().setValue(newY);
	}
	
	public void display(Graphics2D g2d) {
		// background
		//int currentX = getX().getCurrentValue();
		int currentY = getY().getCurrentValue();
		int currentWRef = getReferenceWidth().getCurrentValue();
		int currentHRef = getReferenceHeight().getCurrentValue();
		g2d.setColor(new Color(255, 255, 255, 50));
		g2d.fillRect(0, currentY, currentWRef, currentHRef);
		
		super.display(g2d);
	}
}
