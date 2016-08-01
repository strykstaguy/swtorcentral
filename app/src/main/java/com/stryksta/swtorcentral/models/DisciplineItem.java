package com.stryksta.swtorcentral.models;

public class DisciplineItem {
    public int disID;
    public String advClassName;
    public int disSortIndex;
    public String disType;
    public String disName;
    public String disDescription;
    public String disAbl;
    public String disApc;

    public DisciplineItem(int disID, String advClassName, int disSortIndex, String disType, String disName, String disDescription, String disAbl, String disApc) {
    	this.disID = disID;
    	this.advClassName = advClassName;
    	this.disSortIndex = disSortIndex;
    	this.disType = disType;
    	this.disName = disName;
    	this.disDescription = disDescription;
        this.disAbl = disAbl;
        this.disApc = disApc;
    }
    
    public int getDisciplineID() {
        return disID;
    }

    public void setDisciplineID(int disID){
        this.disID = disID;
    }
    
    public String getAdvancedClassID() {
        return advClassName;
    }

    public void setAdvancedClassID(String advClassName){
        this.advClassName = advClassName;
    }
    
    public int getSortIndex() {
        return disSortIndex;
    }

    public void setSortIndex(int disSortIndex){
        this.disSortIndex = disSortIndex;
    }
    
    public String getType() {
        return disType;
    }

    public void setType(String disType){
        this.disType = disType;
    }

    public String getDisciplineName() {
        return disName;
    }

    public void setDisciplineName(String disName){
        this.disName = disName;
    }

    public String getDisciplineDescription() {
        return disDescription;
    }

    public void setDisciplineDescription(String disDescription){
        this.disDescription = disDescription;
    }

    public String getABL() {
        return disAbl;
    }

    public void setABL(String disAbl){
        this.disAbl = disAbl;
    }

    public String getAPC() {
        return disApc;
    }

    public void setAPC(String disApc){
        this.disApc = disApc;
    }
}