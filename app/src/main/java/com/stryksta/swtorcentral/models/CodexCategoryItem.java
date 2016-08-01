package com.stryksta.swtorcentral.models;

public class CodexCategoryItem {
    public String cdxCategory;

    public CodexCategoryItem(String cdxCategory) {
    	this.cdxCategory = cdxCategory;
    }
    
    public String getCategory() {
        return cdxCategory;
    }

    public void setCategory(String cdxCategory){
        this.cdxCategory = cdxCategory;
    }

}
