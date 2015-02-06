package com.stryksta.swtorcentral.data;

public class DatacronItem {
	public String txtPlanet;
	public String txtReward;
	public String txtLocation;
	public String txtCodex;
    public String txtDescription;
	
	public DatacronItem(String txtPlanet, String txtReward, String txtLocation, String txtCodex, String txtDescription) {
		super();
		this.txtPlanet = txtPlanet;
		this.txtReward = txtReward;
		this.txtLocation = txtLocation;
		this.txtCodex = txtCodex;
        this.txtDescription = txtDescription;
	}

	public String getPlanet() {
		return txtPlanet;
	}
	public void setPlanet(String txtPlanet) {
		this.txtPlanet = txtPlanet;
	}
	
	public String getReward() {
		return txtReward;
	}
	public void setReward(String txtReward) {
		this.txtReward = txtReward;
	}
	
	public String getCodexLocation() {
		return txtLocation;
	}
	public void setCodexLocation(String txtLocation) {
		this.txtLocation = txtLocation;
	}
	
	public String getCodex() {
		return txtCodex;
	}
	
	public void setCodex(String txtCodex) {
		this.txtCodex = txtCodex;
	}

    public String getDescription() {
        return txtDescription;
    }

    public void setDescription(String txtDescription) {
        this.txtDescription = txtDescription;
    }

	@Override
	public String toString() {
		return txtPlanet;
	}
}