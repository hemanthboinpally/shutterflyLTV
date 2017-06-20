/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterflyStorage;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Store the customer information
 *
 * @author Hemanth Boinpally
 */
public class Customer {

    private String customerID;
    private String lastName;
    private String city;
    private String state;
    private LocalDateTime lastUpdateEventTime;

    /**
     * Parameterized Constructor
     *
     * @param customerID
     * @param lastName
     * @param city
     * @param state
     * @param lastUpdateEventTime
     */
    public Customer(String customerID, String lastName, String city, String state, LocalDateTime lastUpdateEventTime) {
        this.customerID = customerID;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
        this.lastUpdateEventTime = lastUpdateEventTime;
    }

    /**
     * No-arg constructor
     */
    public Customer() {

        this.customerID = "NoID";
        this.lastName = "NoName";
        this.city = "NoCity";
        this.state = "NoState";
    }

    /**
     * This method will return customerID
     *
     * @return customerID
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * This method will set customerID
     *
     * @param customerID
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    /**
     * This method will return customer last Name
     *
     * @return LastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method will set customer last Name
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * This method is used to get the latest date the customer information was
     * altered
     *
     * @return localDateTime
     */
    public LocalDateTime getLastUpdateEventTime() {
        return lastUpdateEventTime;
    }

    /**
     * This method is used to set the latest date the customer information was
     * altered
     *
     * @param lastUpdateEventTime
     */
    public void setLastUpdateEventTime(LocalDateTime lastUpdateEventTime) {
        this.lastUpdateEventTime = lastUpdateEventTime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.customerID);
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
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.customerID, other.customerID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerID=" + customerID + ", lastName=" + lastName + ", city=" + city + ", state=" + state + ", lastUpdateEventTime=" + lastUpdateEventTime + '}';
    }

}
