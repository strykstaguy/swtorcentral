package com.stryksta.swtorcentral.data;


public class AdvancedClassesItem {
	private int imgAdvancedClass1;
	private int imgAdvancedClass2;
	private int idAdvancedClass1;
	private int idAdvancedClass2;
    private String txtAdvancedClass1;
    private String txtAdvancedClass1Desc;
    private String txtAdvancedClass2;
    private String txtAdvancedClass2Desc;
    private String txtResource;
    private String apc;

    public AdvancedClassesItem( int idAdvancedClass1, int imgAdvancedClass1, String txtAdvancedClass1, String txtAdvancedClass1Desc, int idAdvancedClass2, int imgAdvancedClass2, String txtAdvancedClass2, String txtAdvancedClass2Desc) {
    	this.idAdvancedClass1 = idAdvancedClass1;
        this.imgAdvancedClass1 = imgAdvancedClass1;
        this.txtAdvancedClass1 = txtAdvancedClass1;
        this.txtAdvancedClass1Desc = txtAdvancedClass1Desc;
        this.idAdvancedClass2 = idAdvancedClass2;
        this.imgAdvancedClass2 = imgAdvancedClass2;
        this.txtAdvancedClass2 = txtAdvancedClass2;
        this.txtAdvancedClass2Desc = txtAdvancedClass2Desc;
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

    public String gettxtAdvancedClass1Desc() {
        return txtAdvancedClass1Desc;
    }

    public void settxtAdvancedClass1Desc(String txtAdvancedClass1Desc) {
        this.txtAdvancedClass1Desc = txtAdvancedClass1Desc;
    }

    public String gettxtAdvancedClass2Desc() {
        return txtAdvancedClass2Desc;
    }

    public void settxtAdvancedClass2Desc(String txtAdvancedClass2Desc) {
        this.txtAdvancedClass2Desc = txtAdvancedClass2Desc;
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
	@Override
	public String toString() {
		return "ID!!!: " + idAdvancedClass1;
	}
}
