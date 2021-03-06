package com.example.student.s091101;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import static android.Manifest.permission.*;
import static android.Manifest.*;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        mMap.setBuildingsEnabled(true);
        // 顯示目前所在位置鈕
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, 1
            );
        } else {
            mMap.setMyLocationEnabled(true);
        }


        // Add a marker in Sydney and move the camera
        LatLng taipei = new LatLng(24.9834668,121.4525423);
        mMap.addMarker(new MarkerOptions().position(taipei).title("Marker in Taipei").icon(BitmapDescriptorFactory.fromResource(R.drawable.cat)));

        //新增位置標示
        MarkerOptions mo1 = new MarkerOptions().position(new LatLng(25.234, 121.5)).title("Test"); //Simple factory pattern
        MarkerOptions mo2 = new MarkerOptions().position(new LatLng(25.034, 121.8)).title("222");
        mMap.addMarker(mo1);
        mMap.addMarker(mo2);

        //畫直線
        LatLng p1 = new LatLng(25.0, 121.533);
        LatLng p2 = new LatLng(25.3, 121.533);
        PolylineOptions options = new PolylineOptions();
        options.add(p1, p2);
        options.width(5);
        options.color(Color.MAGENTA);
        options.zIndex(1); // 疊層 id( 數字越高圖層越上層 )
        mMap.addPolyline(options);


        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(taipei, 17);
        //mMap.moveCamera(update);
        mMap.animateCamera(update, 5000, null); //focus動畫效果
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(this, permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }
}
