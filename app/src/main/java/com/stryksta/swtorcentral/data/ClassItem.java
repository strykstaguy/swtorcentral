package com.stryksta.swtorcentral.data;


public class ClassItem {
    private String txtClass;
    private String txtResource;
    private String txtApc;
    private String txtDescription;
    private int idClass;
    private int imgClass;

    public ClassItem(String txtClass, int imgClass, int idClass, String txtResource, String txtDescription, String txtApc) {
    	this.txtClass = txtClass;
    	this.idClass = idClass;
        this.imgClass = imgClass;
    	this.txtResource = txtResource;
        this.txtApc = txtApc;
    }
    
    public String getClassName() {
        return txtClass;
    }

    public void setClassName(String txtClass) {
        this.txtClass = txtClass;
    }

    public String getClassDescription() {
        return txtDescription;
    }

    public void setClassDescription(String txtDescription) {
        this.txtDescription = txtDescription;
    }

    public int getClassImage() {
        return imgClass;
    }

    public void getClassImage(int imgClass) {
        this.imgClass = imgClass;
    }

    public String getClassResource() {
        return txtResource;
    }

    public void setResource(String txtResource) {
        this.txtResource = txtResource;
    }

    public int getClassID() {
        return idClass;
    }
    
    public void setClassID(int idClass) {
        this.idClass = idClass;
    }

    public String geAPC() {
        return txtApc;
    }

    public void setAPC(String txtApc) {
        this.txtApc = txtApc;
    }
	
	@Override
	public String toString() {
		return "Class: " + txtClass;
	}
}
