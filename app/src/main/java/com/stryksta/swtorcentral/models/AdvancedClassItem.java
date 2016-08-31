package com.stryksta.swtorcentral.models;

public class AdvancedClassItem {
    public int advID;
    public String advClassName;
    public String clsName;
    public String advDescription;
    public String advRole;
    public String advWeapons;
    public String advArmor;
    public String advPriAttribute;
    public String advResource;
    public int advAdvanced_class_icon;
    public String advAPN;
    public String advAPC;

    public AdvancedClassItem(int advID, String advClassName, String clsName, String advDescription, String advRole, String advWeapons, String advArmor, String advPriAttribute, String advResource, int advAdvanced_class_icon, String advAPN, String advAPC) {
        this.advID = advID;
        this.advClassName = advClassName;
        this.clsName = clsName;
        this.advDescription = advDescription;
        this.advRole = advRole;
        this.advWeapons = advWeapons;
        this.advArmor = advArmor;
        this.advPriAttribute = advPriAttribute;
        this.advResource = advResource;
        this.advAdvanced_class_icon = advAdvanced_class_icon;
        this.advAPN = advAPN;
        this.advAPC = advAPC;
    }

    public int getAdvancedID() {
        return advID;
    }

    public void setAdvancedID(int advID) {
        this.advID = advID;
    }

    public String getAdvClassName() {
        return advClassName;
    }

    public void setAdvClassName(String advClassName) {
        this.advClassName = advClassName;
    }

    public String getClassName() {
        return clsName;
    }

    public void setClassName(String clsName) {
        this.clsName = clsName;
    }

    public String getAdvDescription() {
        return advDescription;
    }

    public void setAdvDescription(String advDescription) {
        this.advDescription = advDescription;
    }

    public String getAdvRole() {
        return advRole;
    }

    public void setAdvRole(String advRole) {
        this.advRole = advRole;
    }

    public String getAdvArmor() {
        return advArmor;
    }

    public void setAdvArmor(String advArmor) {
        this.advArmor = advArmor;
    }

    public String getAdvWeapons() {
        return advWeapons;
    }

    public void setAdvWeapons(String category) {
        this.advWeapons = advWeapons;
    }

    public String getAdvPriAttribute() {
        return advPriAttribute;
    }

    public void setAdvPriAttribute(String advPriAttribute) {
        this.advPriAttribute = advPriAttribute;
    }

    public String getResource() {
        return advResource;
    }

    public void setResource(String advResource) {
        this.advResource = advResource;
    }

    public int getAdvAdvancedClassIcon() {
        return advAdvanced_class_icon;
    }

    public void setAdvAdvancedClassIcon(int advAdvanced_class_icon) {
        this.advAdvanced_class_icon = advAdvanced_class_icon;
    }

    public String getAdvAPC() {
        return advAPC;
    }

    public void setAdvAPC(String advAPC) {
        this.advAPC = advAPC;
    }

    public String getAdvAPN() {
        return advAPN;
    }

    public void setAdvAPN(String advAPN) {
        this.advAPN = advAPN;
    }
}