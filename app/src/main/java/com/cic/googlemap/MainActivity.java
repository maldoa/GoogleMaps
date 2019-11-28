package com.cic.googlemap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private LatLng[] sitios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGoogleMaps();
    }

    private void initGoogleMaps() {
        //Obtain the SupportMapFragment and get notified whem the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void moveCameraToPosition(LatLng newPosition) {
        // Zoom in, animating the camera.
        mMap.animateCamera(CameraUpdateFactory.zoomIn());

        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

        // Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(newPosition)      // Sets the center of the map to Mountain View
                .zoom(14)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Mexico City and move the camera
        LatLng mexicoCity = new LatLng(-99.133209, 19.432608);
        mMap.addMarker(new MarkerOptions().position(mexicoCity).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mexicoCity));
        initMarkers();
    }

    private void initMarkers() {
        int numero_sitios = 8;
        this.sitios = new LatLng[numero_sitios];
        this.sitios[0] = new LatLng(40.4319077,116.5703749);
        this.sitios[1] = new LatLng(43.7100385,10.3977407);
        this.sitios[2] = new LatLng(51.5006000,-0.1246500);
        this.sitios[3] = new LatLng(29.9792345,31.1342019);
        this.sitios[4] = new LatLng(48.8583701,2.2944813);
        this.sitios[5] = new LatLng(19.6891100,-98.8473000);
        this.sitios[6] = new LatLng(43.0834200,-79.0662700);
        this.sitios[7] = new LatLng(36.0544445,-112.1401108);
    }

    @Override
    public void onClick(View view) {
        moveCameraToPosition(sitios[Integer.valueOf(view.getTag().toString())]);
    }
}
