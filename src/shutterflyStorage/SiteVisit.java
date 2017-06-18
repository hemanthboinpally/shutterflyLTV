/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterflyStorage;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author Hemanth Boinpally
 */
public class SiteVisit {
    
    private String pageID;
    private String customerID;
    private HashMap<String,String> tags;
    private LocalDateTime lastUpdateEventTime;
   

    public SiteVisit() {
        
    }

    public SiteVisit(String pageID, String customerID, HashMap<String, String> tags, LocalDateTime lastUpdateEventTime) {
        this.pageID = pageID;
        this.customerID = customerID;
        this.tags = tags;
        this.lastUpdateEventTime = lastUpdateEventTime;
    }

    public String getPageID() {
        return pageID;
    }

    public void setPageID(String pageID) {
        this.pageID = pageID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public HashMap<String, String> getTags() {
        return tags;
    }

    public void setTags(HashMap<String, String> tags) {
        this.tags = tags;
    }

    public LocalDateTime getEventTime() {
        return lastUpdateEventTime;
    }

    public void setEventTime(LocalDateTime lastUpdateEventTime) {
        this.lastUpdateEventTime = lastUpdateEventTime;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.pageID);
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
        final SiteVisit other = (SiteVisit) obj;
        if (!Objects.equals(this.pageID, other.pageID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SiteVisit{" + "pageID=" + pageID + ", customerID=" + customerID + ", tags=" + tags + ", lastUpdateEventTime=" + lastUpdateEventTime + '}';
    }
    
    
    
    
    
}

