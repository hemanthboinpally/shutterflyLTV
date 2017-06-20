/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterflySimpleLTV;

/**
 *
 * @author Hemanth Boinpally
 */
public class LTVNode
{
    String customerID;
    double ltv;
    
    public LTVNode(String customerID,double ltv)
    {
        this.customerID = customerID;
        this.ltv = ltv;
    }

    public String getCustomerID() {
        return customerID;
    }

    public double getLtv() {
        return ltv;
    }
    
    
    
}
