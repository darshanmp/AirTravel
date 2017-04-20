package com.AirTravel;

/**
 * Created by abhilashuday on 4/20/17.
 */
import java.io.IOException;
// import org.apache.commons.logging.Log;
// import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.mapreduce.Mapper;

import org.apache.hadoop.io.Text;

import javax.naming.Context;


/**
 *
 * @author mac
 */
public class AirTravelMapper extends Mapper<Text,Text,Text,Text> {
    // The Karmasphere Studio Workflow Log displays logging from Apache Commons Logging, for example:
    // private static final Log LOG = LogFactory.getLog("org.smaple.HomeworkMapper");

    @Override
    protected void map(Text key, Text value, Context context)
            throws IOException, InterruptedException {
    	//darshan first comment
        // TODO: please implement your mapper code here
    }
}
