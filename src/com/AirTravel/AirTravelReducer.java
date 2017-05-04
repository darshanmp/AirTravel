
package com.AirTravel;

/**
 * Created by abhilashuday,Darshan Masti Prakash on 4/20/17.
 */


import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.io.Text;

import javax.swing.*;

/**
 * @author mac
 */

public class AirTravelReducer extends Reducer<Text, Text, Text, Text> {
    // The Karmasphere Studio Workflow Log displays logging from Apache Commons Logging, for example:
    // private static final Log LOG = LogFactory.getLog("org.smaple.HomeworkReducer");

    static String file = "./inputfiles/airline-safety.csv"; // generic path for csv file
    private MultipleOutputs<Text, Text> multipleOutputs;
    private static TreeMap<String, Double> htDelay = null;
    private static TreeMap<String, Double> htCancelFlights = null;
    private static TreeMap<String, Double> htAirportDelay = null;
    private static TreeMap<String, Double> htAirlineDelay = null;

    static Double minDelay = 0.0;
    static Double maxDelay = 0.0;

    @Override
    public void setup(Context context) {
        multipleOutputs = new MultipleOutputs<Text, Text>(context);
        htDelay = new TreeMap<String, Double>();
        htCancelFlights = new TreeMap<String, Double>();
        htAirportDelay = new TreeMap<String, Double>();
        htAirlineDelay = new TreeMap<String, Double>();
    }


    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {
        //Store delays, Cancel flights for source - destination, Process airline flight delays, process flight delays for each airline
        ProcessCancelFlightsAndFlightsDelayAndAirlineDelay(key, values, context);
    }

    //Method for sorting the TreeMap based on values
    //Ref: http://beginnersbook.com/2014/07/how-to-sort-a-treemap-by-value-in-java/
    public static <K, V extends Comparable<V>> Map<K, V>
    sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator =
                new Comparator<K>() {
                    public int compare(K k1, K k2) {
                        int compare =
                                map.get(k1).compareTo(map.get(k2));
                        if (compare == 0)
                            return 1;
                        else
                            return compare;
                    }
                };

