package com.ck.hack.olaalert.service;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.ck.hack.olaalert.app.DataManager;
import com.ck.hack.olaalert.domain.DoctorResponse;
import com.ck.hack.olaalert.domain.ProductsResponse;
import com.ck.hack.olaalert.utils.Utils;
import com.google.android.gms.maps.model.LatLng;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Satvik on 27/09/15.
 */
public class PractoService {

    private static String APP_TOKEN = "693c3906391a98655ee8c6a50f51978c";
    private Map<String,String> headers = new HashMap<>();
    protected DataManager dataManager;
    protected RequestQueue queue;

    public PractoService(DataManager dataManager) {
        this.dataManager = dataManager;
        queue = dataManager.getRequestQueue();
        headers.put("Content-Type", "application/json");
        headers.put("x-fabric-api-token", APP_TOKEN);
    }


    public void getDoctorDetails (LatLng currLoc,Response.Listener<DoctorResponse> listener, Response.ErrorListener errorListener) {
        DecimalFormat df = new DecimalFormat("##.###");

        String url = "https://www.practo.com/health/api/search?city=bangalore&sort_by=practo_ranking&speciality=Dentist&limit=1&searchfor=specialization&near="+df.format(currLoc.latitude)+","+df.format(currLoc.longitude);
        Log.v("PractoService",url);
        GsonRequest getDocReq = new GsonRequest(url, DoctorResponse.class, headers, listener, errorListener );
        queue.add(getDocReq);
    }
}
