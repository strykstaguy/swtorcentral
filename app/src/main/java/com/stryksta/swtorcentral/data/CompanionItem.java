package com.stryksta.swtorcentral.data;

public class CompanionItem {
    public String txtID;
    public String txtRole;
    public String txtBonus;
    public String txtRomance;
    public String txtPrimaryStat;
    public String txtPrimaryWeapon;
    public String txtSecondaryStat;
    public String txtSecondaryWeapon;
    public String txtGifts;
    public String txtGender;
    public String txtRace;
    public String txtFound;
    public String txtArmor;
    public String txtDescription;
    

    public CompanionItem(String txtName, String txtRole, String txtBonus, String txtRomance, String txtPrimaryStat, String txtPrimaryWeapon, String txtSecondaryStat, String txtSecondaryWeapon, String txtGifts, String txtGender, String txtRace, String txtFound, String txtArmor, String txtDescription) {
        this.txtName = txtName;
        this.txtRole = txtRole;
        this.txtBonus = txtBonus;
        this.txtRomance = txtRomance;
        this.txtPrimaryStat = txtPrimaryStat;
        this.txtPrimaryWeapon = txtPrimaryWeapon;
        this.txtSecondaryStat = txtSecondaryStat;
        this.txtSecondaryWeapon = txtSecondaryWeapon;
        this.txtGifts = txtGifts;
        this.txtGender = txtGender;
        this.txtRace = txtRace;
        this.txtFound = txtFound;
        this.txtArmor = txtArmor;
        this.txtDescription = txtDescription;
    }
    
    public String getName() {
        return txtName;
    }

    public void setName(String txtName){
        this.txtName = txtName;
    }

    public String getRole() {
        return txtRole;
    }

    public void setRole(String txtRole) {
        this.txtRole = txtRole;
    }

    public String getBonus() {
        return txtBonus;
    }

    public void setBonus(String txtBonus) {
        this.txtBonus = txtBonus;
    }

    public String getRomance() {
        return txtRomance;
    }

    public void setRomance(String txtRomance) {
        this.txtRomance = txtRomance;
    }

    public String getPrimaryStat() {
        return txtPrimaryStat;
    }

    public void setPrimaryStat(String txtPrimaryStat) {
        this.txtPrimaryStat = txtPrimaryStat;
    }

    public String getPrimaryWeapon() {
        return txtPrimaryWeapon;
    }

    public void setPrimaryWeapon(String txtPrimaryWeapon) {
        this.txtPrimaryWeapon = txtPrimaryWeapon;
    }

    public String getSecondaryStat() {
        return txtSecondaryStat;
    }

    public void setSecondaryStat(String txtSecondaryStat) {
        this.txtSecondaryStat = txtSecondaryStat;
    }

    public String getSecondaryWeapon() {
        return txtSecondaryWeapon;
    }

    public void setSecondaryWeapon(String txtSecondaryWeapon) {
        this.txtSecondaryWeapon = txtSecondaryWeapon;
    }
    
    
    public String getGifts() {
        return txtGifts;
    }

    public void setGifts(String txtGifts) {
        this.txtGifts = txtGifts;
    }
    
    public String getGender() {
        return txtGender;
    }

    public void setGender(String txtGender) {
        this.txtGender = txtGender;
    }
    
    public String getRace() {
        return txtRace;
    }

    public void setRace(String txtRace) {
        this.txtRace = txtRace;
    }
    
    public String getFound() {
        return txtFound;
    }

    public void setFound(String txtFound) {
        this.txtFound = txtFound;
    }

    public String getArmor() {
        return txtArmor;
    }

    public void setArmor(String txtArmor) {
        this.txtArmor = txtArmor;
    }
    
    public String getDescription() {
        return txtDescription;
    }

    public void setDescription(String txtDescription) {
        this.txtDescription = txtDescription;
    }

    public String toString() {
        return txtName;
    }
}
