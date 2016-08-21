package com.stryksta.swtorcentral.models;

public class SkillItem {
    public int skillID;
    public String skillName;
    public String skillDescription;
    public String skillNode;
    public int skillPOS;
    public int skillLevel;

    public SkillItem(int skillID, String skillName, String skillDescription, String skillNode, int skillPOS, int skillLevel) {
    	this.skillID = skillID;
    	this.skillName = skillName;
    	this.skillDescription = skillDescription;
    	this.skillNode = skillNode;
    	this.skillPOS = skillPOS;
    	this.skillLevel = skillLevel;
    }
    
    public int getSkillID() {
        return skillID;
    }

    public void setSkillID(int skillID){
        this.skillID = skillID;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName){
        this.skillName = skillName;
    }

    public String getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription){
        this.skillDescription = skillDescription;
    }

    public String getSkillNode() {
        return skillNode;
    }

    public void setSkillNode(String skillNode){
        this.skillNode = skillNode;
    }

    public int getSkillPOS() {
        return skillPOS;
    }

    public void setSkillPOS(int skillID){
        this.skillPOS = skillPOS;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel){
        this.skillLevel = skillLevel;
    }
}