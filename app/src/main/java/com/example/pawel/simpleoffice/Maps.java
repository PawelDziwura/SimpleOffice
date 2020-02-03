package com.example.pawel.simpleoffice;

import android.app.Activity;
import android.app.ApplicationErrorReport;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //setUpMapIfNeed();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        int status= GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());


        if(status== ConnectionResult.SUCCESS) {

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
        else{
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, (Activity)getApplicationContext(), 10);
            dialog.show();
        }
    }

    //@Override
    //protected void onResume(){
    //    super.onResume();
    //    setUpMapIfNeed();
   //}


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        // Add a marker in Sydney and move the camera
        LatLng krakow = new LatLng(50.06465, 19.94498);
        mMap.addMarker(new MarkerOptions().position(krakow).title("Marker in Krakow"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(krakow));
    }


}
