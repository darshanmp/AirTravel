package com.AirTravel;

/**
 * Created by abhilashuday,Darshan Masti Prakash on 4/20/17.
 */


import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.io.Text; 

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
			for(Text val : values){
			Double delay = Double.parseDouble(val.toString());
			Double [] results  = rating.calcRating(airlineCode, src, dest, delay);
			multipleOutputs.write(key, new Text(results[0] + ";" + results[1] + ";" + results[2]), "Reducer1Output");   
			}
	 }
	 
		protected void cleanup(Context context) throws IOException,
		InterruptedException {
			System.out.println("Reducer2 Completed");		
			multipleOutputs.close();   
		}
}
