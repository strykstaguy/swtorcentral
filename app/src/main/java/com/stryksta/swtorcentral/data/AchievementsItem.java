package com.stryksta.swtorcentral.data;

public class AchievementsItem {
    public int achID;
    public String achCategory1;
    public String achCategory2;
    public String achCategory3;
    public String achTitle;
    public String achDescription;
    public int achRewardPoints;
    public String achRewardTitle;
    public Integer achRewardFleetRequisition;
    public String achVisibility;
    public int achCompleted;

    public AchievementsItem(int achID, String achCategory1, String achCategory2, String achCategory3, String achTitle, String achDescription, int achRewardPoints, String achRewardTitle, Integer achRewardFleetRequisition, String achVisibility, int achCompleted) {
    	this.achID = achID;
        this.achCategory1 = achCategory1;
        this.achCategory2 = achCategory2;
        this.achCategory3 = achCategory3;
        this.achTitle = achTitle;
        this.achDescription = achDescription;
        this.achRewardPoints = achRewardPoints;
        this.achRewardTitle = achRewardTitle;
        this.achRewardFleetRequisition = achRewardFleetRequisition;
        this.achVisibility = achVisibility;
        this.achCompleted = achCompleted;
    }
    
    
    public int getAchievementID() {
        return achID;
    }

    public void setAchievementID(int achievementID){
        this.achID = achievementID;
    }
    
    public String getCategory1() {
        return achCategory1;
    }

    public void setCategory1(String achCategory1){
        this.achCategory1 = achCategory1;
    }
    
    public String getCategory2() {
        return achCategory2;
    }

    public void setCategory2(String achCategory2){
        this.achCategory2 = achCategory2;
    }
    
    public String getCategory3() {
        return achCategory3;
    }

    public void setCategory3(String achCategory3){
        this.achCategory3 = achCategory3;
    }
    
    public String getTitle() {
        return achTitle;
    }

    public void setTitle(String achTitle) {
        this.achTitle = achTitle;
    }
    
    public String getDescription() {
        return achDescription;
    }

    public void setDescription(String achDescription) {
        this.achDescription = achDescription;
    }
    
    public int getRewardPoints() {
        return achRewardPoints;
    }

    public void setRewardPoints(int achRewardPoints){
        this.achRewardPoints = achRewardPoints;
    }
    
    public String getRewardTitle() {
        return achRewardTitle;
    }

    public void setRewardTitle(String achRewardTitle) {
        this.achRewardTitle = achRewardTitle;
    }

    public Integer getRewardFleetRequisition() {
        return achRewardFleetRequisition;
    }

    public void setRewardFleetRequisition(Integer achRewardFleetRequisition){
        this.achRewardFleetRequisition = achRewardFleetRequisition;
    }
    
    public int getCompleted() {
        return achCompleted;
    }

    public void setCompleted(int achCompleted){
        this.achCompleted = achCompleted;
    }
    
    public String getVisibility() {
        return achVisibility;
    }

    public void setVisibility(String achVisibility) {
        this.achVisibility = achVisibility;
    }
}