        Map<K, V> sortedByValues =
                new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }

    private void ProcessCancelFlightsAndFlightsDelayAndAirlineDelay(Text key, Iterable<Text> values, Context context) {
        try {
            String[] keyColl = key.toString().split(";");
            String newKey = keyColl[0] + "," + keyColl[1];

            String src = keyColl[0];
            String dest = keyColl[1];

            String airline = keyColl[2];
            Double newVal = 0.0;
            Double srcDelay = 0.0, destDelay = 0.0, airlineDelay = 0.0;

            for (Text val : values) {

                String[] coll = val.toString().split(";");
                String delayKey = coll[1] + ":" + coll[2]; //Key : Month:Day

                Double delayVal = (coll[14].equals("") ? 0 : Double.parseDouble(coll[14])) + (coll[17].equals("") ? 0 : Double.parseDouble(coll[17]));
                //Value: DepDelay + ArrDelay (coll[14] + coll[17])
                StoreDelaysToHashtable(delayKey, delayVal); //Store delay in hashtable

                newVal += coll[19].equals("") ? 0 : Integer.parseInt(coll[19]);
                srcDelay += coll[14].equals("") ? 0 : Integer.parseInt(coll[14]);
                destDelay += coll[17].equals("") ? 0 : Integer.parseInt(coll[17]);
                airlineDelay += (coll[14].equals("") ? 0 : Integer.parseInt(coll[14])) + (coll[17].equals("") ? 0 : Integer.parseInt(coll[17]));

            }

            // context.write(key, new Text(airlineDelay.toString()));
            if (airlineDelay > maxDelay) maxDelay = airlineDelay;
            else if (airlineDelay < minDelay) minDelay = airlineDelay;
            multipleOutputs.write(key, new Text(airlineDelay.toString()), "Reducer1Output");
            //Cancel flights
            StoreCancelFlightsToHashtable(newKey, newVal);

            //Source and Destination delay
            StoreSrcDestDelayToHashtable(src, srcDelay, dest, destDelay);

            //Airline delay
            StoreAirlineDelayToHashtable(airline, airlineDelay);

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + ";Exception in ProcessCancelFlightsAndFlightsDelayAndAirlineDelay method");
        }

    }

    private static void StoreDelaysToHashtable(String key, Double val) {
        if (htDelay.containsKey(key)) {
            Double del = val + htDelay.get(key);
            htDelay.put(key, del + val);
        } else {
            htDelay.put(key, val);
        }
    }

    private static void StoreCancelFlightsToHashtable(String key, Double val) {
        if (htCancelFlights.containsKey(key)) {
            Double del = val + htCancelFlights.get(key);
            htCancelFlights.put(key, del + val);
        } else {
            htCancelFlights.put(key, val);
        }
    }


    private static void StoreSrcDestDelayToHashtable(String src, Double srcDelay, String dest, Double destDelay) {
        //source to the list
        if (htAirportDelay.containsKey(src)) {
            Double del = srcDelay + htAirportDelay.get(src);
            htAirportDelay.put(src, del + srcDelay);
        } else {
            htAirportDelay.put(src, srcDelay);
        }

        //Destination to the list
        if (htAirportDelay.containsKey(dest)) {
            double del = destDelay + htAirportDelay.get(dest);
            htAirportDelay.put(dest, del + destDelay);
        } else {
            htAirportDelay.put(dest, destDelay);
        }
    }

    private static void StoreAirlineDelayToHashtable(String key, Double val) {
        if (htAirlineDelay.containsKey(key)) {
            Double del = val + htAirlineDelay.get(key);
            htAirlineDelay.put(key, del + val);
        } else {
            htAirlineDelay.put(key, val);
        }
    }

    private void Output2() throws IOException {

        String Airportfile = "./output/Delayoutput/AirportDelay.txt";
        FileWriter f0 = new FileWriter(Airportfile);
        String newLine = System.getProperty("line.separator");
        //What is the best time to travel day/week/time of year to minimize delays?
        Set<Entry<String, Double>> setDelay = sortByValues(htDelay).entrySet();
        System.out.println("What is the best time to travel in a month to minimize delays?(Top 10)");
        Iterator<Entry<String, Double>> iDelay = setDelay.iterator();
        f0.write("What is the best time to travel in a month to minimize delays?" + newLine);
        f0.write(newLine);
        for (int i = 0; iDelay.hasNext() && i < 5; i++) {
            @SuppressWarnings("rawtypes")
            Map.Entry mDelay = (Map.Entry) iDelay.next();
            String sep = mDelay.getKey().toString();
            String day[] = sep.split(":");
            f0.write("Day: " + day[1] + newLine);
            System.out.print("Day: " + day[1]);
            System.out.print(";Delay: " + mDelay.getValue());
            System.out.println();
        }


        //Which airports have higher delays (Top 10) ?
        System.out.println();
        Set<Entry<String, Double>> setAirport = sortByValues(htAirportDelay).entrySet();
        System.out.println("Which airports have lesser delays(Top 10) ?");
        Iterator<Entry<String, Double>> iAirport = setAirport.iterator();
        f0.write(newLine);
        f0.write("Which airports have lesser delays on average? (Top 10)" + newLine);
        f0.write(newLine);
        for (int i = 0; iAirport.hasNext() && i < 10; i++) {
            @SuppressWarnings("rawtypes")
            Map.Entry mAirport = (Map.Entry) iAirport.next();
            String t1 = mAirport.getKey().toString();
            f0.write("Airport: " + MappingCodes.getSrcDest(t1) + newLine);
            //f0.write("Airport: "+ MappingCodes.getSrcDest(t1)  + ";Delay: " + mAirport.getValue()  + newLine);
            System.out.print("Airport: " + MappingCodes.getSrcDest(t1));
            System.out.print(";Delay: " + mAirport.getValue());
            System.out.println();
        }
        f0.write(newLine);

        //Which airline has the highest delay on an average?
        System.out.println();
        Set<Entry<String, Double>> setAirline = sortByValues(htAirlineDelay).entrySet();
        System.out.println("Which airline has the least delay on an average?(Top 10)");
        f0.write("Which airlines have lesser delays on an average?(Top 10)" + newLine);
        f0.write(newLine);
        Iterator<Entry<String, Double>> iAirline = setAirline.iterator();
        for (int i = 0; iAirline.hasNext() && i < 10; i++) {
            @SuppressWarnings("rawtypes")
            Map.Entry mAirline = (Map.Entry) iAirline.next();
            f0.write("Airline: " + MappingCodes.getAirlineMap(mAirline.getKey().toString()) + newLine); //";Delay " + mAirline.getValue() + newLine);
            System.out.print("Airline: " + MappingCodes.getAirlineMap(mAirline.getKey().toString()));
            System.out.print(";Delay: " + mAirline.getValue());
            System.out.println();
        }
        f0.close();
    }

    protected void cleanup(Context context) throws IOException,
            InterruptedException {
        System.out.println("Reducer1 Completed");
        AirSafetyExtractor.minDelay = minDelay;
        AirSafetyExtractor.maxDelay = maxDelay;
        //System.out.println("MinDelayAtRed1 : " + AirSafetyExtractor.minDelay);
        //System.out.println("MaxDelayAtRed1 : " + AirSafetyExtractor.maxDelay);

        Output2();
        multipleOutputs.close();
    }
}
