package com.stryksta.swtorcentral.data;

public class AchievementsItem {
    public int achievementID;
    public String category1;
    public String category2;
    public String category3;
    public String title;
    public String description;
    public int points;
    public String rewards;
    public int count;
    public int completed;
    public String player;
    private boolean isGroupHeader = false;

    public AchievementsItem(String title) {
		this(0, "", "", "", title, "", 0, "", 0, 0, "");
		isGroupHeader = true;
	}
    
    public AchievementsItem(int achievementID, String category1, String category2, String category3, String title, String description, int points, String rewards, int count, int completed, String player) {
    	this.achievementID = achievementID;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
        this.title = title;
        this.description = description;
        this.points = points;
        this.rewards = rewards;
        this.count = count;
        this.completed = completed;
        this.player = player;
    }
    
    
    public int getAchievementID() {
        return achievementID;
    }

    public void setAchievementID(int achievementID){
        this.achievementID = achievementID;
    }
    
    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1){
        this.category1 = category1;
    }
    
    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2){
        this.category2 = category2;
    }
    
    public String getCategory3() {
        return category3;
    }

    public void setCategory3(String category3){
        this.category3 = category3;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getPoints() {
        return points;
    }

    public void setPoints(int points){
        this.points = points;
    }
    
    public String getRewards() {
        return rewards;
    }

    public void setRewards(String rewards) {
        this.rewards = rewards;
    }
    
    public int getCount() {
        return count;
    }

    public void setCount(int count){
        this.count = count;
    }
    
    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed){
        this.completed = completed;
    }
    
    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }
    
    public boolean isGroupHeader() {
		return isGroupHeader;
	}
	
	public void setGroupHeader(boolean isGroupHeader) {
		this.isGroupHeader = isGroupHeader;
	}
}
