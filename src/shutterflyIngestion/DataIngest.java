/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterflyIngestion;

import com.google.gson.*;
import java.util.HashMap;
import shutterflyStorage.*;
import java.time.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Hemanth Boinpally
 */
public class DataIngest {

    public void ingest(JsonElement document, DataStore dataStore) {
        JsonObject jsonObject = document.getAsJsonObject();
        String objectType = jsonObject.get("type").getAsString();

        switch (objectType) {
            case "CUSTOMER":
                insertCustomer(jsonObject, dataStore);
                break;
            case "SITE_VISIT":
                insertSiteVisit(jsonObject, dataStore);
                break;

        }
    }

    public void insertCustomer(JsonObject jsonObject, DataStore dataStore) {
        String verb = jsonObject.get("verb").getAsString();
        String key = jsonObject.get("key").getAsString();
        String eventTime = jsonObject.get("event_time").getAsString();
        String lastName = jsonObject.get("last_name").getAsString();
        String city = jsonObject.get("adr_city").getAsString();
        String state = jsonObject.get("adr_state").getAsString();

        // Parese String to LocalDateTime using Instant
        Instant instant = Instant.parse(eventTime);

        LocalDateTime currEventTime = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));

        HashMap<String, Customer> custMap = dataStore.getCustMap();

        if (custMap.get(key) != null) {
            Customer customer = custMap.get(key);
            LocalDateTime custStoreTime = customer.getLastUpdateEventTime();

            //Insert the latestRecord into the dataStore
            if (currEventTime.isAfter(custStoreTime)) {
                custMap.put(key, new Customer(key, lastName, city, state, currEventTime));
            }

        } else {
            custMap.put(key, new Customer(key, lastName, city, state, currEventTime));
        }
    }

    public void insertSiteVisit(JsonObject jsonObject, DataStore dataStore) {
        
        String verb = jsonObject.get("verb").getAsString();
        String key = jsonObject.get("key").getAsString();
        String eventTime = jsonObject.get("event_time").getAsString();
        String customerID = jsonObject.get("customer_id").getAsString();
        
        HashMap<String,String> tags = null;
        
        
        //To-do tags
//        JsonArray jsonArray = jsonObject.get("tags").getAsJsonArray();
//
//        HashMap<String, String> tags = new HashMap<>();
//
//        for (JsonElement document : jsonArray) {
//            JsonObject tagsObject = document.getAsJsonObject();
//            tagsObject.
//            for(Iterator keyValue = tagsObject.keys(); itr.hasNext();) 
//            {
//                tagsObject.get(keyValue.next());
//            }
//
//        }
        // Parese String to LocalDateTime using Instant
        Instant instant = Instant.parse(eventTime);

        LocalDateTime currEventTime = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
        
        HashMap<String, ArrayList<SiteVisit>> custSiteVistMap = dataStore.getCustSiteVisitMap();
        
        if(custSiteVistMap.get(key)!=null)        
             custSiteVistMap.get(key).add(new SiteVisit(key,customerID,tags,currEventTime));
        else
        {
            ArrayList<SiteVisit> listVisits = new ArrayList<>();
            listVisits.add(new SiteVisit(key,customerID,tags,currEventTime));
            custSiteVistMap.put(key,listVisits);
        }
    }

    
    public void insertImage(JsonObject jsonObject,DataStore dataStore)
    {
        String verb = jsonObject.get("verb").getAsString();
        String key = jsonObject.get("key").getAsString();
        String eventTime = jsonObject.get("event_time").getAsString();
        String customerID = jsonObject.get("customer_id").getAsString();
        
        
        
        
        
    }
    public void insertOrder(JsonObject jsonObject, DataStore dataStore)
    {
        
    }
    
}
