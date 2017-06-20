/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterflySimpleLTV;

/**
 *  This class is used to hold customer and LTV information.
 *  Future enhancements for more information retrieval can be implemented here.
 * 
 * @author Hemanth Boinpally
 */
public class LTVNode
{
    String customerID;
    double ltv;
    
    /**
     * Parameterized constructor 
     * 
     * @param customerID
     * @param ltv
     */
    public LTVNode(String customerID,double ltv)
    {
        this.customerID = customerID;
        this.ltv = ltv;
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
     * This method will return ltv 
     *
     * @return ltv
     */
    public double getLtv() {
        return ltv;
    }
    
    
    
}
