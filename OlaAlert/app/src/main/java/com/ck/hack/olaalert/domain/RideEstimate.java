package com.ck.hack.olaalert.domain;

/**
 * Created by Satvik on 27/09/15.
 */
public class RideEstimate {

    private String category;
    private Double distance;
    private Integer travel_time_in_minutes;
    private Integer amountMin;
    private Integer amountMax;

    public Integer getAmountMax() {
        return amountMax;
    }

    public void setAmountMax(Integer amountMax) {
        this.amountMax = amountMax;
    }

    public Integer getAmountMin() {
        return amountMin;
    }

    public void setAmountMin(Integer amountMin) {
        this.amountMin = amountMin;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getTravelTimeInMinutes() {
        return travel_time_in_minutes;
    }

    public void setTravelTimeInMinutes(Integer travelTimeInMinutes) {
        this.travel_time_in_minutes = travelTimeInMinutes;
    }
}
