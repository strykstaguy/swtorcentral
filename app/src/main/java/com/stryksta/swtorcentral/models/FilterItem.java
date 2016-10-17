package com.stryksta.swtorcentral.models;

public class FilterItem {

	private String txtTitle;

	public FilterItem(String txtTitle) {
		this.txtTitle = txtTitle;
	}

	public String getTitle() {
		return txtTitle;
	}

	public void setTitle(String txtTitle) {
		this.txtTitle = txtTitle;
	}
	@Override
	public String toString() {
		return "Title: " + txtTitle;
	}
	
}
