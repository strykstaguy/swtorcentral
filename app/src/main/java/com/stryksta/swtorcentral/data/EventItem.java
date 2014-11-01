package com.stryksta.swtorcentral.data;

public class EventItem {
    private int imageId;
    private String eventTitle;
    private String eventDesc;

    public EventItem(int imageId, String eventTitle, String eventDesc) {
        this.imageId = imageId;
        this.eventTitle = eventTitle;
        this.eventDesc = eventDesc;
    }
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public String geteventDesc() {
        return eventDesc;
    }
    public void seteventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }
    public String geteventTitle() {
        return eventTitle;
    }
    public void seteventTitle(String serverTitle) {
        this.eventTitle = eventTitle;
    }
    @Override
    public String toString() {
        return eventTitle + "\n" + eventDesc;
    }
}
