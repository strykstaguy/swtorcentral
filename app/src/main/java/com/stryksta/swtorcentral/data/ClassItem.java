package com.stryksta.swtorcentral.data;


public class ClassItem {

    private int clsID;
    private String txtClassName;
    private int clsFaction;
    private int txtIcon;
    private String txtDescription;
    private String txtResource;
    private String txtCombatRole;
    private String txtStory;
    private String txtAbilities;
    private String txtEquipment;
    private String txtApc;
    private String txtNode;

    public ClassItem(int clsID, String txtClassName, int clsFaction, int txtIcon, String txtDescription, String txtResource, String txtCombatRole, String txtStory, String txtAbilities, String txtEquipment, String txtApc, String txtNode) {
        this.clsID = clsID;
        this.txtClassName = txtClassName;
    	this.clsFaction = clsFaction;
        this.txtIcon = txtIcon;
    	this.txtDescription = txtDescription;
        this.txtResource = txtResource;
        this.txtCombatRole = txtCombatRole;
        this.txtStory = txtStory;
        this.txtAbilities = txtAbilities;
        this.txtEquipment = txtEquipment;
        this.txtApc = txtApc;
        this.txtNode = txtNode;
    }

    public int getClassID() {
        return clsID;
    }

    public void setClassID(int clsID) {
        this.clsID = clsID;
    }

    public String getClassName() {
        return txtClassName;
    }

    public void setClassName(String txtClassName) {
        this.txtClassName = txtClassName;
    }

    public int getFaction() {
        return clsFaction;
    }

    public void setFaction(int clsFaction) {
        this.clsFaction = clsFaction;
    }

    public int getIcon() {
        return txtIcon;
    }

    public void setIcon(int txtIcon) {
        this.txtIcon = txtIcon;
    }

    public String getDescription() {
        return txtDescription;
    }

    public void setDescription(String txtDescription) {
        this.txtDescription = txtDescription;
    }

    public String getResource() {
        return txtResource;
    }

    public void setResource(String txtResource) {
        this.txtResource = txtResource;
    }

    public String getCombatRole() {
        return txtCombatRole;
    }

    public void setCombatRole(String txtCombatRole) {
        this.txtCombatRole = txtCombatRole;
    }

    public String getStory() {
        return txtStory;
    }

    public void setStory(String txtStory) {
        this.txtStory = txtStory;
    }

    public String getAbilities() {
        return txtAbilities;
    }

    public void setAbilities(String txtAbilities) {
        this.txtAbilities = txtAbilities;
    }

    public String getEquipment() {
        return txtEquipment;
    }

    public void setEquipment(String txtEquipment) {
        this.txtEquipment = txtEquipment;
    }

    public String getApc() {
        return txtApc;
    }

    public void setApc(String txtApc) {
        this.txtApc = txtApc;
    }

    public String getNode() {
        return txtNode;
    }

    public void setNode(String txtNode) {
        this.txtNode = txtNode;
    }

    @Override
	public String toString() {
		return "Class: " + txtClassName;
	}
}
