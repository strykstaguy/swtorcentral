package com.stryksta.swtorcentral.data;

public class AbilitiesItem {
    public int abilityID;
    public int classID;
    public int advancedID;
    public int skill_tree_id;
    public int dependonID;
    public int level;
    public int credits;
    public String txtName;
    public int rank;
    public String summary;
    public String description;
    public String footnote;
    public String highlight;
    public int resource;
    public int passive;
    public String activation;
    public String channeled;
    public String cooldown;
    public String range;
    public String txtHeader;
    private boolean isGroupHeader = false;

    public AbilitiesItem(String txtHeader) {
		this(txtHeader, 0, 0, 0, 0, 0, 0, 0, "", 0, "", "", "", "", 0, 0, "", "", "", "");
		isGroupHeader = true;
	}
    
    public AbilitiesItem(String txtHeader, int abilityID, int classID, int advancedID, int skill_tree_id, int dependonID, int level, int credits, String txtName, int rank, String summary, String description, String footnote, String highlight, int resource, int passive, String activation, String channeled, String cooldown, String range) {
    	this.txtHeader = txtHeader;
    	this.abilityID = abilityID;
        this.classID = classID;
        this.advancedID = advancedID;
        this.skill_tree_id = skill_tree_id;
        this.dependonID = dependonID;
        this.level = level;
        this.credits = credits;
        this.txtName = txtName;
        this.rank = rank;
        this.summary = summary;
        this.description = description;
        this.footnote = footnote;
        this.highlight = highlight;
        this.resource = resource;
        this.passive = passive;
        this.activation = activation;
        this.channeled = channeled;
        this.cooldown = cooldown;
        this.range = range;
    }
    
    public String gettxtHeader() {
        return txtHeader;
    }

    public void settxtHeader(String txtHeader) {
        this.txtHeader = txtHeader;
    }
    
    public int getabilityID() {
        return abilityID;
    }

    public void setabilityID(int abilityID){
        this.abilityID = abilityID;
    }
    
    public int getSkillTreeID() {
        return skill_tree_id;
    }

    public void setSkillTreeID(int skill_tree_id){
        this.skill_tree_id = skill_tree_id;
    }
    
    public int getclassID() {
        return classID;
    }

    public void setclassID(int classID){
        this.classID = classID;
    }

    public int getadvancedID() {
        return advancedID;
    }

    public void setadvancedID(int advancedID){
        this.advancedID = advancedID;
    }

    public int getdependonID() {
        return dependonID;
    }

    public void setdependonID(int dependonID){
        this.dependonID = dependonID;
    }

    public int getlevel() {
        return level;
    }

    public void setlevel(int level){
        this.level = level;
    }

    public int getcredits() {
        return credits;
    }

    public void setcredits(int credits){
        this.credits = credits;
    }

    public String gettxtName() {
        return txtName;
    }

    public void settxtName(String txtName) {
        this.txtName = txtName;
    }

    public int getrank() {
        return rank;
    }

    public void setrank(int rank){
        this.rank = rank;
    }

    public String getsummary() {
        return summary;
    }

    public void setsummary(String summary) {
        this.summary = summary;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public String getfootnote() {
        return footnote;
    }

    public void setfootnote(String footnote) {
        this.footnote = footnote;
    }

    public String gethighlight() {
        return highlight;
    }

    public void sethighlight(String highlight) {
        this.highlight = highlight;
    }

    public int getresource() {
        return resource;
    }

    public void setresource(int resource){
        this.resource = resource;
    }

    public int getpassive() {
        return passive;
    }

    public void setpassive(int passive){
        this.passive = passive;
    }

    public String getactivation() {
        return activation;
    }

    public void setactivation(String activation) {
        this.activation = activation;
    }

    public String getchanneled() {
        return channeled;
    }

    public void setchanneled(String channeled) {
        this.channeled = channeled;
    }

    public String getcooldown() {
        return cooldown;
    }

    public void setcooldown(String cooldown) {
        this.cooldown = cooldown;
    }

    public String getrange() {
        return range;
    }

    public void setrange(String range) {
        this.range = range;
    }
    
    public boolean isGroupHeader() {
		return isGroupHeader;
	}
	
	public void setGroupHeader(boolean isGroupHeader) {
		this.isGroupHeader = isGroupHeader;
	}
}
