package com.AirTravel;


import java.util.Hashtable;

public class RatingAlgorithm {
	private static Hashtable<String, String> htAirlineCodes = null;
	RatingAlgorithm()
	{
		htAirlineCodes = new Hashtable<String, String>();
		LoadAirlineMap();
		
	}
	public void LoadAirlineMap()
	{
		htAirlineCodes.put("AA", "");
		htAirlineCodes.put("AS", "");
		htAirlineCodes.put("B6", "");
		htAirlineCodes.put("DL", "");
		htAirlineCodes.put("EV", "");
		htAirlineCodes.put("F9", "");
		htAirlineCodes.put("HA", "");
		htAirlineCodes.put("NK", "");
		htAirlineCodes.put("OO", "");
		htAirlineCodes.put("UA", "");
		htAirlineCodes.put("VX", "");
		htAirlineCodes.put("WN", "");
		
	}
	public void calcSafetyRating(String airlineCode)
	{
		//AirSafetyExtractor.getSafetyHashMap()
	}
}
