package com.stryksta.swtorcentral.models;

public class PlanetItem {

	private String planet;
	private String description;
		
		
	public String getPlanet() {
		return planet;
	}

	public void setPlanet(String planet) {
		this.planet = planet;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Planet: " + planet;
	}
	
}
