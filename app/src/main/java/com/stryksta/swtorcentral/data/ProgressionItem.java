package com.stryksta.swtorcentral.data;

import com.stryksta.swtorcentral.util.TimelineType;

public class ProgressionItem {
	
	private int imgPlanet;
    private String planet;
    private String level;
    private String label;
    private TimelineType type;

    public ProgressionItem(String planet, String level, int imgPlanet, String label) {
        this.planet = planet;
        this.level = level;
        this.imgPlanet = imgPlanet;
        this.label = label;
        this.type = TimelineType.LINE;
    }

    public ProgressionItem(String planet, String level, int imgPlanet, String label, TimelineType type) {
        this.planet = planet;
        this.level = level;
        this.imgPlanet = imgPlanet;
        this.label = label;
        this.type = type;
    }
    
    public int getimgPlanet() {
		return imgPlanet;
	}
	
	public void setimgPlanet(int imgPlanet) {
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
    
    public TimelineType getType() {
        return type;
    }

    public void setType(TimelineType type) {
        this.type = type;
    }
}
