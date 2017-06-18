/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterflyStorage;

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
    private HashMap<String,ArrayList<Order>> custOrderMap;
    private HashMap<String,ArrayList<Image>> custImageMap;
    
    public DataStore()
    {
        custMap  = new HashMap<>();
        custSiteVisitMap = new HashMap<>();
        custOrderMap = new HashMap<>();
        custImageMap = new HashMap<>();
        
        
    }

    public HashMap<String, Customer> getCustMap() {
        return custMap;
    }

    public HashMap<String, ArrayList<SiteVisit>> getCustSiteVisitMap() {
        return custSiteVisitMap;
    }

    public HashMap<String, ArrayList<Order>> getCustOrderMap() {
        return custOrderMap;
    }

    public HashMap<String, ArrayList<Image>> getCustImageMap() {
        return custImageMap;
    }

    
   
    
    
    
}
