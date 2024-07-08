package fr.svedel.sandtetris;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;

import fr.svedel.vcomponent.VAdjustInt;
import fr.svedel.vcomponent.VButton;
import fr.svedel.vcomponent.VLabel;
import fr.svedel.vcomponent.VPanel;
import fr.svedel.vcomponent.VNumberWriter;

public class SettingsMenu extends Menu {
	
	private static final int COMPONENT_GAP = 3;
	private static final int BORDER_GAP = 10;
	private static final int FONT_SIZE = 10;
	
	private static final int PANEL_GAP = 15;
	private static final int PANEL_WIDTH = 200;
	private static final Color PANEL_COLOR = Color.DARK_GRAY.darker();
	
	private static final int WRITER_HEIGHT = 15;
	
	private SettingsPanel dimPanel = new SettingsPanel(/*PANEL_WIDTH, 0*/);
	private VLabel nrowLabel = new VLabel("n row");
	private VNumberWriter nrowWriter = new VNumberWriter(WRITER_HEIGHT);
	private VLabel ncolLabel = new VLabel("n col");
	private VNumberWriter ncolWriter = new VNumberWriter(WRITER_HEIGHT);
	
	private SettingsPanel ncolorsPanel = new SettingsPanel();
	private VLabel ncolorsLabel = new VLabel("n colors");
	private VNumberWriter ncolorsWriter = new VNumberWriter(WRITER_HEIGHT);
	
	public SettingsMenu() {
		super(800, 600);
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
		
		nrowLabel.getX().setValue(pw/3-nrowLabel.getWidth().getValue()/2);
		nrowLabel.getY().setValue(BORDER_GAP);
		
		ncolLabel.getX().setValue(2*pw/3-nrowLabel.getWidth().getValue()/2);
		ncolLabel.getY().setValue(BORDER_GAP);
		
		nrowWriter.setMaximumNumber(20);
		nrowWriter.getX().setValue(pw/3-nrowWriter.getWidth().getValue()/2);
		nrowWriter.getY().setValue(nrowLabel.getY().getValue()
								   +nrowLabel.getHeight().getValue()
								   +COMPONENT_GAP);
		
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
		int ph = ncolorsPanel.getHeight().getValue();
		
		ncolorsLabel.display(g2d);
		
		ncolorsWriter.setMaximumNumber(Grain.MAX_NUM_COLORS);
		ncolorsWriter.getX().setValue(pw/2+COMPONENT_GAP/2);
		ncolorsWriter.getY().setValue(BORDER_GAP);
		
		ncolorsLabel.getX().setValue(pw/2-COMPONENT_GAP/2
									 -ncolorsLabel.getWidth().getValue());
		ncolorsLabel.getY().setValue(ncolorsWriter.getY().getValue()
									 +ncolorsWriter.getHeight().getValue()/2
									 -ncolorsLabel.getHeight().getValue()/2);
		
		ncolorsPanel.getHeight().setValue(2*BORDER_GAP
										  +ncolorsWriter.getHeight().getValue());
		ncolorsPanel.updateSonsReferences();
	}
	
	private class SettingsPanel extends VPanel {
		
		private VAdjustInt round = new VAdjustInt(15);
		
		public SettingsPanel() {
			super(PANEL_WIDTH, 10);
		}
		
		@Override
		public void adjust(int referenceWidth, int referenceHeight) {
			super.adjust(referenceWidth, referenceHeight);
			adjustValue(round);
		}
		
		@Override
		public void display(Graphics2D g2d) {
			int currentX = getX().getCurrentValue();
			int currentY = getY().getCurrentValue();
			int currentW = getWidth().getCurrentValue();
			int currentH = getHeight().getCurrentValue();
			Graphics2D g2d2 = (Graphics2D) g2d.create();
			g2d2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
								  RenderingHints.VALUE_ANTIALIAS_ON);
			g2d2.setColor(PANEL_COLOR);
			g2d2.fillRoundRect(currentX, currentY, currentW, currentH,
							  round.getValue(), round.getValue());
			g2d2.dispose();
			super.display(g2d);
		}
	}
}
