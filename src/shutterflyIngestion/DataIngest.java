/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterflyIngestion;

import com.google.gson.*;
import java.math.BigDecimal;
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
            case "IMAGE":
                insertImage(jsonObject, dataStore);
                break;
            case "ORDER":
                insertOrder(jsonObject, dataStore);
                break;
            default:
                System.out.println("UnKnown data");
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
        LocalDateTime currEventTime = getDateTime(eventTime);
        dataStore.checkDataStoreIngestDates(currEventTime);

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

        HashMap<String, String> tags = null;

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
        // Parse String to LocalDateTime using Instant
        LocalDateTime currEventTime = getDateTime(eventTime);
        dataStore.checkDataStoreIngestDates(currEventTime);

        HashMap<String, ArrayList<SiteVisit>> custSiteVistMap = dataStore.getCustSiteVisitMap();

        if (custSiteVistMap.get(key) != null) {
            custSiteVistMap.get(key).add(new SiteVisit(key, customerID, tags, currEventTime));
        } else {
            ArrayList<SiteVisit> listVisits = new ArrayList<>();
            listVisits.add(new SiteVisit(key, customerID, tags, currEventTime));
            custSiteVistMap.put(key, listVisits);
        }
    }

    public void insertImage(JsonObject jsonObject, DataStore dataStore) {
        
        String verb = jsonObject.get("verb").getAsString();
        String key = jsonObject.get("key").getAsString();
        String eventTime = jsonObject.get("event_time").getAsString();
        String customerID = jsonObject.get("customer_id").getAsString();
        String cameraMake = jsonObject.get("camera_make").getAsString();
        String cameraModel = jsonObject.get("camera_model").getAsString();

        LocalDateTime currEventTime = getDateTime(eventTime);
        dataStore.checkDataStoreIngestDates(currEventTime);

        HashMap<String, ArrayList<Image>> custImageMap = dataStore.getCustImageMap();
        if (custImageMap.get(customerID) != null) {
            custImageMap.get(customerID).add(new Image(key, customerID, cameraMake, cameraModel, currEventTime));
        } else {
            ArrayList<Image> listImages = new ArrayList<>();
            listImages.add(new Image(key, customerID, cameraMake, cameraModel, currEventTime));
            custImageMap.put(key, listImages);
        }

    }

    public void insertOrder(JsonObject jsonObject, DataStore dataStore) {

        String verb = jsonObject.get("verb").getAsString();
        String key = jsonObject.get("key").getAsString();
        String eventTime = jsonObject.get("event_time").getAsString();
        String customerID = jsonObject.get("customer_id").getAsString();
        String amountWithCode = jsonObject.get("total_amount").getAsString();
        BigDecimal amount = new BigDecimal(amountWithCode.split(" ")[0]);
        
        
        LocalDateTime currEventTime = getDateTime(eventTime);
        dataStore.checkDataStoreIngestDates(currEventTime);
        // To-do Currency
        
          System.out.println(key);
          HashMap<String, HashMap<String,Order>> custOrderMap = dataStore.getCustOrderMap();
          HashMap<String,BigDecimal> totalOrderAmtMap = dataStore.getTotalOrderAmtMap();
          
        if (custOrderMap.get(customerID) != null) {
            Order order = custOrderMap.get(customerID).get(key);
            if(order ==null)
            {
                custOrderMap.get(customerID).put(key, new Order(key,customerID, amount, currEventTime));
                //Track order amount per customer
                totalOrderAmtMap.get(customerID).add(amount);
                
            }
            else if(currEventTime.isAfter(order.getEventTime()))
            {
                custOrderMap.get(customerID).put(key,new Order(key,customerID, amount, currEventTime) );
                
                totalOrderAmtMap.get(customerID).add(amount.subtract(order.getTotalAmount()));
            }
            
            
        } else {
            HashMap<String,Order> orderMap = new HashMap<>();
            orderMap.put(key,new Order(key,customerID, amount, currEventTime) );
            custOrderMap.put(customerID,orderMap);
            totalOrderAmtMap.put(customerID,amount);
        }

        
    }
    
    
    public LocalDateTime getDateTime(String eventTime)
    {
        Instant instant = Instant.parse(eventTime);

        LocalDateTime currEventTime = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
        
        return currEventTime;
    }
    
   

}
