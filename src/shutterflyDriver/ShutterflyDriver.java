/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterflyDriver;

/**
 *
 * @author Hemanth Boinpally
 */
import java.io.BufferedReader;
import java.io.FileReader;
import shutterflyIngestion.DataIngest;
import shutterflyStorage.*;

import com.google.gson.*;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import shutterflySimpleLTV.*;

public class ShutterflyDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic heret

        DataIngest dataIngest = new DataIngest();
        DataStore dataStore = new DataStore();

        String line = null;

        try {
            FileReader fileReader = new FileReader("./input/input.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder sb = new StringBuilder();

            //Reading the input file for events
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }

            bufferedReader.close();

            String events = sb.toString();

            JsonArray jsonArray = (JsonArray) (new JsonParser().parse(events));

            for (JsonElement document : jsonArray) {
                dataIngest.ingest(document, dataStore);
            }

//            //Checking values customers
//            for(Map.Entry set:dataStore.getCustMap().entrySet())
//            {
//                System.out.println(set.getKey());
//                System.out.println(set.getValue());
//                
//            }
//            
//            // cheaking Image data
//            for(Map.Entry set:dataStore.getCustImageMap().entrySet())
//            {
//                System.out.println(set.getKey());
//                System.out.println(set.getValue());
//                
//            }
//            
//            //checking customers
//            for(Map.Entry set:dataStore.getCustSiteVisitMap().entrySet())
//            {
//                System.out.println(set.getKey());
//                System.out.println(set.getValue());
//                
//            }
//            
//            //checking Orders
//            for(Map.Entry set:dataStore.getCustOrderMap().entrySet())
//            {
//                System.out.println(set.getKey());
//
//                System.out.println(set.getValue());
//                
//            }
          //  System.out.println("----------Data Store values ------------");
            BufferedWriter bufferWriter = new BufferedWriter(new FileWriter("./output/output.txt"));
            SimpleLTV ltvCalculate = new SimpleLTV();
            bufferWriter.write("CustomerID            LTV\n");
            for (LTVNode custltv : ltvCalculate.topXSimpleLTVCustomers(2, dataStore)) {
                //System.out.println(custltv.getCustomerID() + "  --  " + custltv.getLtv());
                bufferWriter.write(custltv.getCustomerID() + "    " + custltv.getLtv()+"\n");
            }
            bufferWriter.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

    }

}
