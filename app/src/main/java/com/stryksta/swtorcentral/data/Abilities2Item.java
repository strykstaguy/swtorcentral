package com.stryksta.swtorcentral.data;

public class Abilities2Item {

    public int ID;
    public int ablClass_ID;
    public int ablAdvanced_Class_ID;
    public int ablID;
    public String ablIsPassive;
    public String ablIconSpec;
    public int ablGlobalCooldownTime;
    public int ablCooldownTime;
    public int ablCastingTime;
    public int ablForceCost;
    public int ablEnergyCost;
    public int ablMinRange;
    public int ablMaxRange;
    public String ablName;
    public String ablDesc;
    public String ablNode;

    public String ablHeader;
    private boolean isGroupHeader = false;

    public Abilities2Item(String ablHeader) {
		this(ablHeader,0,0,0,0,"","",0,0,0,0,0,0,0,"","","");
		isGroupHeader = true;
	}

    public Abilities2Item(String ablHeader, int ID, int ablClass_ID, int ablAdvanced_Class_ID, int ablID, String ablIsPassive, String ablIconSpec, int ablGlobalCooldownTime, int ablCooldownTime, int ablCastingTime, int ablForceCost, int ablEnergyCost, int ablMinRange, int ablMaxRange, String ablName, String ablDesc, String ablNode) {
    	this.ablHeader = ablHeader;
        this.ID = ID;
        this.ablClass_ID = ablClass_ID;
        this.ablAdvanced_Class_ID = ablAdvanced_Class_ID;
        this.ablID = ablID;
        this.ablIsPassive = ablIsPassive;
        this.ablIconSpec = ablIconSpec;
        this.ablGlobalCooldownTime = ablGlobalCooldownTime;
        this.ablCooldownTime = ablCooldownTime;
        this.ablCastingTime = ablCastingTime;
        this.ablForceCost = ablForceCost;
        this.ablEnergyCost = ablEnergyCost;
        this.ablMinRange = ablMinRange;
        this.ablMaxRange = ablMaxRange;
        this.ablName = ablName;
        this.ablDesc = ablDesc;
        this.ablNode = ablNode;
    }
    
    public String getAblHeader() {
        return ablHeader;
    }

    public void setAblHeader(String txtHeader) {
        this.ablHeader = txtHeader;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    public int getablClassID() {
        return ablClass_ID;
    }

    public void setablClassID(int ablClass_ID){
        this.ablClass_ID = ablClass_ID;
    }

    public int getablAdvancedClassID() {
        return ablAdvanced_Class_ID;
    }

    public void setablAdvancedClassID(int ablAdvanced_Class_ID){
        this.ablAdvanced_Class_ID = ablAdvanced_Class_ID;
    }

    public int getablID() {
        return ablID;
    }

    public void setablID(int ablID){
        this.ablID = ablID;
    }

    public String getablIsPassive() {
        return ablIsPassive;
    }

    public void setablIsPassive(String ablIsPassive){
        this.ablIsPassive = ablIsPassive;
    }

    public String getablIconSpec() {
        return ablIconSpec;
    }

    public void setablIconSpece(String ablIconSpec){
        this.ablIconSpec = ablIconSpec;
    }

    public int getablGlobalCooldownTime() {
        return ablGlobalCooldownTime;
    }

    public void setablGlobalCooldownTime(int ablGlobalCooldownTime){
        this.ablGlobalCooldownTime = ablGlobalCooldownTime;
    }

    public int getablCooldownTime() {
        return ablCooldownTime;
    }

    public void setablCooldownTime(int ablCooldownTime){
        this.ablGlobalCooldownTime = ablGlobalCooldownTime;
    }

    public int getablCastingTime() {
        return ablCastingTime;
    }

    public void setablCastingTime(int ablCastingTime){
        this.ablCastingTime = ablCastingTime;
    }

    public int getablForceCost() {
        return ablForceCost;
    }

    public void setablForceCost(int ablForceCost){
        this.ablForceCost = ablForceCost;
    }

    public int getablEnergyCost() {
        return ablEnergyCost;
    }

    public void setablEnergyCost(int ablEnergyCost){
        this.ablEnergyCost = ablEnergyCost;
    }

    public int getablMinRange() {
        return ablMinRange;
    }

    public void setablMinRange(int ablMinRange){
        this.ablMinRange = ablMinRange;
    }

    public int getablMaxRange() {
        return ablMaxRange;
    }

    public void setablMaxRange(int ablMaxRange){
        this.ablMaxRange = ablMaxRange;
    }

    public String getablName() {
        return ablName;
    }

    public void setablName(String ablName){
        this.ablName = ablName;
    }

    public String getablDesc() {
        return ablDesc;
    }

    public void setablDesc(String ablDesc){
        this.ablName = ablDesc;
    }

    public String getablNode() {
        return ablNode;
    }

    public void setablNode(String ablNode){
        this.ablNode = ablNode;
    }

    public boolean isGroupHeader() {
		return isGroupHeader;
	}
	
	public void setGroupHeader(boolean isGroupHeader) {
		this.isGroupHeader = isGroupHeader;
	}
}
