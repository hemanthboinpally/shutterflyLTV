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
 * This class holds the data of the events that are ingested for LTV calculation
 *
 * @author Hemanth Boinpally
 */
public class DataStore {

    private HashMap<String, Customer> custMap;
    private HashMap<String, ArrayList<SiteVisit>> custSiteVisitMap;
    private HashMap<String, HashMap<String, Order>> custOrderMap;
    private HashMap<String, ArrayList<Image>> custImageMap;
    private HashMap<String, Double> totalOrderAmtMap;
    private LocalDateTime startIngestDateTime;
    private LocalDateTime endIngestDateTime;

    //Company StartDate September 1, 1999
    /**
     * Initializing the data structures
     *
     * The ingest start and end date are given a dummy dates
     */
    public DataStore() {
        custMap = new HashMap<>();
        custSiteVisitMap = new HashMap<>();
        custOrderMap = new HashMap<>();
        custImageMap = new HashMap<>();
        totalOrderAmtMap = new HashMap<>();
        startIngestDateTime = LocalDateTime.of(3100, Month.SEPTEMBER, 29, 19, 30, 40);
        endIngestDateTime = LocalDateTime.of(1998, Month.SEPTEMBER, 29, 19, 30, 40);
    }

    /**
     * This method returns a map of CustomerID and Customer Object
     *
     * @return HashMap
     */
    public HashMap<String, Customer> getCustMap() {
        return custMap;
    }

    /**
     * This method returns a map of CustomerID and SiteVist List
     *
     * @return Customer based SiteVisit list
     */
    public HashMap<String, ArrayList<SiteVisit>> getCustSiteVisitMap() {
        return custSiteVisitMap;
    }

    /**
     * This method returns a map of CustomerID and Order information. OrderID is
     * also hashed for faster performance
     *
     * @return customerID and Order Map
     */
    public HashMap<String, HashMap<String, Order>> getCustOrderMap() {
        return custOrderMap;
    }

    /**
     * This method returns a map of CustomerID and Image List.
     *
     * @return Images uploaded by customers
     */
    public HashMap<String, ArrayList<Image>> getCustImageMap() {
        return custImageMap;
    }

    /**
     * This method returns a map of CustomerID and totalAmount
     *
     * @return totalAmount per customer
     */
    public HashMap<String, Double> getTotalOrderAmtMap() {
        return totalOrderAmtMap;
    }

    /**
     * Data Store oldest date is retrieved
     *
     * @return oldest date
     */
    public LocalDateTime getStartIngestDateTime() {
        return startIngestDateTime;
    }

    /**
     * Data Store oldest date is set
     *
     * @param startIngestDateTime
     */
    public void setStartIngestDateTime(LocalDateTime startIngestDateTime) {
        this.startIngestDateTime = startIngestDateTime;
    }

    /**
     * Retrieves the latest date in the Data Store
     *
     * @return
     */
    public LocalDateTime getEndIngestDateTime() {
        return endIngestDateTime;
    }

    /**
     * Sets the latest date in the Data Store
     *
     * @param endIngestDateTime
     */
    public void setEndIngestDateTime(LocalDateTime endIngestDateTime) {
        this.endIngestDateTime = endIngestDateTime;
    }

    /**
     * This method is used to check if the dateTime passed is either the oldest
     * date or the latest date
     *
     * @param currDateTime
     */
    public void checkDataStoreIngestDates(LocalDateTime currDateTime) {
        if (currDateTime.isBefore(startIngestDateTime)) {
            startIngestDateTime = currDateTime;
        }

        if (currDateTime.isAfter(endIngestDateTime)) {
            endIngestDateTime = currDateTime;
        }
    }

}
