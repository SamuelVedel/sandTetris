package fr.svedel.sandtetris;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;

import fr.svedel.vcomponent.VAdjustInt;
import fr.svedel.vcomponent.VButton;
import fr.svedel.vcomponent.VLabel;
import fr.svedel.vcomponent.VNumberWriter;
import fr.svedel.vcomponent.VPanel;
import fr.svedel.vcomponent.VSwitch;

public class SettingsMenu extends Menu {
	
	private static final int COMPONENT_GAP = 3;
	private static final int BORDER_GAP = 10;
	private static final int FONT_SIZE = 10;
	
	private static final int PANEL_GAP = 15;
	private static final int PANEL_WIDTH = 150;
	
	private static final int WRITER_HEIGHT = 15;
	private static final int SWITCH_HEIGHT = 15;
	
	private static final int BUTTON_WIDTH = 50;
	private static final int BUTTON_HEIGHT = 25;
	
	private static final Color backgroundColor = new Color(255, 255, 255);
	private static final Color foregroundColor = new Color(0, 0, 0);
	private static final Color alphaBackgroundColor = new Color(255, 255, 255, 200);
	private static final Color alphaForegroundColor = new Color(0, 0, 0, 200);
	
	private SettingsPanel dimPanel = new SettingsPanel(/*PANEL_WIDTH, 0*/);
	private VLabel nrowLabel = new VLabel("n row");
	private VNumberWriter nrowWriter = new VNumberWriter(WRITER_HEIGHT);
	private VLabel ncolLabel = new VLabel("n col");
	private VNumberWriter ncolWriter = new VNumberWriter(WRITER_HEIGHT);
	
	private SettingsPanel ncolorsPanel = new SettingsPanel();
	private VLabel ncolorsLabel = new VLabel("n colors");
	private VNumberWriter ncolorsWriter = new VNumberWriter(WRITER_HEIGHT);
	
	private SettingsPanel stonePanel = new SettingsPanel();
	private VLabel stoneLabel = new VLabel("Stone");
	private VSwitch stoneSwitch = new VSwitch(SWITCH_HEIGHT);
	private VLabel stonePerLabel = new VLabel("%");
	private VNumberWriter stoneWriter = new VNumberWriter(WRITER_HEIGHT);
	
	private SettingsPanel waterPanel = new SettingsPanel();
	private VLabel waterLabel = new VLabel("Water");
	private VSwitch waterSwitch = new VSwitch(SWITCH_HEIGHT);
	private VLabel waterPerLabel = new VLabel("%");
	private VNumberWriter waterWriter = new VNumberWriter(WRITER_HEIGHT);
	
	private SandButton okButt = new SandButton(BUTTON_WIDTH, BUTTON_HEIGHT, "Ok");
	private SandButton cancelButt = new SandButton(BUTTON_WIDTH, BUTTON_HEIGHT, "Cancel");
	
	public SettingsMenu() {
		super(800, 500);
		initVComponents();
	}
	
	private void initVComponents() {
		// this image is used to get a graphics2D that is used
		// to set the labels' dimensions
		BufferedImage bi = new BufferedImage(this.getWidth().getValue(),
											 this.getHeight().getValue(),
											 BufferedImage.TYPE_INT_RGB);
		Graphics2D g2dbi = bi.createGraphics();
		initDimPanel(g2dbi);
		initNColorsPanel(g2dbi);
		initStonePanel(g2dbi);
		initWaterPanel(g2dbi);
		initButtons();
		g2dbi.dispose();
	}
	
	private void initDimPanel(Graphics2D g2d) {
		int refWidth = getReferenceWidth().getValue();
		int refHeight = getReferenceHeight().getValue();
		add(dimPanel);
		dimPanel.add(nrowLabel);
		dimPanel.add(nrowWriter);
		dimPanel.add(ncolLabel);
		dimPanel.add(ncolWriter);
		dimPanel.getX().setValue(refWidth/2-dimPanel.getWidth().getValue()/2);
		dimPanel.getY().setValue(PANEL_GAP);
		
		int pw = dimPanel.getWidth().getValue();
		
		// to set the labels' dimensions
		nrowLabel.getFontSize().setValue(FONT_SIZE);
		nrowLabel.display(g2d);
		ncolLabel.getFontSize().setValue(FONT_SIZE);
		ncolLabel.display(g2d);
		
		nrowLabel.setColor(foregroundColor);
		nrowLabel.getX().setValue(pw/3-nrowLabel.getWidth().getValue()/2);
		nrowLabel.getY().setValue(BORDER_GAP);
		
		ncolLabel.setColor(foregroundColor);
		ncolLabel.getX().setValue(2*pw/3-nrowLabel.getWidth().getValue()/2);
		ncolLabel.getY().setValue(BORDER_GAP);
		
		nrowWriter.setBackground(backgroundColor);
		nrowWriter.setForeground(foregroundColor);
		nrowWriter.setMaximumNumber(20);
		nrowWriter.getX().setValue(pw/3-nrowWriter.getWidth().getValue()/2);
		nrowWriter.getY().setValue(nrowLabel.getY().getValue()
								   +nrowLabel.getHeight().getValue()
								   +COMPONENT_GAP);
		
		ncolWriter.setBackground(backgroundColor);
		ncolWriter.setForeground(foregroundColor);
		ncolWriter.setMaximumNumber(20);
		ncolWriter.getX().setValue(2*pw/3-ncolWriter.getWidth().getValue()/2);
		ncolWriter.getY().setValue(ncolLabel.getY().getValue()
								   +ncolLabel.getHeight().getValue()
								   +COMPONENT_GAP);
		
		dimPanel.getHeight().setValue(2*BORDER_GAP+COMPONENT_GAP
									  +nrowLabel.getHeight().getValue()
									  +nrowWriter.getHeight().getValue()
									  );
		dimPanel.updateSonsReferences();
	}
	
