
package com.AirTravel;

/**
 * Created by abhilashuday,Darshan Masti Prakash on 4/20/17.
 */


import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.Text;

/**
 * @author mac
 */

public class AirTravelReducer extends Reducer<Text, Text, Text, Text> {
    // The Karmasphere Studio Workflow Log displays logging from Apache Commons Logging, for example:
    // private static final Log LOG = LogFactory.getLog("org.smaple.HomeworkReducer");

    String file="./inputfiles/airline-safety.csv"; // generic path for csv file
    long count = -1;
    private static Hashtable<String, Integer> htDelay = new Hashtable<String, Integer>();
    private static Hashtable<String, Integer> htCancelFlights = new Hashtable<String, Integer>();
    private static Hashtable<String, Integer> htAirportDelay = new Hashtable<String, Integer>();
    private static Hashtable<String, Integer> htAirlineDelay = new Hashtable<String, Integer>();
    private static HashMap<String, String> htAirlineSafety = new HashMap<String,String>();
	static enum InputCount
	{
		PerformanceCount
	}
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        //System.out.println("Key:" + key.toString());
        //Store delays, Cancel flights for source - destination, Process airline flight delays, process flight delays for each airline
        ProcessCancelFlightsAndFlightsDelayAndAirlineDelay(key, values);
        //System.out.println(htAirlineSafety=StoreAirlineSafety(file));
        

        if(count == -1) count = context.getCounter(InputCount.PerformanceCount).getValue() - 1;
        else count--;       
        if(count == 0)
        {
           //Performance analysis is done. get the safety data and send performance and safety hashtable to algorithm
        }
               
    }

    private void ProcessCancelFlightsAndFlightsDelayAndAirlineDelay(Text key, Iterable<Text> values) {
        try {
            String[] keyColl = key.toString().split(";");
            String newKey = keyColl[0] + "," + keyColl[1];

            String src = keyColl[0];
            String dest = keyColl[1];

            String airline = keyColl[2];
            Integer newVal = 0;
            Integer srcDelay = 0, destDelay = 0, airlineDelay = 0;

            for (Text val : values) {

                String[] coll = val.toString().split(";");
                String delayKey = coll[1] + ":" + coll[2]; //Key : Month:Day
                if (newKey.equals("AUS,HRL")) {
                    System.out.println();
                }
                Integer delayVal = (coll[14].equals("") ? 0 : Integer.parseInt(coll[14])) + (coll[17].equals("") ? 0 : Integer.parseInt(coll[17]));
                //Value: DepDelay + ArrDelay (coll[14] + coll[17])
                StoreDelaysToHashtable(delayKey, delayVal); //Store delay in hashtable

                newVal += coll[19].equals("") ? 0 : Integer.parseInt(coll[19]);
                srcDelay += coll[14].equals("") ? 0 : Integer.parseInt(coll[14]);
                destDelay += coll[17].equals("") ? 0 : Integer.parseInt(coll[17]);
                airlineDelay += (coll[14].equals("") ? 0 : Integer.parseInt(coll[14])) + (coll[17].equals("") ? 0 : Integer.parseInt(coll[17]));
            }

            //Cancel flights
            //System.out.println(newKey);
            //System.out.println(newVal);
            StoreCancelFlightsToHashtable(newKey, newVal);

            //Source and Destination delay
            //System.out.println(src + ":" + srcDelay);
            //System.out.println(dest + ":" + destDelay);
            StoreSrcDestDelayToHashtable(src, srcDelay, dest, destDelay);

            //Airline delay
            System.out.println(airline + ":" + airlineDelay);
            StoreAirlineDelayToHashtable(airline, airlineDelay);

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + ";Exception in ProcessCancelFlightsAndFlightsDelayAndAirlineDelay method");
        }
    }

    private static void StoreDelaysToHashtable(String key, Integer val) {
        if (htDelay.containsKey(key)) {
            Integer del = val + htDelay.get(key);
            htDelay.put(key, del + val);
        } else {
            htDelay.put(key, val);
        }
    }

    private static void StoreCancelFlightsToHashtable(String key, Integer val) {
        if (htCancelFlights.containsKey(key)) {
            Integer del = val + htCancelFlights.get(key);
            htCancelFlights.put(key, del + val);
        } else {
            htCancelFlights.put(key, val);
        }
    }


    private static void StoreSrcDestDelayToHashtable(String src, Integer srcDelay, String dest, Integer destDelay) {
        //source to the list
        if (htAirportDelay.containsKey(src)) {
            Integer del = srcDelay + htAirportDelay.get(src);
            htAirportDelay.put(src, del + srcDelay);
        } else {
            htAirportDelay.put(src, srcDelay);
        }

        //Destination to the list
        if (htAirportDelay.containsKey(dest)) {
            Integer del = destDelay + htAirportDelay.get(dest);
            htAirportDelay.put(dest, del + destDelay);
        } else {
            htAirportDelay.put(dest, destDelay);
        }
    }

    private static void StoreAirlineDelayToHashtable(String key, Integer val) {
        if (htAirlineDelay.containsKey(key)) {
            Integer del = val + htAirlineDelay.get(key);
            htAirlineDelay.put(key, del + val);
        } else {
            htAirlineDelay.put(key, val);
        }
    }

    // returns Hashmap with airline as key and " incidents;accidents "  as value
    public static HashMap<String,String> StoreAirlineSafety(String path){
        AirSafetyExtractor ase=new AirSafetyExtractor();
        htAirlineSafety=ase.extraction(path);
        return htAirlineSafety;
    }

}
