package com.stryksta.swtorcentral.data;

public class ServerItem {
    private int imageId;
    private String serverName;
    private String serverStatus;
    
    public ServerItem(int imageId, String serverName, String serverStatus) {
        this.imageId = imageId;
        this.serverName = serverName;
        this.serverStatus = serverStatus;
    }
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public String getserverStatus() {
        return serverStatus;
    }
    public void setserverStatus(String serverStatus) {
        this.serverStatus = serverStatus;
    }
    public String getserverName() {
        return serverName;
    }
    public void setserverName(String serverName) {
        this.serverName = serverName;
    }
    @Override
    public String toString() {
        return serverName + "\n" + serverStatus;
    }
}
