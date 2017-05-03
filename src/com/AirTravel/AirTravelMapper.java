package com.AirTravel;

/**
 * Created by abhilashuday,Darshan Masti Prakash on 4/20/17.
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
public class AirTravelMapper extends Mapper<Text, Text, Text, Text> {
    // The Karmasphere Studio Workflow Log displays logging from Apache Commons Logging, for example:
    // private static final Log LOG = LogFactory.getLog("org.smaple.HomeworkMapper");


    @Override
    protected void map(Text key, Text value, Context context)
            throws IOException, InterruptedException {

        //Extract out the Source, Destination, AirlineName as the Key
        //Source 7, Destination 11,Airline ID 5
        String[] coll = key.toString().split(",");

        if (!coll[0].equals("YEAR")) {
            String newKey = coll[7] + ";" + coll[12] + ";" + coll[5];

            //Remove keys from the value collection since we have to used it in the values
            coll = (String[]) ArrayUtils.removeElement(coll, coll[7]);
            coll = (String[]) ArrayUtils.removeElement(coll, coll[11]);
            coll = (String[]) ArrayUtils.removeElement(coll, coll[5]);


            StringBuilder build = new StringBuilder();
            for (int i = 0; i < coll.length; i++) {
                if (i == coll.length - 1) build.append(coll[i].toString());
                else build.append(coll[i].toString() + ";");
            }

            String newVal = build.toString();

            context.write(new Text(newKey), new Text(newVal));

        }
    }


}
