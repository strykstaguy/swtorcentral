package com.stryksta.swtorcentral.data;

public class TutorialItem {

	private String title;
	private String videourl;
	private String imageurl;
	private String smalldescription;
	private String description;

	public TutorialItem(String title, String videourl, String imageurl, String smalldescription, String description) {
    	this.title = title;
    	this.videourl = videourl;
        this.imageurl = imageurl;
        this.smalldescription = smalldescription;
        this.description = description;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVideoURL() {
		return videourl;
	}

	public void setVideoURL(String videourl) {
		this.videourl = videourl;
	}

	public String getImageURL() {
		return imageurl;
	}

	public void setImageURL(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getSmallDescription() {
		return description;
	}

	public void setSmallDescription(String smalldescription) {
		this.smalldescription = smalldescription;
	}

	public String getDescription() {
		return smalldescription;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
