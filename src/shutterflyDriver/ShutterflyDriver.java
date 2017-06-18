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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class ShutterflyDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        // TODO code application logic heret
        
        DataIngest dataIngest = new DataIngest();
        DataStore dataStore = new DataStore();
        
        
        String line = null;

        try {
            FileReader fileReader = new FileReader("./input/events.txt");
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
                dataIngest.ingest(document,dataStore);
            }
            
            //Checking values
            for(Map.Entry set:dataStore.getCustMap().entrySet())
            {
                System.out.println(set.getKey());
                System.out.println(set.getValue());
                
            }

        } catch (FileNotFoundException ex) {
               System.out.println(ex.toString());
        }
        catch(IOException ex)
        {
            System.out.println(ex.toString());
        }

    }

}
