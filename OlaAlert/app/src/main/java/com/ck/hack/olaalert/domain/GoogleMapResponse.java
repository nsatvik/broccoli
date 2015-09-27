package com.ck.hack.olaalert.domain;

import java.util.List;

/**
 * Created by Satvik on 27/09/15.
 */
public class GoogleMapResponse {

    List<Hospital> results;

    public List<Hospital> getResults() {
        return results;
    }

    public void setResults(List<Hospital> results) {
        this.results = results;
    }
}
