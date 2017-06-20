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
import java.util.List;
import shutterflyStorage.*;

/**
 *
 * This class is used to calculate the Simple LTV
 *
 * @author Hemanth Boinpally
 */
public class SimpleLTVCalculator {

    /**
     * Get top X Customers sorted by LTV
     *
     * @param x
     * @param dataStore
     * @return list of LTV Nodes
     */
    public List<LTVNode> topXSimpleLTVCustomers(int x, DataStore dataStore) {

        int timePeriod = 10;
        ArrayList<LTVNode> custLTVList = new ArrayList<>();
        long numberOfWeeks = ChronoUnit.WEEKS.between(dataStore.getStartIngestDateTime(), dataStore.getEndIngestDateTime());
        int noOfCustomers = 0;

        for (String customerID : dataStore.getCustMap().keySet()) {

            double custExpendPerVisit = getCustExpendPerVisit(customerID, dataStore);
            double visitsPerWeek = getCustVisitPerWeek(customerID, dataStore, numberOfWeeks);
            double avg = custExpendPerVisit * visitsPerWeek;
            double simpleLTV = 52 * avg * timePeriod;
            custLTVList.add(new LTVNode(customerID, simpleLTV));
            noOfCustomers++;
        }

        // Sort to get the top customers
        Collections.sort(custLTVList, new Comparator<LTVNode>() {
            @Override
            public int compare(LTVNode t1, LTVNode t2) {
                return Double.compare(t2.getLtv(), t1.getLtv());

            }
        });

        // Handling fuzzy input
        if (x > noOfCustomers || x < 0) {
            x = noOfCustomers;
        }

        return custLTVList.subList(0, x);
    }

    /**
     * This method will return customer expenditure per visit. It will be zero
     * if no orders.
     *
     * @param customerID
     * @param dataStore
     * @return customerExpenditure
     */
    public double getCustExpendPerVisit(String customerID, DataStore dataStore) {
        HashMap<String, Order> orderMap = dataStore.getCustOrderMap().get(customerID);
        double amount = 0;
        if (orderMap == null) {
            System.out.println(customerID + " has no orders");
            return 0;
        }

        for (Order order : orderMap.values()) {
            amount += order.getTotalAmount();
        }

        return amount;

    }

    /**
     * This method will return customer visits per week. It will be zero if no
     * visits.
     *
     * @param customerID
     * @param dataStore
     * @param noOfWeeks
     * @return visitsPerWeek
     */
    public double getCustVisitPerWeek(String customerID, DataStore dataStore, long noOfWeeks) {
        ArrayList<SiteVisit> siteVisit = dataStore.getCustSiteVisitMap().get(customerID);

        if (siteVisit == null) {
            System.out.println(customerID + " has no site visits");
            return 0;
        }
        int noOfVisits = siteVisit.size();

        return (double) noOfVisits / noOfWeeks;
    }
}
