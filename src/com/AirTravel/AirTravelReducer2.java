package com.AirTravel;

/**
 * Created by abhilashuday,Darshan Masti Prakash on 4/20/17.
 */


import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.io.Text; 

/**
 * @author mac
 */

public class AirTravelReducer2 extends Reducer<Text, Text, Text, Text> {
	 @Override
	    protected void reduce(Text key, Iterable<Text> values, Context context)
	            throws IOException, InterruptedException {
			RatingAlgorithm rating = new RatingAlgorithm();
			System.out.println("MinDelay : " + AirSafetyExtractor.minDelay);
			System.out.println("MaxDelay : " + AirSafetyExtractor.maxDelay);

			rating.calcSafetyRating();
	 }
}
