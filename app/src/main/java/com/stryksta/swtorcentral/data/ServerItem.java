package com.stryksta.swtorcentral.data;

public class ServerItem {
    private int imageId;
    private String serverName;
    private String serverStatus;
    private String serverType;
    private String serverZone;
    
    public ServerItem(int imageId, String serverName, String serverStatus, String serverType, String serverZone) {
        this.imageId = imageId;
        this.serverName = serverName;
        this.serverStatus = serverStatus;
        this.serverType = serverType;
        this.serverZone = serverZone;
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

    public String getserverType() {
        return serverType;
    }
    public void setserverType(String serverType) {
        this.serverType = serverType;
    }

    public String getserverZone() {
        return serverZone;
    }
    public void setserverZone(String serverZone) {
        this.serverZone = serverZone;
    }
    @Override
    public String toString() {
        return serverName + "\n" + serverStatus;
    }
}
