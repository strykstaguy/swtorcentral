package com.stryksta.swtorcentral.models;

public class PlanetCodexItem {

	private String cdxCategory;
    private String cdxPlanetID;
	private String cdxCount;
    private String cdxPlanetName;

    public PlanetCodexItem(String cdxCategory, String cdxCount, String cdxPlanetID, String cdxPlanetName) {
        this.cdxCategory = cdxCategory;
        this.cdxCount = cdxCount;
        this.cdxPlanetID = cdxPlanetID;
        this.cdxPlanetName = cdxPlanetName;
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

    public String getPlanetID() {
        return cdxPlanetID;
    }

    public void setPlanetID(String cdxPlanetID) {
        this.cdxPlanetID = cdxPlanetID;
    }

    public String getPlanetName() {
        return cdxPlanetName;
    }

    public void setPlanetName(String cdxPlanetName) {
        this.cdxPlanetName = cdxPlanetName;
    }
}
