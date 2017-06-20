/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterflySimpleLTV;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;
import shutterflyStorage.*;


/**
 *
 * @author Hemanth Boinpally
 */


public class SimpleLTV {
    
    public ArrayList<LTVNode> topXSimpleLTVCustomers(int x,DataStore dataStore)
    {
        int timePeriod = 10;
        ArrayList<LTVNode> custLTVList= new ArrayList<>();
        long numberOfWeeks = ChronoUnit.WEEKS.between(dataStore.getStartIngestDateTime(), dataStore.getEndIngestDateTime());
 
        
        for(String customerID:dataStore.getCustMap().keySet())
        {
            
            double custExpendPerVisit = getCustExpendPerVisit(customerID,dataStore);
            double visitsPerWeek = getCustVisitPerWeek(customerID,dataStore,numberOfWeeks);
            double avg = custExpendPerVisit * visitsPerWeek;
            double simpleLTV = 52* avg *timePeriod; 
            custLTVList.add(new LTVNode(customerID, simpleLTV));
            
        }
        
        Collections.sort(custLTVList, new Comparator<LTVNode>(){
            @Override
            public int compare(LTVNode t1, LTVNode t2) {
               double diff = t2.ltv-t1.ltv; 
                if(diff>0)
                {
                    return 1;
                }
                else if(diff<0)
                {
                    return 0;
                }
                return -1;
            }
        });
        
        
        return custLTVList;
    }
    
    
    public double getCustExpendPerVisit(String customerID,DataStore dataStore)
    {
        HashMap<String,Order> orderMap = dataStore.getCustOrderMap().get(customerID);
        double amount = 0;
        if(orderMap == null)
        {
            System.out.println(customerID + " has no orders");
            return 0;
        }
        
        for(Order order:orderMap.values())
        {
            amount +=order.getTotalAmount();
        }
        
        return amount;
        
    }
    
     public double getCustVisitPerWeek(String customerID,DataStore dataStore,long noOfWeeks)
     {
        ArrayList<SiteVisit> siteVisit = dataStore.getCustSiteVisitMap().get(customerID);
        
        if(siteVisit==null)
        {
            System.out.println(customerID + " has no site visits");
            return 0;
        }
        int noOfVisits =  siteVisit.size();
        
        
        return (double) noOfVisits/noOfWeeks;
     }
}
