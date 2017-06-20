/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterflyStorage;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.*;

/**
 *
 * @author Hemanth Boinpally
 */
public class DataStore {
    
    //private HashMap<String,ArrayList<Object>> objectCollection;
    
    //private HashMap<String,HashMap<String,HashSet<Object>>> mapObjectCollection;
    
    private HashMap<String,Customer> custMap;
    private HashMap<String,ArrayList<SiteVisit>> custSiteVisitMap;
    private HashMap<String,HashMap<String,Order>> custOrderMap;
    private HashMap<String,ArrayList<Image>> custImageMap;
    private HashMap<String,Double> totalOrderAmtMap;
    private LocalDateTime startIngestDateTime;
    private LocalDateTime endIngestDateTime;
    
    //Company StartDate September 1, 1999
    
    
    public DataStore()
    {
        custMap  = new HashMap<>();
        custSiteVisitMap = new HashMap<>();
        custOrderMap = new HashMap<>();
        custImageMap = new HashMap<>();
        totalOrderAmtMap = new HashMap<>();
        startIngestDateTime = LocalDateTime.of(3100, Month.SEPTEMBER, 29, 19, 30, 40);
        endIngestDateTime = LocalDateTime.of(1998, Month.SEPTEMBER, 29, 19, 30, 40);
    }

    public HashMap<String, Customer> getCustMap() {
        return custMap;
    }

    public HashMap<String, ArrayList<SiteVisit>> getCustSiteVisitMap() {
        return custSiteVisitMap;
    }

    public HashMap<String, HashMap<String,Order>> getCustOrderMap() {
        return custOrderMap;
    }

    public HashMap<String, ArrayList<Image>> getCustImageMap() {
        return custImageMap;
    }

    public HashMap<String, Double> getTotalOrderAmtMap() {
        return totalOrderAmtMap;
    }

    public LocalDateTime getStartIngestDateTime() {
        return startIngestDateTime;
    }

    public void setStartIngestDateTime(LocalDateTime startIngestDateTime) {
        this.startIngestDateTime = startIngestDateTime;
    }

    public LocalDateTime getEndIngestDateTime() {
        return endIngestDateTime;
    }

    public void setEndIngestDateTime(LocalDateTime endIngestDateTime) {
        this.endIngestDateTime = endIngestDateTime;
    }
    
     public void checkDataStoreIngestDates(LocalDateTime currDateTime)
    {
        if(currDateTime.isBefore(startIngestDateTime))
        {
            startIngestDateTime = currDateTime;
        }
        
        if(currDateTime.isAfter(endIngestDateTime))
        {
            endIngestDateTime=currDateTime;
        }
    }

    
   
    
    
    
}
