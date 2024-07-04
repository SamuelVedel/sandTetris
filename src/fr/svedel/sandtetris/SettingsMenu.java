package fr.svedel.sandtetris;

import java.awt.Color;

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
		initDimPanel();
	}
	
	private void initDimPanel() {
		
	}
}
