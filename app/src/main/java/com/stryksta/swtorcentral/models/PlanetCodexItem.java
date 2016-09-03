package com.stryksta.swtorcentral.models;

public class PlanetCodexItem {

	private String cdxCategory;
	private String cdxCount;

    public PlanetCodexItem(String cdxCategory, String cdxCount) {
        this.cdxCategory = cdxCategory;
        this.cdxCount = cdxCount;
    }
		
	public String getCategory() {
		return cdxCategory;
	}

	public void setCategory(String cdxCategory) {
		this.cdxCategory = cdxCategory;
	}

	public String getCount() {
		return cdxCount;
	}

	public void setCount(String cdxCount) {
		this.cdxCount = cdxCount;
	}
	
}
