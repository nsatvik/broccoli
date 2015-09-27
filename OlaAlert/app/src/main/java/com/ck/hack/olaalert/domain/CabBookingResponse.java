package com.ck.hack.olaalert.domain;

/**
 * Created by Satvik on 27/09/15.
 */
public class CabBookingResponse {

    private String status;
    private String code;
    private String message;
    private String crn;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String driver_name;
    private String driver_number;
    private String cab_type;
    private String cab_number;
    private String car_model;
    private Integer eta;
    private Double driver_lat;
    private Double driver_lng;

    public String getCab_number() {
        return cab_number;
    }

    public void setCab_number(String cab_number) {
        this.cab_number = cab_number;
    }

    public String getCab_type() {
        return cab_type;
    }

    public void setCab_type(String cab_type) {
        this.cab_type = cab_type;
    }

    public String getCar_model() {
        return car_model;
    }

    public void setCar_model(String car_model) {
        this.car_model = car_model;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public Double getDriver_lat() {
        return driver_lat;
    }

    public void setDriver_lat(Double driver_lat) {
        this.driver_lat = driver_lat;
    }

    public Double getDriver_lng() {
        return driver_lng;
    }

    public void setDriver_lng(Double driver_lng) {
        this.driver_lng = driver_lng;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public String getDriver_number() {
        return driver_number;
    }

    public void setDriver_number(String driver_number) {
        this.driver_number = driver_number;
    }

    public Integer getEta() {
        return eta;
    }

    public void setEta(Integer eta) {
        this.eta = eta;
    }
}
