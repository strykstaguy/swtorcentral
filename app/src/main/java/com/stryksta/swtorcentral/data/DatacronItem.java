package com.stryksta.swtorcentral.data;

public class DatacronItem {
	public static final int ITEM = 0;
	public static final int SECTION = 1;
	public int type;
	public String txtPlanet;
	public String txtReward;
	public String txtLocation;
	public String txtCodex;
	
	public DatacronItem(int type, String txtPlanet, String txtReward, String txtLocation, String txtCodex) {
		super();
		this.type = type;
		this.txtPlanet = txtPlanet;
		this.txtReward = txtReward;
		this.txtLocation = txtLocation;
		this.txtCodex = txtCodex;
	}
	
	public DatacronItem(int type, String txtPlanet){
    	this(type, txtPlanet, "", "", "");
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
	
	public String getLocation() {
		return txtLocation;
	}
	public void setLocation(String txtLocation) {
		this.txtLocation = txtLocation;
	}
	
	public String getCodex() {
		return txtCodex;
	}
	
	public void setCodex(String txtCodex) {
		this.txtCodex = txtCodex;
	}
	
	@Override
	public String toString() {
		return txtPlanet;
	}
}