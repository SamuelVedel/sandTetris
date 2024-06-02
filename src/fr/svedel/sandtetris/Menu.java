package fr.svedel.sandtetris;

import java.awt.Color;
import java.awt.Graphics2D;

import fr.svedel.vcomponent.VPanel;

public abstract class Menu {
	
	private boolean active = false;
	
	private VPanel vp;
	
	private double percentOnScreen = 0;
	private double v = 0;
	private static final double a = 0.5;
	
	public Menu(int widthReference, int heightReference) {
		vp = new VPanel(0, -heightReference, widthReference, heightReference,
						widthReference, heightReference);
		vp.setAdjustment(VPanel.ADJUSTMENT_BY_WIDTH_AND_HEIGHT);
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
		int newY = (int)(-(100-percentOnScreen)*vp.getHeight().getValue()/100);
		vp.getY().setValue(newY);
	}
	
	public void adjust(int widthReference, int heightReference) {
		vp.adjust(widthReference, heightReference);
	}
	
	public void display(Graphics2D g2d) {
		// background
		//int currentX = vp.getX().getCurrentValue();
		int currentY = vp.getY().getCurrentValue();
		int currentWRef = vp.getWidthReference().getCurrentValue();
		int currentHRef = vp.getHeightReference().getCurrentValue();
		g2d.setColor(new Color(255, 255, 255, 50));
		g2d.fillRect(0, currentY, currentWRef, currentHRef);
		
		vp.display(g2d);
	}
}