	private void initNColorsPanel(Graphics2D g2d) {
		add(ncolorsPanel);
		ncolorsPanel.add(ncolorsLabel);
		ncolorsPanel.add(ncolorsWriter);
		
		ncolorsPanel.getX().setValue(getWidth().getValue()/2-PANEL_WIDTH/2);
		ncolorsPanel.getY().setValue(dimPanel.getY().getValue()
									 +dimPanel.getHeight().getValue()
									 +PANEL_GAP);
		int pw = ncolorsPanel.getWidth().getValue();
		
		ncolorsLabel.display(g2d);
		
		ncolorsWriter.setBackground(backgroundColor);
		ncolorsWriter.setForeground(foregroundColor);
		ncolorsWriter.setMaximumNumber(Grain.MAX_NUM_COLORS);
		ncolorsWriter.getX().setValue(pw/2+COMPONENT_GAP/2);
		ncolorsWriter.getY().setValue(BORDER_GAP);
		
		ncolorsLabel.setColor(foregroundColor);
		ncolorsLabel.getX().setValue(pw/2-COMPONENT_GAP/2
									 -ncolorsLabel.getWidth().getValue());
		ncolorsLabel.getY().setValue(ncolorsWriter.getY().getValue()
									 +ncolorsWriter.getHeight().getValue()/2
									 -ncolorsLabel.getHeight().getValue()/2);
		
		ncolorsPanel.getHeight().setValue(2*BORDER_GAP
										  +ncolorsWriter.getHeight().getValue());
		ncolorsPanel.updateSonsReferences();
	}
	
	private void initStonePanel(Graphics2D g2d) {
		int refWidth = getReferenceWidth().getValue();
		int refHeight = getReferenceHeight().getValue();
		add(stonePanel);
		stonePanel.add(stoneLabel);
		stonePanel.add(stoneSwitch);
		stonePanel.add(stonePerLabel);
		stonePanel.add(stoneWriter);
		
		stonePanel.getX().setValue(refWidth/2-stonePanel.getWidth().getValue()/2);
		stonePanel.getY().setValue(ncolorsPanel.getY().getValue()
								   +ncolorsPanel.getHeight().getValue()
								   +PANEL_GAP);
		
		int pw = stonePanel.getWidth().getValue();
		
		// to set the labels' dimensions
		stoneLabel.getFontSize().setValue(FONT_SIZE);
		stoneLabel.display(g2d);
		stonePerLabel.getFontSize().setValue(FONT_SIZE);
		stonePerLabel.display(g2d);
		
		stoneSwitch.getX().setValue(pw/2+COMPONENT_GAP/2);
		stoneSwitch.getY().setValue(BORDER_GAP);
		stoneLabel.setColor(foregroundColor);
		stoneLabel.getX().setValue(pw/2-COMPONENT_GAP/2-stoneLabel.getWidth().getValue());
		stoneLabel.getY().setValue(stoneSwitch.getY().getValue()
								   +stoneSwitch.getHeight().getValue()/2
								   -stoneLabel.getHeight().getValue()/2);
		
		stoneWriter.setBackground(backgroundColor);
		stoneWriter.setForeground(foregroundColor);
		stoneWriter.setMaximumNumber(100);
		stoneWriter.getX().setValue(pw/2+COMPONENT_GAP/2);
		stoneWriter.getY().setValue(stoneSwitch.getY().getValue()
									+stoneSwitch.getHeight().getValue()
									+COMPONENT_GAP);
		stonePerLabel.setColor(foregroundColor);
		stonePerLabel.getX().setValue(pw/2-COMPONENT_GAP/2-stonePerLabel.getWidth().getValue());
		stonePerLabel.getY().setValue(stoneWriter.getY().getValue()
								   +stoneWriter.getHeight().getValue()/2
								   -stonePerLabel.getHeight().getValue()/2);
		
		stonePanel.getHeight().setValue(2*BORDER_GAP
									  +COMPONENT_GAP
									  +stoneSwitch.getHeight().getValue()
									  +stoneWriter.getHeight().getValue());
		stonePanel.updateSonsReferences();
	}
	
