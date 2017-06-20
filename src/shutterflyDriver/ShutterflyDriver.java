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
            FileReader fileReader = new FileReader("./input/test_input.txt");
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

            // write the customerID and LTV in the file
            BufferedWriter bufferWriter = new BufferedWriter(new FileWriter("./output/output.txt"));
            
            SimpleLTVCalculator ltvCalculate = new SimpleLTVCalculator();
            String format = "%-20s %-20s%n";
            bufferWriter.write(String.format(format, "CustomerID","LTV"));
            
            for (LTVNode custltv : ltvCalculate.topXSimpleLTVCustomers(7, dataStore)) {

                bufferWriter.write(String.format(format, custltv.getCustomerID(),custltv.getLtv()));
            }
            bufferWriter.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
