package com.ck.hack.olaalert.domain;

import java.util.List;

/**
 * Created by Satvik on 27/09/15.
 */
public class DoctorResponse {
    private String listingType;
    private Boolean isAjax;
    List<Doctor> doctors;

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Boolean getIsAjax() {
        return isAjax;
    }

    public void setIsAjax(Boolean isAjax) {
        this.isAjax = isAjax;
    }

    public String getListingType() {
        return listingType;
    }

    public void setListingType(String listingType) {
        this.listingType = listingType;
    }
}
