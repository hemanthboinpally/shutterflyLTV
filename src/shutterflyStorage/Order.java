/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterflyStorage;


import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Hemanth Boinpally
 */
public class Order {
    
    private String customerID;
    private double totalAmount;
    private LocalDateTime  lastUpdateEventTime;

    
    private String orderID;

    /**
     *
     */
    public Order() {
    }

    /**
     *
     * @param orderID
     * @param customerID
     * @param totalAmount
     * @param lastUpdateEventTime
     */
    public Order(String orderID, String customerID, double totalAmount, LocalDateTime lastUpdateEventTime) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.totalAmount = totalAmount;
        this.lastUpdateEventTime = lastUpdateEventTime;
    }

    /**
     *
     * @return
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     *
     * @param orderID
     */
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     *
     * @return
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     *
     * @param customerID
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    /**
     *
     * @return
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    /**
     *
     * @param totalAmount
     */
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     *
     * @return
     */
    public LocalDateTime getEventTime() {
        return lastUpdateEventTime;
    }

    /**
     *
     * @param lastUpdateEventTime
     */
    public void setEventTime(LocalDateTime lastUpdateEventTime) {
        this.lastUpdateEventTime = lastUpdateEventTime;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.orderID);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
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
        final Order other = (Order) obj;
        if (!Objects.equals(this.orderID, other.orderID)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", customerID=" + customerID + ", totalAmount=" + totalAmount + ", lastUpdateEventTime=" + lastUpdateEventTime + '}';
    }
    
    
    
    
    
}
