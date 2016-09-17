package com.stryksta.swtorcentral.models;

import com.stryksta.swtorcentral.ui.views.timeline.TimelineType;

public class ProgressionItem {
	
	private int imgPlanetRepublic;
    private int imgPlanetEmpire;
    private int imgIcon;

    private String planetRepublic;
    private String flashpointRepublic;
    private String planetEmpire;
    private String flashpointEmpire;

    private String levelRepublic;
    private String levelEmpire;

    int layoutType;
    int layoutDirection;
    private TimelineType timelineType;

    public static final int HEADER = 0;
    public static final int CHAPTER = 1;
    public static final int PLANET_SINGLE = 2;
    public static final int PLANET_DOUBLE = 3;
    public static final int FLASHOP_SINGLE = 4;
    public static final int FLASHOP_DOUBLE = 5;
    public static final int FOOTER = 6;
    public static final int DIR_RIGHT = 0;
    public static final int DIR_LEFT = 1;

    //Header
    public ProgressionItem(String planetRepublic, TimelineType timelineType, int layoutType) {
        this.planetRepublic = planetRepublic;
        this.timelineType = timelineType;
        this.layoutType = layoutType;
    }

    //Footer
    public ProgressionItem(int layoutType) {
        this.timelineType = TimelineType.LINE;
        this.layoutType = layoutType;
    }

    //Chapter
    public ProgressionItem(String planetRepublic, int layoutType) {
        this.planetRepublic = planetRepublic;
        this.layoutType = layoutType;
        this.timelineType = TimelineType.LINE;
    }

    //Flashpoint/Operation Single
    public ProgressionItem(String flashpointRepublic, String levelRepublic, int imgIcon, int layoutType, int layoutDirection) {
        this.flashpointRepublic = flashpointRepublic;
        this.levelRepublic = levelRepublic;
        this.imgIcon = imgIcon;
        this.layoutType = layoutType;
        this.timelineType = TimelineType.LINE;
        this.layoutDirection = layoutDirection;
    }

    //Flashpoint/Operation Double
    public ProgressionItem(String flashpointRepublic, String levelRepublic, String flashpointEmpire, String levelEmpire, int imgIcon, int layoutType) {
        this.flashpointRepublic = flashpointRepublic;
        this.flashpointEmpire = flashpointEmpire;
        this.levelRepublic = levelRepublic;
        this.levelEmpire = levelEmpire;
        this.imgIcon = imgIcon;
        this.layoutType = layoutType;
        this.timelineType = TimelineType.LINE;
    }

    //Planet Single
    public ProgressionItem(String planetRepublic, String levelRepublic, int imgPlanetRepublic, int layoutDirection) {
        this.planetRepublic = planetRepublic;
        this.levelRepublic = levelRepublic;
        this.imgPlanetRepublic = imgPlanetRepublic;
        this.timelineType = TimelineType.LINE;
        this.layoutType = PLANET_SINGLE;
        this.layoutDirection = layoutDirection;
    }

    //Planet Double
    public ProgressionItem(String planetRepublic, String levelRepublic, int imgPlanetRepublic, String planetEmpire, String levelEmpire, int imgPlanetEmpire) {
        this.planetRepublic = planetRepublic;
        this.levelRepublic = levelRepublic;
        this.imgPlanetRepublic = imgPlanetRepublic;
        this.planetEmpire = planetEmpire;
        this.levelEmpire = levelEmpire;
        this.imgPlanetEmpire = imgPlanetEmpire;
        this.timelineType = TimelineType.LINE;
        this.layoutType = PLANET_DOUBLE;
    }
    public int getIcon() {
        return imgIcon;
    }

    public void setIcon(int imgIcon) {
        this.imgIcon = imgIcon;
    }

    public int getRepublicPlanetImage() {
		return imgPlanetRepublic;
	}
	
	public void setRepublicPlanetImage(int imgPlanetRepublic) {
		this.imgPlanetRepublic = imgPlanetRepublic;
	}
	
    public String getRepublicPlanet() {
        return planetRepublic;
    }

    public void setPlanetRepublic(String planetRepublic) {
        this.planetRepublic = planetRepublic;
    }

    public String getRepublicFlashpoint() {
        return flashpointRepublic;
    }

    public void setFlashpointRepublic(String flashpointRepublic) {
        this.flashpointRepublic = flashpointRepublic;
    }

    public String getEmpireFlashpoint() {
        return flashpointEmpire;
    }

    public void setFlashpointEmpire(String flashpointEmpire) {
        this.flashpointEmpire = flashpointEmpire;
    }

    public String getRepublicLevel() {
        return levelRepublic;
    }

    public void setRepublicLevel(String levelRepublic) {
        this.levelRepublic = levelRepublic;
    }

    public int getEmpirePlanetImage() {
        return imgPlanetEmpire;
    }

    public void setEmpirePlanetImage(int imgPlanetEmpire) {
        this.imgPlanetEmpire = imgPlanetEmpire;
    }

    public String getEmpirePlanet() {
        return planetEmpire;
    }

    public void setEmpirePlanet(String planetEmpire) {
        this.planetEmpire = planetEmpire;
    }

    public String getEmpireLevel() {
        return levelEmpire;
    }

    public void setEmpireLevel(String levelEmpire) {
        this.levelEmpire = levelEmpire;
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

    public int getLayoutDirection() {
        return layoutDirection;
    }

    public void setLayoutDirection(int layoutDirection) {
        this.layoutDirection = layoutDirection;
    }
}
