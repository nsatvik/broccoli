package com.ck.hack.olaalert.domain;

/**
 * Created by Satvik on 27/09/15.
 */
public class TrackRideResponse {

    private String status;
    private String request_type;
    private String crn;
    private Double driver_lat;
    private Double driver_lng;
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
        return driver_lat;
    }

    public void setDriverLat(Double driverLat) {
        this.driver_lat = driverLat;
    }

    public Double getDriverLong() {
        return driver_lng;
    }

    public void setDriverLong(Double driverLong) {
        this.driver_lng = driverLong;
    }

    public String getRequestType() {
        return request_type;
    }

    public void setRequestType(String requestType) {
        this.request_type = requestType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
