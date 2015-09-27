package com.ck.hack.olaalert.domain;

/**
 * Created by Satvik on 27/09/15.
 */
public class CabCategory {

    private String id;
    private String displayName;
    private String currency;
    private String distanceUnit;
    private String timeUnit;
    private double eta;
    private String distance;
    private String image;
    private FareBreakUp fareBreakup;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDistanceUnit() {
        return distanceUnit;
    }

    public void setDistanceUnit(String distanceUnit) {
        this.distanceUnit = distanceUnit;
    }

    public double getEta() {
        return eta;
    }

    public void setEta(double eta) {
        this.eta = eta;
    }

    public FareBreakUp getFareBreakup() {
        return fareBreakup;
    }

    public void setFareBreakup(FareBreakUp fareBreakup) {
        this.fareBreakup = fareBreakup;
    }

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
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }
}
