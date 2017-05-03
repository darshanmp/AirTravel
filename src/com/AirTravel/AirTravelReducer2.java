package com.AirTravel;

/**
 * Created by abhilashuday,Darshan Masti Prakash on 4/20/17.
 */


import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;

import org.apache.hadoop.io.file.tfile.TFile;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.io.Text;

import javax.swing.*;

/**
 * @author mac
 */

public class AirTravelReducer2 extends Reducer<Text, Text, Text, Text> {
	private MultipleOutputs<Text, Text> multipleOutputs;


	@Override
	public void setup(Context context){
		multipleOutputs = new MultipleOutputs<Text, Text>(context);
	}

	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		RatingAlgorithm rating = new RatingAlgorithm(AirSafetyExtractor.maxDelay);
		//System.out.println("MinDelay : " + AirSafetyExtractor.minDelay);
		//System.out.println("MaxDelay : " + AirSafetyExtractor.maxDelay);			
		//Key: SFO;HNL;UA Value: 738.0
		String[] keys = key.toString().split(";");
		String airlineCode = keys[2];
		String src = keys[0];
		String dest = keys[1];
		multipleOutputs.write(new Text("Source;\tDestination\t;Airline\t\t\t\t\t"), new Text("TotalRating;PerformanceRating;SafetyRating"), "Reducer2Output");  
		for(Text val : values){
			Double delay = Double.parseDouble(val.toString());
			Double [] results  = rating.calcRating(MappingCodes.getAirlineMap(airlineCode), src, dest, delay);
			multipleOutputs.write(new Text(MappingCodes.getSrcDest(keys[0]) + ";" + MappingCodes.getSrcDest(keys[1]) + ";" + MappingCodes.getAirlineMap(keys[2]) + "\t\t\t"), 
					new Text(results[0] + ";" + results[1] + ";" + results[2] + "\n"), "Reducer2Output");
		}
	}

	protected void cleanup(Context context) throws IOException,
	InterruptedException {
		System.out.println("Reducer2 Completed");
				multipleOutputs.close();
	}
}
