package com.AirTravel;


import java.util.Hashtable;

public class RatingAlgorithm {
	private static Hashtable<String, String> htAirlineCodes = null;
	private final Double minDel;
	private final Double maxDel;
	RatingAlgorithm(Double minDelay, Double maxDelay)
	{ 
		minDel = minDelay; 
		maxDel = maxDelay;
		htAirlineCodes = new Hashtable<String, String>();
		LoadAirlineMap();
		
	}
	public void LoadAirlineMap()
	{
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
		 
	}
	public Double calcSafetyRating(String airlineCode)
	{		
		if(AirSafetyExtractor.getSafetyHashMap().containsKey(airlineCode))
		{
			double safetyScore = 0.0;
			String[] vals = AirSafetyExtractor.getSafetyHashMap().get(airlineCode).split(";");
			int incidents = Integer.parseInt(vals[0]);
			int accidents = Integer.parseInt(vals[1]);
			return safetyScore;
		}
		else		
			return 10.0;		
	}
	
	public Double calcPerfRating(String srcAirp,String destAirp, String airlineCode, Double delay)
	{	
		//calculate performance rating
		return 10.0;

	}	
}
