package com.stryksta.swtorcentral.data;

public class TestItem {

	private int imgPlanet;
    private String planet;
    private String level;
    private String label;
    public int type;
    public static final int ITEM = 0;
    public static final int SECTION = 1;

    public TestItem(String planet, String level, int imgPlanet, String label, int type) {
        super();
        this.type = type;
        this.planet = planet;
        this.level = level;
        this.imgPlanet = imgPlanet;
        this.label = label;
    }

    public TestItem(String label, int type){
        this("", "", 0, label, type);
    }

    public int getImgPlanet() {
		return imgPlanet;
	}
	
	public void setImgPlanet(int imgPlanet) {
		this.imgPlanet = imgPlanet;
	}
	
    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }
    
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
