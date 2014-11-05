package com.stryksta.swtorcentral.data;

public class EventItem {
    private int eventImage;
    private String eventStatus;
    private String eventTitle;
    private String eventStart;
    private String eventEnd;
    private String eventDescription;

    public EventItem(int eventImage, String eventStatus, String eventTitle, String eventStart, String eventEnd, String eventDescription) {
        this.eventImage = eventImage;
        this.eventStatus = eventStatus;
        this.eventTitle = eventTitle;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.eventDescription = eventDescription;
    }
    public int getImage() {
        return eventImage;
    }
    public void setEventImage(int eventImage) {
        this.eventImage = eventImage;
    }
    public String getDescription() {
        return eventDescription;
    }
    public void setDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
    public String getTitle() {
        return eventTitle;
    }
    public void setTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }
    public String getStatus() {
        return eventStatus;
    }
    public void setStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }
    public String getStart() {
        return eventStart;
    }
    public void setStart(String eventStart) {
        this.eventStart = eventStart;
    }
    public String getEnd() {
        return eventEnd;
    }
    public void setEnd(String eventEnd) {
        this.eventEnd = eventEnd;
    }
    @Override
    public String toString() {
        return eventTitle + "\n";
    }
}
