package com.ck.hack.olaalert.service;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.ck.hack.olaalert.app.DataManager;
import com.ck.hack.olaalert.domain.GoogleMapResponse;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Satvik on 27/09/15.
 */
public class PractoService {

    private static String APP_TOKEN = "693c3906391a98655ee8c6a50f51978c";
    private Map<String, String> headers = new HashMap<>();
    protected DataManager dataManager;
    protected RequestQueue queue;

    public PractoService(DataManager dataManager) {
        this.dataManager = dataManager;
        queue = dataManager.getRequestQueue();
        headers.put("Content-Type", "application/json");
        headers.put("x-fabric-api-token", APP_TOKEN);
    }


    public void getDoctorDetails(LatLng currLoc, Response.Listener<GoogleMapResponse> listener, Response.ErrorListener errorListener) {
        // DecimalFormat df = new DecimalFormat("##.###");
        // String url = "http://www.practo.com/health/api/search?city=bangalore&sort_by=practo_ranking&speciality=Dentist&limit=1&searchfor=specialization&near="+df.format(currLoc.latitude)+","+df.format(currLoc.longitude);
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?radius=5000&types=hospital&key=AIzaSyBVDjngU7AiFbIv7WUtX61R_xIqLoJhvv8&location=" + currLoc.latitude + "," + currLoc.longitude;
        Log.v("PractoService", url);
        GsonRequest getDocReq = new GsonRequest(url, GoogleMapResponse.class, headers, listener, errorListener);
        queue.add(getDocReq);
    }
}
