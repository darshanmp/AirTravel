package com.AirTravel;


import java.util.Hashtable;

public class RatingAlgorithm {
	private static Hashtable<String, String> htAirlineCodes = null;
	private final Double maxDel;
	RatingAlgorithm(Double maxDelay)
	{ 
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
	public Double[] calcRating(String airlineCode, String srcAirp,String destAirp, Double delay)
	{
		Double safetyRating = calcSafetyRating(airlineCode);
		Double perfRating = calcPerfRating(srcAirp, destAirp,  airlineCode,  delay);	
		Double[] ratings = new Double[] {(safetyRating * 0.4 + perfRating * 0.6), safetyRating, perfRating};
		return ratings;
	}
	public Double calcSafetyRating(String airlineCode)
	{		
		if(AirSafetyExtractor.getSafetyHashMap().containsKey(airlineCode))
		{
			double safetyScore = 0.0;
			String[] vals = AirSafetyExtractor.getSafetyHashMap().get(airlineCode).split(";");
			int incidents = Integer.parseInt(vals[0]);
			int accidents = Integer.parseInt(vals[1]);
			safetyScore = (3- (incidents * 0.5 /10)) + (7 - (accidents * 0.5 /5));
			return safetyScore;
		}
		else		
			return 10.0;		
	}
	
	public Double calcPerfRating(String srcAirp,String destAirp, String airlineCode, Double delay)
	{	
		//calculate performance rating		
		if(delay < 0 ) return 10.0;
		else 
		{
			return 10 - (delay / (maxDel /10));
		}
	}	
}
