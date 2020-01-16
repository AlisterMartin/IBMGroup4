package com.ibm.watson.developer_cloud.android.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String conferenceTitle = "Chi Conference 2019";
    private String conferenceAddress = "Exhibition Way\nGlasgow\nG3 8YW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);

        TextView addressT = (TextView) findViewById(R.id.addressT);
        TextView address = (TextView) findViewById(R.id.address);
        addressT.setText(conferenceTitle);
        address.setText(conferenceAddress);

        FloatingActionButton bounceBut = (FloatingActionButton) findViewById(R.id.FAB);
        Animation bounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        bounceBut.startAnimation(bounce);

        FloatingActionButton fab = findViewById(R.id.FAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri gmmIntentUri = Uri.parse("google.navigation:q=Exhibition+Way+Glasgow&mode=d");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }

            }
        });
    }


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
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        // Add a marker in Sydney and move the camera
        LatLng venue = new LatLng(55.861224, -4.288927);

        mMap.addMarker(new MarkerOptions().position(venue).title("Scottish Event Campus Ltd Glasgow, Scotland, G3 8YW"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(venue));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(venue,15));

    }

}

