package com.stryksta.swtorcentral.models;

public class CodexItem {
    public int cdxID;
    public String cdxTitle;
    public String cdxDescription;
    public String cdxCategory;
    public String cdxLevel;
    public String cdxImage;
    public String cdxFaction;
    public String cdxIsPlanetCodex;
    public String cdxPlants;

    public CodexItem(int cdxID, String cdxTitle, String cdxDescription, String cdxCategory, String cdxLevel, String cdxImage, String cdxFaction, String cdxIsPlanetCodex, String cdxPlants) {
    	this.cdxID = cdxID;
        this.cdxTitle = cdxTitle;
        this.cdxDescription = cdxDescription;
        this.cdxCategory = cdxCategory;
        this.cdxLevel = cdxLevel;
        this.cdxImage = cdxImage;
        this.cdxFaction = cdxFaction;
        this.cdxIsPlanetCodex = cdxIsPlanetCodex;
        this.cdxPlants = cdxPlants;
    }
    
    public int getID() {
        return cdxID;
    }

    public void setID(int cdxID){
        this.cdxID = cdxID;
    }

    public String getTitle() {
        return cdxTitle;
    }

    public void setTitle(String cdxTitle){
        this.cdxTitle = cdxTitle;
    }

    public String getDescription() {
        return cdxDescription;
    }

    public void setDescription(String cdxDescription){
        this.cdxDescription = cdxDescription;
    }

    public String getCategory() {
        return cdxCategory;
    }

    public void setCategory(String cdxCategory){
        this.cdxCategory = cdxCategory;
    }

    public String getLevel() {
        return cdxLevel;
    }

    public void setLevel(String cdxLevel){
        this.cdxLevel = cdxLevel;
    }

    public String getImage() {
        return cdxImage;
    }

    public void setImage(String cdxImage){
        this.cdxImage = cdxImage;
    }

    public String getFaction() {
        return cdxFaction;
    }

    public void setFaction(String cdxFaction){
        this.cdxFaction = cdxFaction;
    }

    public String getCdxIsPlanetCodex() {
        return cdxIsPlanetCodex;
    }

    public void setCdxIsPlanetCodex(String cdxIsPlanetCodex){
        this.cdxIsPlanetCodex = cdxIsPlanetCodex;
    }

    public String getPlanets() {
        return cdxPlants;
    }

    public void setPlanets(String cdxPlants){
        this.cdxPlants = cdxPlants;
    }
}
