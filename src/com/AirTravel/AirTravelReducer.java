
package com.AirTravel;

/**
 * Created by abhilashuday,Darshan Masti Prakash on 4/20/17.
 */


import java.io.IOException;
import java.util.Hashtable;

// import org.apache.commons.logging.Log;
// import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.Text;

/**
 *
 * @author mac
 */
public class AirTravelReducer extends Reducer<Text,Text,Text,Text> {
    // The Karmasphere Studio Workflow Log displays logging from Apache Commons Logging, for example:
    // private static final Log LOG = LogFactory.getLog("org.smaple.HomeworkReducer");

	private static Hashtable<String, Integer> htDelay = new Hashtable<String, Integer>();
	private static Hashtable<String, Integer> htCancelFlights = new Hashtable<String, Integer>();
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

    	//Store delays
    	ProcessStoreDelay(key,values);
    	
    	//Cancel flights for source - destination
    	ProcessCancelFlights(key,values);
    }
    
    private void ProcessStoreDelay(Text key, Iterable<Text> values)
    {
    	//System.out.println(key.toString());    	
    	for(Text val : values){
    	//System.out.println(val.toString());    	
    	String[] coll = val.toString().split(",");
    	String newKey =  coll[1]  + ":" + coll[2]; //Key : Month:Day
    	Integer newVal = Integer.parseInt(coll[16]) + Integer.parseInt(coll[19]); //Value: DepDelay + ArrDelay (coll[16] + coll[19])
    	System.out.println(newKey);
    	System.out.println(newVal);
    	StoreDelays(newKey,newVal); //Store delay in hashtable     	    	       
    	} 
    }
    
    private static void StoreDelays(String key, Integer val)
    {
    	if(htDelay.containsKey(key))
    	{   		
    		Integer del = val +  htDelay.get(key);
    		htDelay.put(key, del + val);
    	}
    	else
    	{
    		htDelay.put(key, val);
    	}
    }
    
    private void ProcessCancelFlights(Text key, Iterable<Text> values)
    {
    	//Key : Source and Destination
    	String[] keyColl = key.toString().split(",");
    	String newKey =  keyColl[0] + "," + keyColl[1];
    	Integer newVal =  0;
    	for(Text val : values)
    	{
    		String[] cancFlight = val.toString().split(",");
    		newVal +=  Integer.parseInt(cancFlight[20]);
    	}
    	System.out.println(newKey);
    	System.out.println(newVal);
    	StoreCancelFlightsToHashtable(newKey,newVal);
    }
    
    private static void StoreCancelFlightsToHashtable(String key, Integer val)
    {
    	if(htCancelFlights.containsKey(key))
    	{   		
    		Integer del = val +  htCancelFlights.get(key);
    		htCancelFlights.put(key, del + val);
    	}
    	else
    	{
    		htCancelFlights.put(key, val);
    	}
    }
}
