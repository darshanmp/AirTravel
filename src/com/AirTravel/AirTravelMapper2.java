package com.AirTravel;

/**
 * Created by abhilashuday,Darshan Masti Prakash on 4/29/17.
 */

import java.io.IOException;
// import org.apache.commons.logging.Log;
// import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.mapreduce.Mapper;


import org.apache.hadoop.io.Text;
import org.apache.commons.lang.ArrayUtils;

/**
 * @author mac
 */
public class AirTravelMapper2 extends Mapper<Text, Text, Text, Text> {
    // The Karmasphere Studio Workflow Log displays logging from Apache Commons Logging, for example:
    // private static final Log LOG = LogFactory.getLog("org.smaple.HomeworkMapper");


    @Override
    protected void map(Text key, Text value, Context context)
            throws IOException, InterruptedException {
        System.out.println("Mapper2Key: " + key ) ;
        System.out.println(" Mapper2Value: " + value);
        //System.out.println();

        context.write(key, value);
    }
}
