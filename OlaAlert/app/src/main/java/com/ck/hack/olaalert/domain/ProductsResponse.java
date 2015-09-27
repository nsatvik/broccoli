package com.ck.hack.olaalert.domain;

import java.util.List;

/**
 * Created by Satvik on 27/09/15.
 */
public class ProductsResponse {
    private List<CabCategory> categories;
    private List<RideEstimate> estimates;

    public List<CabCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<CabCategory> categories) {
        this.categories = categories;
    }

    public List<RideEstimate> getEstimates() {
        return estimates;
    }

    public void setEstimates(List<RideEstimate> estimates) {
        this.estimates = estimates;
    }
}
