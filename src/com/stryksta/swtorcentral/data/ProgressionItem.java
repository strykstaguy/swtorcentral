package com.stryksta.swtorcentral.data;

public class ProgressionItem {

	private int imgPlanet;
	private int imgPlanet_dbl_1;
	private int imgPlanet_dbl_2;
	private String txtPlanet;
	private String txtPlanet_dbl_1;
	private String txtPlanet_dbl_2;
	private String txtType;

	private boolean isGroupHeader = false;
	private boolean isSeparator = false;
	
	//Single Point
	public ProgressionItem(String title, int imgPlanet) {
		this(imgPlanet, title, null);
		isGroupHeader = true;
	}
	
	//Flashpoint or Bonus
	public ProgressionItem(int imgPlanet, String txtPlanet, String txtType) {
		super();
		this.imgPlanet = imgPlanet;
		this.txtPlanet = txtPlanet;
		this.txtType = txtType;
	}
	
	//Starter and End Game Planets - Double
	public ProgressionItem(String title) {
		isSeparator = true;
	}

	public int getimgPlanet() {
		return imgPlanet;
	}
	
	public void setimgPlanet(int imgPlanet) {
		this.imgPlanet = imgPlanet;
	}
	
	public int getimgPlanetDBL_1() {
		return imgPlanet_dbl_1;
	}
	
	public void setimgPlanetDBL_1(int imgPlanet_dbl_1) {
		this.imgPlanet_dbl_1 = imgPlanet_dbl_1;
	}
	
	public int getimgPlanetDBL_2() {
		return imgPlanet_dbl_2;
	}
	
	public void setimgPlanetDBL_2(int imgPlanet_dbl_2) {
		this.imgPlanet_dbl_2 = imgPlanet_dbl_2;
	}
	
	public String getPlanet() {
		return txtPlanet;
	}
	
	public void setPlanet(String txtPlanet) {
		this.txtPlanet = txtPlanet;
	}
	
	public String getPlanet_DBL_1() {
		return txtPlanet_dbl_1;
	}
	
	public void setPlanet_DBL_1(String txtPlanet_dbl_1) {
		this.txtPlanet_dbl_1 = txtPlanet_dbl_1;
	}
	
	public String getPlanet_DBL_2() {
		return txtPlanet_dbl_2;
	}
	
	public void setPlanet_DBL_2(String txtPlanet_dbl_2) {
		this.txtPlanet_dbl_2 = txtPlanet_dbl_2;
	}
	
	public String getType() {
		return txtType;
	}
	
	public void setType(String txtType) {
		this.txtType = txtType;
	}
	
	public boolean isGroupHeader() {
		return isGroupHeader;
	}
	
	public void setGroupHeader(boolean isGroupHeader) {
		this.isGroupHeader = isGroupHeader;
	}
	
	public boolean isSeparator() {
		return isSeparator;
	}
	
	public void isSeparator(boolean isSeparator) {
		this.isSeparator = isSeparator;
	}
}
