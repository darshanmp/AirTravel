package com.AirTravel;

import java.util.Hashtable;

public class MappingCodes {
	private static Hashtable<String, String> htAirlineCodes = new  Hashtable<String, String> ();
	private static Hashtable<String, String> htSrcDest = new  Hashtable<String, String> ();
    private MappingCodes()
	{
    	
	}
    public static String getAirlineMap(String airlinecode)
    {
    	if(htAirlineCodes.size() == 0)
    		LoadAirlineMap();
    	if(htAirlineCodes.containsKey(airlinecode))
    	{
    		return htAirlineCodes.get(airlinecode);
    		
    	}
    	else
    	{
    		System.out.println("Airline Key not found" + airlinecode);
    		return airlinecode;
    	}
    }
    
    public static String getSrcDest(String loc)
    {
    	if(htSrcDest.size() == 0)
    	{
    		LoadSourceDest();
    	}
    	if(htSrcDest.containsKey(loc))
    	{
    		return htSrcDest.get(loc);
    		
    	}
    	else
    	{
    		System.out.println("Location Key not found" + loc);
    		return loc;
    	}
    }
       
	public static void LoadAirlineMap()
	{
		htAirlineCodes = new Hashtable<String, String>();
		htAirlineCodes.put("AA", "American Airlines");
		htAirlineCodes.put("AS", "Alaska Airlines");
		htAirlineCodes.put("B6", "JetBlue Airways");
		htAirlineCodes.put("DL", "Delta Airways");
		htAirlineCodes.put("EV", "ExpressJet Airlines");
		htAirlineCodes.put("F9", "Frontier Airlines");
		htAirlineCodes.put("HA", "Hawaiian Airlines");
		htAirlineCodes.put("NK", "Spirit Airlines");
		htAirlineCodes.put("OO", "SkyWest Airlines");
		htAirlineCodes.put("UA", "United Airlines");
		htAirlineCodes.put("VX", "Virgin America");
		htAirlineCodes.put("WN", "Southwest Airlines");
		htAirlineCodes.put("MQ", "American Eagle");
		htAirlineCodes.put("9E", "Endeavor Air");
		htAirlineCodes.put("CO", "Continental Airlines");
		htAirlineCodes.put("XE", "Express Jet");
		htAirlineCodes.put("US", "US Airways");
		htAirlineCodes.put("FL", "AirTran");

	}
	public static  void LoadSourceDest()
	{
		htSrcDest = new Hashtable<String, String>();
		htSrcDest.put("PSP","Palm Springs, CA");
		htSrcDest.put("IAD","Washington, DC");
		htSrcDest.put("DAL","Dallas, TX");
		htSrcDest.put("SFO","San Francisco, CA");
		htSrcDest.put("DCA","Washington, DC");
		htSrcDest.put("LAS","Las Vegas, NV");
		htSrcDest.put("SEA","Seattle, WA");
		htSrcDest.put("LGA","New York, NY");
		htSrcDest.put("LAX","Los Angeles, CA");
		htSrcDest.put("SAN","San Diego, CA");
		htSrcDest.put("DFW","Dallas/Fort Worth, TX");
		htSrcDest.put("DTW","Detroit, MI");
		htSrcDest.put("CVG","Cincinnati, OH");
		htSrcDest.put("MCO","Orlando, FL");
		htSrcDest.put("PHL","Philadelphia, PA");
		htSrcDest.put("ATL","Atlanta, GA");
		htSrcDest.put("ELP","El Paso, TX");
		htSrcDest.put("MSP","Minneapolis, MN");
		htSrcDest.put("PHX","Phoenix, AZ");
		htSrcDest.put("BOS","Boston, MA");
		htSrcDest.put("ANC","Anchorage, AK");
		htSrcDest.put("BIS","Bismarck/Mandan, ND");
		htSrcDest.put("IAH","Houston, TX");
		htSrcDest.put("ORF","Norfolk, VA");
		htSrcDest.put("HNL","Honolulu, HI");
		htSrcDest.put("MIA","Miami, FL");
		htSrcDest.put("AVL","Asheville, NC");
		htSrcDest.put("PBI","West Palm Beach/Palm Beach, FL");
		htSrcDest.put("MSY","New Orleans, LA");
		htSrcDest.put("CLT","Charlotte, NC");
		htSrcDest.put("TUS","Tucson, AZ");
		htSrcDest.put("PDX","Portland, OR");
		htSrcDest.put("STT","Charlotte Amalie, VI");
		htSrcDest.put("GRR","Grand Rapids, MI");
		htSrcDest.put("RSW","Fort Myers, FL");
		htSrcDest.put("MCI","Kansas City, MO");
		htSrcDest.put("FNT","Flint, MI");
		htSrcDest.put("MYR","Myrtle Beach, SC");
		htSrcDest.put("DEN","Denver, CO");
		htSrcDest.put("AUS","Austin, TX");
		htSrcDest.put("EWR","Newark, NJ");
		htSrcDest.put("CLE","Cleveland, OH");
		htSrcDest.put("ORD","Chicago, IL");
		htSrcDest.put("BZN","Bozeman, MT");
		htSrcDest.put("SNA","Santa Ana, CA");
		htSrcDest.put("FLL","Fort Lauderdale, FL");
		htSrcDest.put("MTJ","Montrose/Delta, CO");
		htSrcDest.put("RIC","Richmond, VA");
		htSrcDest.put("OGG","Kahului, HI");
		htSrcDest.put("OMA","Omaha, NE");
		htSrcDest.put("IND","Indianapolis, IN");
		htSrcDest.put("SRQ","Sarasota/Bradenton, FL");
		htSrcDest.put("ICT","Wichita, KS");
		htSrcDest.put("SJU","San Juan, PR");
		htSrcDest.put("TPA","Tampa, FL");
		htSrcDest.put("ABQ","Albuquerque, NM");
		htSrcDest.put("DSM","Des Moines, IA");
		htSrcDest.put("FSD","Sioux Falls, SD");
		htSrcDest.put("JAC","Jackson, WY");
		htSrcDest.put("BIL","Billings, MT");
		htSrcDest.put("BLI","Bellingham, WA");
		htSrcDest.put("FAI","Fairbanks, AK");
		htSrcDest.put("OME","Nome, AK");
		htSrcDest.put("OTZ","Kotzebue, AK");
		htSrcDest.put("SJC","San Jose, CA");
		htSrcDest.put("OAK","Oakland, CA");
		htSrcDest.put("SMF","Sacramento, CA");
		htSrcDest.put("SLC","Salt Lake City, UT");
		htSrcDest.put("BDL","Hartford, CT");
		htSrcDest.put("BHM","Birmingham, AL");
		htSrcDest.put("BNA","Nashville, TN");
		htSrcDest.put("BUR","Burbank, CA");
		htSrcDest.put("BWI","Baltimore, MD");
		htSrcDest.put("SBN","South Bend, IN");
		htSrcDest.put("CID","Cedar Rapids/Iowa City, IA");
		htSrcDest.put("GRB","Green Bay, WI");
		htSrcDest.put("MBS","Saginaw/Bay City/Midland, MI");
		htSrcDest.put("MKE","Milwaukee, WI");
		htSrcDest.put("JFK","New York, NY");
		htSrcDest.put("CMH","Columbus, OH");
		htSrcDest.put("FAR","Fargo, ND");
		htSrcDest.put("MSN","Madison, WI");
		htSrcDest.put("HDN","Hayden, CO");
		htSrcDest.put("PIT","Pittsburgh, PA");
		htSrcDest.put("SAT","San Antonio, TX");
		htSrcDest.put("RNO","Reno, NV");
		htSrcDest.put("RDU","Raleigh/Durham, NC");
		htSrcDest.put("HOU","Houston, TX");
		htSrcDest.put("HRL","Harlingen/San Benito, TX");
		htSrcDest.put("LBB","Lubbock, TX");
		htSrcDest.put("MDW","Chicago, IL");
		htSrcDest.put("STL","St. Louis, MO");
		htSrcDest.put("CHS","Charleston, SC");
		htSrcDest.put("ECP","Panama City, FL");
		htSrcDest.put("JAX","Jacksonville, FL");
		htSrcDest.put("ALB","Albany, NY");
		htSrcDest.put("LIH","Lihue, HI");
		htSrcDest.put("ITO","Hilo, HI");
		htSrcDest.put("KOA","Kalaoa, HI");
		htSrcDest.put("AZO","Kalamazoo, MI");
		htSrcDest.put("ACT","Waco, TX");
		htSrcDest.put("ABE","Lehigh County, PA");
		htSrcDest.put("AMA","Amarillo, TX");
		htSrcDest.put("MAF","Midland County, TX");
		htSrcDest.put("ABY","Albany, GA");
		htSrcDest.put("ACV","McKinleyville, CA");
		htSrcDest.put("ADK","Adak Island, AK");
		htSrcDest.put("ADQ","Kodiak Station, AK");
		htSrcDest.put("AEX","Rapides Parish, LA");
		htSrcDest.put("AGS","Augusta, GA");
		htSrcDest.put("MEI","Meridian, MS");
		htSrcDest.put("MDT","Dauphin County, PA");
		htSrcDest.put("MLB","Melbourne, FL");
		htSrcDest.put("MLI","Rock Island County, IL");
		htSrcDest.put("PHF","Newport News, VA");
		htSrcDest.put("PWM","Portland, ME");
		htSrcDest.put("ROA","Roanoke, VA");
		htSrcDest.put("ROC","Rochester, NY");
		htSrcDest.put("SAV","Savannah, GA");
		htSrcDest.put("SDF","Louisville, KY");
		htSrcDest.put("VPS","Okaloosa County, FL");
		htSrcDest.put("BMI","Bloomington, IL");
		htSrcDest.put("BKG","Taney County, MS");
		htSrcDest.put("BOI","Boise, ID");
		htSrcDest.put("BUF","Cheektowaga, NY");
		htSrcDest.put("CAK","Green, OH");
		htSrcDest.put("BRW","Barrow, AK");
		htSrcDest.put("SCC","Prudhoe Bay, AK");
		htSrcDest.put("BTV","South Burlington, VT");
		htSrcDest.put("EGE","Gypsum, CO");
		htSrcDest.put("FAT","Fresno, CA");
		htSrcDest.put("FCA","Flathead County, MT");
		htSrcDest.put("GEG","Spokane County, WA");
		htSrcDest.put("GSO","Guilford County, NC");
		htSrcDest.put("GSP","Spartanburg County, SC");
		htSrcDest.put("HPN","Harrison, NY");
		htSrcDest.put("HSV","Huntsville, AL");
		htSrcDest.put("ISP","Ronkonkoma, NY");
		htSrcDest.put("LEX","Lexington, KY");
		htSrcDest.put("LGB","Long Beach, CA");
		htSrcDest.put("LIT","Little Rock, AR");
		htSrcDest.put("MEM","Memphis, TN");
		htSrcDest.put("OKC","Oklahoma City, OK");
		htSrcDest.put("XNA","Highfill, AR");
//		htSrcDest.put("","");

	}
}
