package com.AirTravel;


import java.util.Hashtable;

public class RatingAlgorithm {
    private final Double maxDel;

    RatingAlgorithm(Double maxDelay) {
        maxDel = maxDelay;
    }

    public Double[] calcRating(String airlineCode, String srcAirp, String destAirp, Double delay) {
        Double tempSafe = calcSafetyRating(airlineCode);
        Double safetyRating = Math.round(tempSafe * 100.0) / 100.0;
        //System.out.println(safetyRating);

        Double tempPerf = calcPerfRating(srcAirp, destAirp, airlineCode, delay);
        Double perfRating = Math.round(tempPerf * 100.0) / 100.0;
        //System.out.println(perfRating);

        Double tempRat = safetyRating * 0.4 + perfRating * 0.6;
        Double totalRating = Math.round(tempRat * 100.0) / 100.0;
        //System.out.println(totalRating);

        Double[] ratings = new Double[]{totalRating, perfRating, safetyRating};
        return ratings;
    }

    public Double calcSafetyRating(String airlineCode) {
        if (AirSafetyExtractor.getSafetyHashMap().containsKey(airlineCode)) {
            double safetyScore = 0.0;
            String[] vals = AirSafetyExtractor.getSafetyHashMap().get(airlineCode.trim()).split(";");
            int incidents = Integer.parseInt(vals[0]);
            int accidents = Integer.parseInt(vals[1]);
            safetyScore = (3 - (incidents * 0.5 / 10)) + (7 - (accidents * 0.5 / 5));
            return safetyScore;
        } else
            return 10.0;
    }

    public Double calcPerfRating(String srcAirp, String destAirp, String airlineCode, Double delay) {
        //calculate performance rating
        if (delay < 0) return 10.0;
        else {
            return 10 - (delay / (maxDel / 10));
        }
    }
}
