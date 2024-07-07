package fr.svedel.sandtetris;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import fr.svedel.vcomponent.VButton;
import fr.svedel.vcomponent.VLabel;
import fr.svedel.vcomponent.VPanel;
import fr.svedel.vcomponent.VNumberWriter;

public class SettingsMenu extends Menu {
	
	private static final int COMPONENT_GAP = 3;
	
	private static final int PANEL_GAP = 5;
	private static final int PANEL_WIDTH = 15;
	private static final Color PANEL_COLOR = Color.BLACK.brighter();
	
	private static final int WRITER_HEIGHT = 5;
	
	private VPanel dimPanel = new VPanel(PANEL_WIDTH, 0);
	private VLabel nrowLabel = new VLabel("n row");
	private VNumberWriter nrowWriter = new VNumberWriter(WRITER_HEIGHT);
	private VLabel ncolLabel = new VLabel("n col");
	private VNumberWriter ncolWriter = new VNumberWriter(WRITER_HEIGHT);
	
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
		g2dbi.dispose();
	}
	
	private void initDimPanel(Graphics2D g2d) {
		int refWidth = getReferenceWidth().getValue();
		int refHeight = getReferenceHeight().getValue();
		add(dimPanel);
		/*dimPanel.*/add(nrowLabel);
		/*dimPanel.*/add(nrowWriter);
		/*dimPanel.*/add(ncolLabel);
		/*dimPanel.*/add(ncolWriter);
		
		// to set the labels' dimensions
		nrowLabel.display(g2d);
		ncolLabel.display(g2d);
		
		dimPanel.getX().setValue(refWidth/2-getWidth().getValue()/2);
	}
}
