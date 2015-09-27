package com.ck.hack.olaalert.ui;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ck.hack.olaalert.R;
import com.ck.hack.olaalert.app.DataManager;
import com.ck.hack.olaalert.app.OlaAlertApp;
import com.ck.hack.olaalert.domain.CabBookingResponse;
import com.ck.hack.olaalert.domain.CabCategory;
import com.ck.hack.olaalert.domain.ProductsResponse;
import com.ck.hack.olaalert.domain.ResponseListener;
import com.ck.hack.olaalert.service.OlaService;
import com.ck.hack.olaalert.utils.BackgroundLooper;
import com.ck.hack.olaalert.utils.Utils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

/**
 * Created by Satvik on 27/09/15.
 */
public class HomeFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMapClickListener,
        GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerDragListener, LocationListener, GoogleMap.OnMyLocationChangeListener, View.OnClickListener {

    private static final String LOGTAG = HomeFragment.class.getSimpleName();


    private GoogleApiClient mGoogleApiClient;
    private LocationManager mLocationManager;
    private Marker mMapMarker;
    private ProgressBar mProgressBar;
    private MapView mMapView;
    private GoogleMap mGoogMap;
    private Geocoder mGeoCoder;
    private LatLng mCurrentLatLng;
    private Address mCurrentAddress;
    private Location mLocation;
    private DataManager mDataManager;
    private Handler mBkgHandler = new Handler(BackgroundLooper.getInstance());
    private Button mMedicalEmergencyButton;

    public HomeFragment() {
        // NO OP
    }

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity host = getActivity();
        mGoogleApiClient = new GoogleApiClient.Builder(host.getApplicationContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mLocationManager = (LocationManager) host.getSystemService(Context.LOCATION_SERVICE);
        mBkgHandler = new Handler(BackgroundLooper.getInstance());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v(LOGTAG, "onCreateView+ Tab: ");

        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mMapView = (MapView) rootView.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.prog_bar);
        mProgressBar.setVisibility(View.VISIBLE);

        mGoogMap = mMapView.getMap();
        if (mGoogMap != null) {
            mGoogMap.setOnMarkerDragListener(this);
            mGoogMap.setOnMapClickListener(this);
            mGoogMap.setOnMyLocationChangeListener(this);
            mGoogMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    return false;
                }
            });
        }

        Activity host = getActivity();
        mGeoCoder = new Geocoder(host);

        mDataManager = ((OlaAlertApp) (host.getApplicationContext())).getDataManager();

        MapsInitializer.initialize(host);
        if (mCurrentLatLng != null) {
            updateMarker(mCurrentLatLng);
        }

        mMedicalEmergencyButton = (Button) rootView.findViewById(R.id.emerg_button);
        mMedicalEmergencyButton.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
        if (mGoogMap != null) {
            mGoogMap.setMyLocationEnabled(true);
        }

        if (mCurrentLatLng == null) {
            mLocationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, HomeFragment.this, null);
        } else {
            mLocationManager.removeUpdates(HomeFragment.this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
        if (mGoogMap != null) {
            mGoogMap.setMyLocationEnabled(false);
        }
        mLocationManager.removeUpdates(HomeFragment.this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.v(LOGTAG, "Connected to google play services");
        android.location.Location currLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        LatLng currentLatLng = null;
        if (currLocation != null) {
            currentLatLng = new LatLng(currLocation.getLatitude(), currLocation.getLongitude());
            mLocationManager.removeUpdates(HomeFragment.this);
        }
        updateMarker(currentLatLng);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.v(LOGTAG, "Connection suspended! Retrying again!");
        mGoogleApiClient.connect();
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(LOGTAG, "Connection failed " + connectionResult.getResolution() + " error code: " + connectionResult.getErrorCode());
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }

        if (mMapView != null) {
            mMapView.onPause();
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Log.v(LOGTAG, "onMapClick");
        updateMarker(latLng);
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        Log.v(LOGTAG, "onMapLongClick");
        updateMarker(latLng);
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        // NO OP
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        // NO OP
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        updateMarker(marker.getPosition());
    }


    private void updateMarker(final LatLng newLatLng) {

        if (newLatLng == null) {
            return;
        }
        mProgressBar.setVisibility(View.VISIBLE);
        mCurrentLatLng = newLatLng;
        if (mMapMarker != null) {
            mMapMarker.remove();
        }

        mMapMarker = mGoogMap.addMarker(new MarkerOptions()
                .position(newLatLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_maps_location_history))
                .draggable(true));
        if (mCurrentLatLng != null) {
            mGoogMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mCurrentLatLng, Utils.DEFAULT_CAMERA_ZOOM), 100, null);
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLocationChanged(android.location.Location location) {
        Log.v(LOGTAG, "onLocCHanged");
        if (mCurrentLatLng == null) {
            LatLng newLocation = new LatLng(location.getLatitude(), location.getLongitude());
            updateMarker(newLocation);
        }
        mLocationManager.removeUpdates(HomeFragment.this);
    }


    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        // NO OP
    }

    @Override
    public void onProviderEnabled(String s) {
        // NO OP
    }

    @Override
    public void onProviderDisabled(String s) {
        // NO OP
    }

    @Override
    public void onMyLocationChange(android.location.Location location) {
        Log.v(LOGTAG, "onMyLocChanged");
        if (mCurrentLatLng == null) {
            LatLng newLatLng = new LatLng(location.getLatitude(), location.getLongitude());
            updateMarker(newLatLng);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.emerg_button) {
            OlaService olaService = mDataManager.getOlaService();
            mProgressBar.setVisibility(View.VISIBLE);
            Response.Listener<ProductsResponse> listener = new Response.Listener<ProductsResponse>() {
                @Override
                public void onResponse(ProductsResponse response) {
                    Log.v(LOGTAG, "success ");
                    for (CabCategory category :  response.getCategories()) {
                        Log.v(LOGTAG, ""+category.getDisplayName());
                        bookCab(category.getDisplayName());
                        break;
                    }
                }
            };

            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.v(LOGTAG, "Listing Products error : " + error.getMessage());
                    Activity host = getActivity();
                    if (host != null) {
                        Toast.makeText(host, "No cabs found!", Toast.LENGTH_LONG).show();
                        mProgressBar.setVisibility(View.GONE);
                    }
                }
            };
            olaService.listCabProducts(mCurrentLatLng, listener, errorListener);
        }
    }

    private void bookCab(String category) {
        Response.Listener<CabBookingResponse> listener = new Response.Listener<CabBookingResponse>() {
            @Override
            public void onResponse(CabBookingResponse response) {
                Log.v(LOGTAG, "success ");
                mProgressBar.setVisibility(View.GONE);
                MainActivity host = (MainActivity) getActivity();
                Gson gson = new Gson();
                host.showDriverDetailsPage(gson.toJson(response, CabBookingResponse.class), mCurrentLatLng);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "No cabs found!", Toast.LENGTH_LONG).show();
                mProgressBar.setVisibility(View.GONE);
                Log.v(LOGTAG, "Booking error : " + error.getMessage() + " "+error.getCause());
            }
        };
        OlaService olaService = mDataManager.getOlaService();
        olaService.bookCab(category, mCurrentLatLng, listener, errorListener);
    }
}
