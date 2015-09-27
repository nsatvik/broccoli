package com.ck.hack.olaalert.domain;

/**
 * Created by Satvik on 27/09/15.
 */
public class CabCategory {

    private String id;
    private String display_name;
    private String currency;
    private String distance_unit;
    private String time_unit;
    private double eta;
    private String distance;
    private String image;
    //private FareBreakUp fare_breakup;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDisplayName() {
        return display_name;
    }

    public void setDisplayName(String displayName) {
        this.display_name = displayName;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDistanceUnit() {
        return distance_unit;
    }

    public void setDistanceUnit(String distanceUnit) {
        this.distance_unit = distanceUnit;
    }

    public double getEta() {
        return eta;
    }

    public void setEta(double eta) {
        this.eta = eta;
    }

//    public FareBreakUp getFareBreakup() {
//        return fare_breakup;
//    }
//
//    public void setFareBreakup(FareBreakUp fareBreakup) {
//        this.fare_breakup = fareBreakup;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTimeUnit() {
        return time_unit;
    }

    public void setTimeUnit(String timeUnit) {
        this.time_unit = timeUnit;
    }
}
