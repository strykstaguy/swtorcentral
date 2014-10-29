package com.stryksta.swtorcentral.data;

public class AchievementCategoryItem {
    public String category;
    public int completed;
    public int total;
    
    public AchievementCategoryItem(String category, int completed, int total) {
    	this.category = category;
        this.completed = completed;
        this.total = total;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }
    
    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed){
        this.completed = completed;
    }
    
    public int getTotal() {
        return total;
    }

    public void setTotal(int total){
        this.total = total;
    }
}
