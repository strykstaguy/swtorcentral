package com.stryksta.swtorcentral.models;

public class DatacronItem {
	public String dtcTitle;
	public String dtcCodex;
	public String dtcMap;
	public String dtcLocation;
    public String dtcFaction;
	public String dtcReward;
	public String dtcCoord;
    public String dtcPath;
	
	public DatacronItem(String dtcTitle, String dtcCodex, String dtcMap, String dtcLocation, String dtcFaction, String dtcReward, String dtcCoord, String dtcPath) {
		super();
		this.dtcTitle = dtcTitle;
		this.dtcCodex = dtcCodex;
		this.dtcMap = dtcMap;
		this.dtcLocation = dtcLocation;
        this.dtcFaction = dtcFaction;
        this.dtcReward = dtcReward;
        this.dtcCoord = dtcCoord;
        this.dtcPath = dtcPath;
	}

    public String getTitle() {
        return dtcTitle;
    }
    public void setTitle(String dtcTitle) {
        this.dtcTitle = dtcTitle;
    }

    public String getCodex() {
        return dtcCodex;
    }
    public void setCodex(String dtcCodex) {
        this.dtcCodex = dtcCodex;
    }

	public String getMap() {
		return dtcMap;
	}
	public void setMap(String dtcMap) {
		this.dtcMap = dtcMap;
	}

    public String getLocation() {
        return dtcLocation;
    }
    public void setLocation(String dtcLocation) {
        this.dtcLocation = dtcLocation;
    }

    public String getFaction() {
        return dtcFaction;
    }
    public void setFaction(String dtcFaction) {
        this.dtcFaction = dtcFaction;
    }

    public String getReward() {
        return dtcReward;
    }
    public void setReward(String dtcReward) {
        this.dtcReward = dtcReward;
    }

    public String getCoord() {
        return dtcCoord;
    }
    public void setCoord(String dtcCoord) {
        this.dtcCoord = dtcCoord;
    }

    public String getPath() {
        return dtcPath;
    }
    public void setPath(String dtcPath) {
        this.dtcPath = dtcPath;
    }

	@Override
	public String toString() {
		return dtcTitle;
	}
}