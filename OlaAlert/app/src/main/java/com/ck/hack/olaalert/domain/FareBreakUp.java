package com.ck.hack.olaalert.domain;

/**
 * Created by Satvik on 27/09/15.
 */
public class FareBreakUp {

    private String type;
    private String minimumDistance;
    private String minimumTime;
    private String baseFare;
    private String costPerDistance;
    private String waitingCostPerMinute;
    private String rideCostPerMinute;
    private Surcharge surcharge;

    public String getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(String baseFare) {
        this.baseFare = baseFare;
    }

    public String getCostPerDistance() {
        return costPerDistance;
    }

    public void setCostPerDistance(String costPerDistance) {
        this.costPerDistance = costPerDistance;
    }

    public String getMinimumDistance() {
        return minimumDistance;
    }

    public void setMinimumDistance(String minimumDistance) {
        this.minimumDistance = minimumDistance;
    }

    public String getMinimumTime() {
        return minimumTime;
    }

    public void setMinimumTime(String minimumTime) {
        this.minimumTime = minimumTime;
    }

    public String getRideCostPerMinute() {
        return rideCostPerMinute;
    }

    public void setRideCostPerMinute(String rideCostPerMinute) {
        this.rideCostPerMinute = rideCostPerMinute;
    }

    public Surcharge getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(Surcharge surcharge) {
        this.surcharge = surcharge;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWaitingCostPerMinute() {
        return waitingCostPerMinute;
    }

    public void setWaitingCostPerMinute(String waitingCostPerMinute) {
        this.waitingCostPerMinute = waitingCostPerMinute;
    }
}
