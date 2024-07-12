package fr.svedel.sandtetris;

public class GameSettings {
	
	private int nRow;
	private int nCol;
	
	// peuvent être ajouté pour combiné les règles
	public static final int GRAY_IS_STONE = 1<<0;
	public static final int BLUE_IS_WATER = 1<<1;
	
	private int rules;
	
	// Gray is not include
	private int numColors;
	/** 1/val chances to get stone */
	private int proportionOfStone;
	/** 1/val chances to get stone */
	private int proportionOfWater;
	
	public GameSettings(int nRow, int nCol, int rules, int numColors,
						int proportionOfStone, int proportionOfWater) {
		this.nRow = nRow;
		this.nCol = nCol;
		this.rules = rules;
		this.numColors = numColors;
		this.proportionOfStone = proportionOfStone;
		this.proportionOfWater = proportionOfWater;
	}
	
	public GameSettings() {
		this(20, 12, 0
			 +GRAY_IS_STONE
			 //+BLUE_IS_WATER
			 , Grain.MAX_NUM_COLORS-1, 50, 20);
	}
	
	public int getNRow() {
		return nRow;
	}
	
	public void setNRow(int nRow) {
		this.nRow = nRow;
	}
	
	public int getNCol() {
		return nCol;
	}
	
	public void setNCol(int nCol) {
		this.nCol = nCol;
	}
	
	public int getRules() {
		return rules;
	}
	
	public void setRules(int rules) {
		this.rules = rules;
	}
	
	public int getNumColors() {
		return numColors;
	}
	
	public void setNumColors(int numColors) {
		this.numColors = numColors;
	}
	
	public int getProportionOfStone() {
		return proportionOfStone;
	}
	
	public void setProportionOfStone(int proportionOfStone) {
		this.proportionOfStone = proportionOfStone;
	}
	
	public int getProportionOfWater() {
		return proportionOfWater;
	}
	
	public void setProportionOfWater(int proportionOfWater) {
		this.proportionOfWater = proportionOfWater;
	}
	
	public boolean isGrayStone() {
		return (rules&GRAY_IS_STONE) != 0;
	}
	
	public boolean isBlueWater() {
		return (rules&BLUE_IS_WATER) != 0;
	}
}
