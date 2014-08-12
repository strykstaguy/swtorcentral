package com.stryksta.swtorcentral.data;


public class ClassItem {
	private int imgAdvancedClass1;
	private int imgAdvancedClass2;
	private int idAdvancedClass1;
	private int idAdvancedClass2;
    private String txtAdvancedClass1;
    private String txtAdvancedClass2;
    private String txtClass;
    private String txtResource;
    private int idClass;
    private boolean isGroupHeader = false;
    
    public ClassItem(String title) {
		this(title, 0, "", 0, 0, "", 0, 0, "");
		isGroupHeader = true;
	}
    
    public ClassItem(String txtClass, int idClass, String txtResource, int idAdvancedClass1, int imgAdvancedClass1, String txtAdvancedClass1, int idAdvancedClass2, int imgAdvancedClass2, String txtAdvancedClass2) {
    	this.txtClass = txtClass;
    	this.idClass = idClass;
    	this.txtResource = txtResource;
    	this.idAdvancedClass1 = idAdvancedClass1;
        this.imgAdvancedClass1 = imgAdvancedClass1;
        this.txtAdvancedClass1 = txtAdvancedClass1;
        this.idAdvancedClass2 = idAdvancedClass2;
        this.imgAdvancedClass2 = imgAdvancedClass2;
        this.txtAdvancedClass2 = txtAdvancedClass2;
    }
    
    public String gettxtClass() {
        return txtClass;
    }
    
    public void settxtClass(String txtClass) {
    	this.txtClass = txtClass;
    }
    
    public int getIdClass() {
        return idClass;
    }
    
    public void getIdClass1(int idClass) {
        this.idClass = idClass;
    }
    
    public int getAdvancedClassID1() {
        return idAdvancedClass1;
    }
    
    public void getAdvancedClassID1(int idAdvancedClass1) {
        this.idAdvancedClass1 = idAdvancedClass1;
    }
    
    public int getAdvancedClassID2() {
        return idAdvancedClass2;
    }
    
    public void getAdvancedClassID2(int idAdvancedClass2) {
        this.idAdvancedClass2 = idAdvancedClass2;
    }
    
    public int getimgAdvancedClass1() {
        return imgAdvancedClass1;
    }
    
    public void getimgAdvancedClass1(int imgAdvancedClass1) {
        this.imgAdvancedClass1 = imgAdvancedClass1;
    }
    
    public int getimgAdvancedClass2() {
        return imgAdvancedClass2;
    }
    
    public void getimgAdvancedClass2(int imgAdvancedClass2) {
        this.imgAdvancedClass2 = imgAdvancedClass2;
    }
    
    public String gettxtAdvancedClass1() {
        return txtAdvancedClass1;
    }
    
    public void settxtAdvancedClass1(String txtAdvancedClass1) {
    	this.txtAdvancedClass1 = txtAdvancedClass1;
    }
    
    public String gettxtAdvancedClass2() {
        return txtAdvancedClass2;
    }
    
    public void settxtResource(String txtResource) {
    	this.txtResource = txtResource;
    }
    
    public String gettxtResource() {
        return txtResource;
    }
    
    public void settxtAdvancedClass2(String txtAdvancedClass2) {
    	this.txtAdvancedClass2 = txtAdvancedClass2;
    }
    
    public boolean isGroupHeader() {
		return isGroupHeader;
	}
	
	public void setGroupHeader(boolean isGroupHeader) {
		this.isGroupHeader = isGroupHeader;
	}
	
	@Override
	public String toString() {
		return "ID!!!: " + idAdvancedClass1;
	}
}
