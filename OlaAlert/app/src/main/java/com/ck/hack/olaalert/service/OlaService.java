package com.ck.hack.olaalert.service;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.ck.hack.olaalert.app.DataManager;
import com.ck.hack.olaalert.domain.ProductsResponse;
import com.ck.hack.olaalert.utils.Utils;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Satvik on 26/09/15.
 */
public class OlaService {

    private static String AUTH_TOKEN = "ea93fcb7a26e4979b291fd3a722695d6";
    private static String APP_TOKEN = "dc14bd452a7f45d9bf0e4f1dcab106f9";
    private Map<String,String> headers = new HashMap<>();
    protected DataManager dataManager;
    protected RequestQueue queue;


    public OlaService(DataManager dataManager) {
        this.dataManager = dataManager;
        queue = dataManager.getRequestQueue();
        headers.put("Authorization", AUTH_TOKEN);
        headers.put("X-APP-TOKEN", APP_TOKEN);
    }




    public void bookCab() {


    }

    public void listCabProducts(LatLng loc, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String path = "/v1/products?pickup_lat=" + loc.latitude + "&pickup_lng=" + loc.longitude;
        GsonRequest listCabRequest = new GsonRequest(Utils.BASE_URL+path, ProductsResponse.class, headers, listener, errorListener );
        queue.add(listCabRequest);
    }

    public void cancelRide() {

    }
}
