package com.ck.hack.olaalert.domain;

/**
 * Created by Satvik on 27/09/15.
 */
public class Doctor {
    private String doctor_name;
    private Double locality_latitude;
    private Double locality_longitude;
    private String locality;

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public Double getLocality_latitude() {
        return locality_latitude;
    }

    public void setLocality_latitude(Double locality_latitude) {
        this.locality_latitude = locality_latitude;
    }

    public Double getLocality_longitude() {
        return locality_longitude;
    }

    public void setLocality_longitude(Double locality_longitude) {
        this.locality_longitude = locality_longitude;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }
}
