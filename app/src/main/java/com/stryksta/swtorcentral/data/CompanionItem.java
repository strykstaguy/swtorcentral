package com.stryksta.swtorcentral.data;

public class CompanionItem {
    public String txtID;
    public String txtName;
    public String txtTitle;
    public String txtCategory;
    public String txtSubCategory;
    public String txtDescription;
    public String txtGender;
    public String txtGiftsUnRomanced;
    public String txtGiftsRomanced;
    public String txtRace;
    public String txtPath;

    public CompanionItem(String txtID, String txtName, String txtTitle, String txtCategory, String txtSubCategory, String txtDescription, String txtGender, String txtGiftsUnRomanced, String txtGiftsRomanced, String txtRace, String txtPath) {
        this.txtID = txtID;
        this.txtName = txtName;
        this.txtTitle = txtTitle;
        this.txtCategory = txtCategory;
        this.txtSubCategory = txtSubCategory;
        this.txtDescription = txtDescription;
        this.txtGender = txtGender;
        this.txtGiftsUnRomanced = txtGiftsUnRomanced;
        this.txtGiftsRomanced = txtGiftsRomanced;
        this.txtRace = txtRace;
        this.txtPath = txtPath;
    }

    public String getID() {
        return txtID;
    }

    public void getID(String txtName){
        this.txtID = txtID;
    }

    public String getName() {
        return txtName;
    }

    public void setName(String txtName){
        this.txtName = txtName;
    }

    public String getTitle() {
        return txtTitle;
    }

    public void setTitle(String txtTitle){
        this.txtTitle = txtTitle;
    }

    public String getCategory() {
        return txtCategory;
    }

    public void setCategory(String txtCategory) {
        this.txtCategory = txtCategory;
    }

    public String getSubCategory() {
        return txtSubCategory;
    }

    public void setSubCategory(String txtSubCategory) {
        this.txtSubCategory = txtSubCategory;
    }

    public String getDescription() {
        return txtDescription;
    }

    public void setDescription(String txtDescription) {
        this.txtDescription = txtDescription;
    }

    public String getGender() {
        return txtGender;
    }

    public void setGender(String txtGender) {
        this.txtGender = txtGender;
    }

    public String getGiftsUnRomanced() {
        return txtGiftsUnRomanced;
    }

    public void setGiftsUnRomanced(String txtGiftsUnRomanced) {
        this.txtGiftsUnRomanced = txtGiftsUnRomanced;
    }

    public String getGiftsRomanced() {
        return txtGiftsRomanced;
    }

    public void setGiftsRomanced(String txtGiftsRomanced) {
        this.txtGiftsRomanced = txtGiftsRomanced;
    }

    public String getRace() {
        return txtRace;
    }

    public void setRace(String txtRace) {
        this.txtRace = txtRace;
    }

    public String getPath() {
        return txtPath;
    }

    public void setPath(String txtPath) {
        this.txtPath = txtPath;
    }

    public String toString() {
        return txtName;
    }
}