	private void initWaterPanel(Graphics2D g2d) {
		int refWidth = getReferenceWidth().getValue();
		int refHeight = getReferenceHeight().getValue();
		add(waterPanel);
		waterPanel.add(waterLabel);
		waterPanel.add(waterSwitch);
		waterPanel.add(waterPerLabel);
		waterPanel.add(waterWriter);
		
		waterPanel.getX().setValue(refWidth/2-waterPanel.getWidth().getValue()/2);
		waterPanel.getY().setValue(stonePanel.getY().getValue()
								   +stonePanel.getHeight().getValue()
								   +PANEL_GAP);
		
		int pw = waterPanel.getWidth().getValue();
		
		// to set the labels' dimensions
		waterLabel.getFontSize().setValue(FONT_SIZE);
		waterLabel.display(g2d);
		waterPerLabel.getFontSize().setValue(FONT_SIZE);
		waterPerLabel.display(g2d);
		
		waterSwitch.getX().setValue(pw/2+COMPONENT_GAP/2);
		waterSwitch.getY().setValue(BORDER_GAP);
		waterLabel.setColor(foregroundColor);
		waterLabel.getX().setValue(pw/2-COMPONENT_GAP/2-waterLabel.getWidth().getValue());
		waterLabel.getY().setValue(waterSwitch.getY().getValue()
								   +waterSwitch.getHeight().getValue()/2
								   -waterLabel.getHeight().getValue()/2);
		
		waterWriter.setBackground(backgroundColor);
		waterWriter.setForeground(foregroundColor);
		waterWriter.setMaximumNumber(100);
		waterWriter.getX().setValue(pw/2+COMPONENT_GAP/2);
		waterWriter.getY().setValue(waterSwitch.getY().getValue()
									+waterSwitch.getHeight().getValue()
									+COMPONENT_GAP);
		waterPerLabel.setColor(foregroundColor);
		waterPerLabel.getX().setValue(pw/2-COMPONENT_GAP/2-waterPerLabel.getWidth().getValue());
		waterPerLabel.getY().setValue(waterWriter.getY().getValue()
								   +waterWriter.getHeight().getValue()/2
								   -waterPerLabel.getHeight().getValue()/2);
		
		waterPanel.getHeight().setValue(2*BORDER_GAP
									  +COMPONENT_GAP
									  +waterSwitch.getHeight().getValue()
									  +waterWriter.getHeight().getValue());
		waterPanel.updateSonsReferences();
	}
	
	private void initButtons() {
		int refWidth = getReferenceWidth().getValue();
		int refHeight = getReferenceHeight().getValue();
		add(okButt);
		add(cancelButt);
		
		okButt.getFontSize().setValue(FONT_SIZE);
		okButt.getBorderWidth().setValue(1);
		okButt.getX().setValue(refWidth/2-PANEL_GAP/2
							   -okButt.getWidth().getValue());
		okButt.getY().setValue(waterPanel.getY().getValue()
							   +waterPanel.getHeight().getValue()
							   +PANEL_GAP);
		
		cancelButt.getBorderWidth().setValue(1);
		cancelButt.getFontSize().setValue(FONT_SIZE);
		cancelButt.getX().setValue(refWidth/2+PANEL_GAP/2);
		cancelButt.getY().setValue(waterPanel.getY().getValue()
								   +waterPanel.getHeight().getValue()
								   +PANEL_GAP);
	}
	
	public void setUsable(boolean usable) {
		nrowWriter.setFocusable(usable);
		ncolWriter.setFocusable(usable);
		ncolorsWriter.setFocusable(usable);
		stoneWriter.setFocusable(usable);
		waterWriter.setFocusable(usable);
		
		stoneSwitch.setUsable(usable);
		waterSwitch.setUsable(usable);
		okButt.setUsable(usable);
		cancelButt.setUsable(usable);
	}
	
	private class SettingsPanel extends VPanel {
		
		private VAdjustInt borderWidth = new VAdjustInt(2);
		
		public SettingsPanel() {
			super(PANEL_WIDTH, 10);
		}
		
		@Override
		public void adjust(int referenceWidth, int referenceHeight) {
			super.adjust(referenceWidth, referenceHeight);
			adjustValue(borderWidth);
		}
		
		@Override
		public void display(Graphics2D g2d) {
			int currentX = getX().getCurrentValue();
			int currentY = getY().getCurrentValue();
			int currentW = getWidth().getCurrentValue();
			int currentH = getHeight().getCurrentValue();
			
			g2d.setColor(alphaBackgroundColor);
			g2d.fillRect(currentX, currentY, currentW, currentH);
			
			g2d.setColor(alphaForegroundColor);
			g2d.setStroke(new BasicStroke(borderWidth.getValue()));
			g2d.drawRect(currentX, currentY, currentW, currentH);
			g2d.setStroke(new BasicStroke(1));
			super.display(g2d);
		}
	}
}
