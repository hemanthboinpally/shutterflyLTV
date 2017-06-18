/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterflyStorage;

/**
 *
 * @author Hemanth Boinpally
 */
import java.time.*;
import java.util.Objects;

public class Image {
    
    private String imageID;
    private String customerID;
    private String cameraMake;
    private String cameraModel;
    private LocalDateTime lastUpdateEventTime;

    public Image() {
        this.imageID = "imageID";
        this.customerID = "customerID";
        this.cameraMake = "cameraMake";
        this.cameraModel = "cameraModel";
        this.lastUpdateEventTime = LocalDateTime.now(); 
    }
    
    
    public Image(String imageID, String customerID, String cameraMake, String cameraModel, LocalDateTime lastUpdateEventTime) {
        this.imageID = imageID;
        this.customerID = customerID;
        this.cameraMake = cameraMake;
        this.cameraModel = cameraModel;
        this.lastUpdateEventTime = lastUpdateEventTime;
    }

    public String getPageID() {
        return imageID;
    }

    public void setPageID(String imageID) {
        this.imageID = imageID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCameraMake() {
        return cameraMake;
    }

    public void setCameraMake(String cameraMake) {
        this.cameraMake = cameraMake;
    }

    public String getCameraModel() {
        return cameraModel;
    }

    public void setCameraModel(String cameraModel) {
        this.cameraModel = cameraModel;
    }

    public LocalDateTime getEventTime() {
        return lastUpdateEventTime;
    }

    public void setEventTime(LocalDateTime lastUpdateEventTime) {
        this.lastUpdateEventTime = lastUpdateEventTime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.imageID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Image other = (Image) obj;
        if (!Objects.equals(this.imageID, other.imageID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Image{" + "imageID=" + imageID + ", customerID=" + customerID + ", cameraMake=" + cameraMake + ", cameraModel=" + cameraModel + ", lastUpdateEventTime=" + lastUpdateEventTime + '}';
    }

   
    
    
    
    
    
}
