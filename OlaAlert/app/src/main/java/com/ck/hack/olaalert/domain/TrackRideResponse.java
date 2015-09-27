package com.ck.hack.olaalert.domain;

/**
 * Created by Satvik on 27/09/15.
 */
public class TrackRideResponse {

    private String status;
    private String requestType;
    private String crn;
    private Double driverLat;
    private Double driverLong;
    private String bookingStatus;

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public Double getDriverLat() {
        return driverLat;
    }

    public void setDriverLat(Double driverLat) {
        this.driverLat = driverLat;
    }

    public Double getDriverLong() {
        return driverLong;
    }

    public void setDriverLong(Double driverLong) {
        this.driverLong = driverLong;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
