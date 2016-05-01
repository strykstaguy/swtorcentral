package com.stryksta.swtorcentral.data;

public class AdvancedClassItem {
    public int advID;
    public int advClass_id;
    public String advClass;
    public String advDescription;
    public String advRole;
    public String advArmor;
    public String advWeapons;
    public String advPriAttribute;
    public int advAdvanced_class_icon;
    public String advApc;
    public String advAdv_bg;

    public AdvancedClassItem(int advID, int advClass_id, String advClass, String advDescription, String advRole, String advArmor, String advWeapons, String advPriAttribute, int advAdvanced_class_icon, String advApc, String advAdv_bg) {
        this.advID = advID;
        this.advClass_id = advClass_id;
        this.advClass = advClass;
        this.advDescription = advDescription;
        this.advRole = advRole;
        this.advArmor = advArmor;
        this.advWeapons = advWeapons;
        this.advPriAttribute = advPriAttribute;
        this.advAdvanced_class_icon = advAdvanced_class_icon;
        this.advApc = advApc;
        this.advAdv_bg = advAdv_bg;
    }

    public int getAdvID() {
        return advID;
    }

    public void setAdvID(int advID) {
        this.advID = advID;
    }

    public int getAdvClassID() {
        return advClass_id;
    }

    public void setAdvClassID(int advClass_id) {
        this.advClass_id = advClass_id;
    }

    public String getAdvClass() {
        return advClass;
    }

    public void setAdvClass(String advClass) {
        this.advClass = advClass;
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

    public int getAdvAdvancedClassIcon() {
        return advAdvanced_class_icon;
    }

    public void setAdvAdvancedClassIcon(int advAdvanced_class_icon) {
        this.advAdvanced_class_icon = advAdvanced_class_icon;
    }

    public String getAdvApc() {
        return advApc;
    }

    public void setAdvApc(String advApc) {
        this.advApc = advApc;
    }

    public String getAdvBg() {
        return advAdv_bg;
    }

    public void setAdvBg(String advAdv_bg) {
        this.advAdv_bg = advAdv_bg;
    }
}