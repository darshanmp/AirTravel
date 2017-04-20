package com.AirTravel;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class AirTravelJob {

    public static void main(String[] args) {

        String inputfile= "/Users/abhilashuday/Desktop/2008.csv";
        BufferedReader br = null;

        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(inputfile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                System.out.println("Carrier code " + country[8] + " year of flight" + country[0] );
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


    }
}
