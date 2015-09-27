package com.ck.hack.olaalert.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ck.hack.olaalert.R;
import com.ck.hack.olaalert.app.DataManager;
import com.ck.hack.olaalert.app.OlaAlertApp;
import com.ck.hack.olaalert.domain.CabBookingResponse;
import com.ck.hack.olaalert.domain.CancelBookingResponse;
import com.ck.hack.olaalert.domain.Doctor;
import com.ck.hack.olaalert.domain.DoctorResponse;
import com.ck.hack.olaalert.service.PractoService;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import org.w3c.dom.Text;

/**
 * Created by Satvik on 27/09/15.
 */
public class DriverDetailsFragment extends Fragment {

    private static final String LOGTAG = DriverDetailsFragment.class.getSimpleName();

    private CabBookingResponse mBookingResponse;
    private PractoService mPractoService;
    private Doctor mDoctor;

    public DriverDetailsFragment() {
        // NO OP
    }

    private DataManager mDataMan;

    public static DriverDetailsFragment getInstance(String param, double lat, double lng) {
        Bundle args = new Bundle();
        args.putString("driver_data_key", param);
        args.putDouble("lat", lat);
        args.putDouble("lng",lng);
        DriverDetailsFragment fragment =  new DriverDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Activity host = getActivity();
        OlaAlertApp app = (OlaAlertApp) host.getApplication();

        mDataMan = app.getDataManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v(LOGTAG, "onCreateView+");
        View rootView = inflater.inflate(
                R.layout.fragment_driver_details, container, false);
        Bundle args = getArguments();
        Gson gson = new Gson();
        mBookingResponse = gson.fromJson(args.getString("driver_data_key"), CabBookingResponse.class);
        if (mBookingResponse != null) {
            TextView driverName = (TextView) rootView.findViewById(R.id.driver_name);
            driverName.setText(mBookingResponse.getDriver_name());

            TextView driverNum = (TextView) rootView.findViewById(R.id.driver_number);
            driverNum.setText(mBookingResponse.getDriver_number());

            TextView cabType = (TextView) rootView.findViewById(R.id.cab_type);
            cabType.setText(mBookingResponse.getCab_type());

            TextView carName = (TextView) rootView.findViewById(R.id.car_name);
            carName.setText(mBookingResponse.getCar_model());

            final TextView docDetails = (TextView) rootView.findViewById(R.id.destination_details);

            Response.Listener<DoctorResponse> listener = new Response.Listener<DoctorResponse>() {
                @Override
                public void onResponse(DoctorResponse response) {
                    Log.v(LOGTAG, "Doctor success ");
                    Doctor doc = response.getDoctors().get(0);
                    mDoctor = doc;
                    docDetails.setText(doc.getDoctor_name()+"\n"+doc.getLocality());
                }
            };

            final Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.v(LOGTAG, "Doctor error : " + error.getMessage());
                }
            };
            LatLng latLng = new LatLng(args.getDouble("lat"), args.getDouble("lng"));
            mPractoService = new PractoService(mDataMan);
            mPractoService.getDoctorDetails(latLng, listener, errorListener);

            rootView.findViewById(R.id.navigate).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri location = null;

                    if (mDoctor != null) {
                        location = Uri.parse("http://maps.google.com/maps?daddr=" + mDoctor.getLocality_latitude() + "," + mDoctor.getLocality_longitude());
                    } else {
                        location = Uri.parse("http://maps.google.com/maps?daddr=12.9596717,77.6467143");
                    }
                    Intent intent = new Intent(Intent.ACTION_VIEW, location);
                    try {
                        startActivity(intent);
                    } catch (android.content.ActivityNotFoundException ex) {
                        Log.v(LOGTAG, "Maps app not found!");
                    }
                }
            });

            rootView.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Response.Listener<CancelBookingResponse> listener = new Response.Listener<CancelBookingResponse>() {
                        @Override
                        public void onResponse(CancelBookingResponse response) {
                            Log.v(LOGTAG, "Cancel success "+response.toString());
                           MainActivity host = (MainActivity) getActivity();
                            if (host != null) {
                                host.showPage(MainActivity.Screen.HOME_PAGE);
                            }
                        }
                    };

                    mDataMan.getOlaService().cancelRide(mBookingResponse.getCrn(), listener, errorListener);
                }
            });
        }

        return rootView;
    }
}
