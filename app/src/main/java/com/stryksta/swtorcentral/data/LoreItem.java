package com.stryksta.swtorcentral.data;

public class LoreItem {
    public String faction;
    public String planet;
    public String category;
    public String codex;
    public String text;
    public String comment;
    private boolean isGroupHeader = false;
    
    public LoreItem(String txtTitle, String txtSubTitle) {
		this(txtTitle, txtSubTitle, "", "", "", "");
		isGroupHeader = true;
	}
    
    public LoreItem(String faction, String planet, String category, String codex,  String text, String comment) {
    	this.faction = faction;
    	this.planet = planet;
    	this.category = category;
    	this.codex = codex;
    	this.text = text;
    	this.comment = comment;
    }
    
    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction){
        this.faction = faction;
    }
    
    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet){
        this.planet = planet;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }
    
    public String getCodex() {
        return codex;
    }

    public void setCodex(String codex){
        this.codex = codex;
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text){
        this.text = text;
    }
    
    public String getComment() {
        return comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }
    
    public boolean isGroupHeader() {
		return isGroupHeader;
	}
	
	public void setGroupHeader(boolean isGroupHeader) {
		this.isGroupHeader = isGroupHeader;
	}
}