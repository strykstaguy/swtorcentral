package com.stryksta.swtorcentral.data;

import com.stryksta.swtorcentral.util.timeline.TimelineType;

public class ProgressionItem {
	
	private int imgPlanet;
    private String planet;
    private String level;
    private String label;
    int layoutType;
    private TimelineType timelineType;
    public static final int HEADER = 0;
    public static final int PLANET = 1;
    public static final int SECTION = 2;
    public static final int FLASHOPR = 3;
    public static final int FLASHOPL = 4;
    public static final int BONUS = 5;
    public static final int FOOTER = 6;

    public ProgressionItem(String planet, TimelineType timelineType, int layoutType) {
        this.planet = planet;
        this.timelineType = timelineType;
        this.layoutType = layoutType;
    }

    public ProgressionItem(String planet, int layoutType) {
        this.planet = planet;
        this.layoutType = layoutType;
        this.timelineType = TimelineType.LINE;
    }

    public ProgressionItem(String planet, String level, int imgPlanet, TimelineType timelineType, int layoutType) {
        this.planet = planet;
        this.level = level;
        this.imgPlanet = imgPlanet;
        this.timelineType = timelineType;
        this.layoutType = layoutType;
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

    public int getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(int layoutType) {
        this.layoutType = layoutType;
    }

    public TimelineType getTimelineTypeType() {
        return timelineType;
    }

    public void setTimelineTypeType(TimelineType timelineType) {
        this.timelineType = timelineType;
    }
}
