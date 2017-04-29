package com.AirTravel;

import org.apache.commons.beanutils.converters.IntegerArrayConverter;
import org.apache.commons.collections.map.HashedMap;
import org.apache.hadoop.mapred.FileInputFormat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by abhilash uday,Darshan Masti Prakash on 4/28/17.
 */
public class AirSafetyExtractor {

    HashMap<String,String> safetyIncidents=new HashMap();
    public HashMap<String,String> extraction(String csvFile) {

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] safetydata = line.split(cvsSplitBy);
                int totalincidents=0,totalaccidents=0;
                // pre-process to extract incidents and accidents to total
                if(!safetydata[0].equals("airline")) {
                    int incidents1 = Integer.parseInt(safetydata[2]);
                    int incidents2 = Integer.parseInt(safetydata[5]);
                    totalincidents = incidents2 + incidents1;
                    int accidents1 = Integer.parseInt(safetydata[3]);
                    int accidents2 = Integer.parseInt(safetydata[6]);
                    totalaccidents = accidents1 + accidents2;
                }
              //  System.out.println(safetydata[0] + " total incidents are " + totalincidents + " total accidents are " + totalaccidents);
                //Hashmap for airline name,accidents and  Incidents
                String total=Integer.toString(totalincidents) + ";" + Integer.toString(totalaccidents);
                safetyIncidents.put(safetydata[0],total);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    return safetyIncidents;
    }
}
