package com.AirTravel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by abhilash uday,Darshan Masti Prakash on 4/28/17.
 */
public class AirSafetyExtractor {

    static String csvFile="";
    public static Double minDelay = 0.0;
    public static Double maxDelay = 0.0;
    private static HashMap<String,String> safetyIncidents=new HashMap<String, String>();
    
    private AirSafetyExtractor()
	{
    	
	}
    
	public static void setFile(String file)
	{
		csvFile = file;
	}

    public static HashMap<String,String> getSafetyHashMap()
    {
    	if(safetyIncidents.size() == 0)
    		return extraction();
    	return safetyIncidents;
    }
    
    public static HashMap<String,String> extraction() {
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
                System.out.println(safetydata[0] + ":" +total);
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
