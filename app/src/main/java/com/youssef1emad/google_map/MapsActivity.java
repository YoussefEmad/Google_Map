package com.youssef1emad.google_map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    /**youssef Emad android developer**/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
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

        if (mMap != null) {
            mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    View v = getLayoutInflater().inflate(R.layout.info_window, null);
                    TextView tvLocality = v.findViewById(R.id.tv_locality);
                    TextView tvLat = v.findViewById(R.id.tv_lat);
                    TextView tvLog = v.findViewById(R.id.tv_lng);
                    TextView tvSnippet = v.findViewById(R.id.tv_snippet);

                    LatLng ll = marker.getPosition();
                    tvLocality.setText(marker.getTitle());
                    tvLat.setText("latitude" + ll.latitude);
                    tvLog.setText("longitude" + ll.longitude);
                    tvSnippet.setText(marker.getSnippet());

                    return v;
                }
            });
        }

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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

    private void goToLocation (double lat , double lng) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLng(ll);
        mMap.moveCamera(update);
    }
    private void goToLocationZoom(Double lat, Double lng, float zoom)
    {
        LatLng li = new LatLng(lat,lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(li, zoom);
        mMap.moveCamera(update);
    }
    Marker marker;
    public void geoLocate(View view)  throws IOException
    {Marker marker;
        EditText stopPort = findViewById(R.id.editText);
        if (stopPort.getText().toString().length() > 0)
        {
            String location = stopPort.getText().toString().trim();
            Geocoder gc = new Geocoder(this);
            List<Address> list = gc.getFromLocationName(location, 1);
            if (list != null && list.size() != 0)
            {
                Address address = list.get(0);
                String locality = address.getLocality();
                Toast.makeText(this, locality, Toast.LENGTH_LONG).show();
                Double lat = address.getLatitude();
                Double lng = address.getLongitude();
                goToLocationZoom(lat, lng, 15);


                MarkerOptions options = new MarkerOptions()
                        .title(locality)
                        .position(new LatLng(lat, lng));
                        //.snippet("iam here");

                marker =  mMap.addMarker(options);
            }else
            {
                Toast.makeText(this, "No such address ya youssef", Toast.LENGTH_SHORT).show();
            }
        }else
        {
            Toast.makeText(this, "set your destination first \n i can`t take you to no where land :D", Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mapTypeNone:
                mMap.setMapType(mMap.MAP_TYPE_NONE);
                break;
            case R.id.mapTypeNormal:
                mMap.setMapType(mMap.MAP_TYPE_NORMAL);
                break;
            case R.id.mapTypeTerrain:
                mMap.setMapType(mMap.MAP_TYPE_TERRAIN);
                break;
            case R.id.mapTypeSatellite:
                mMap.setMapType(mMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.mapTypeHybrid:
                mMap.setMapType(mMap.MAP_TYPE_HYBRID);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
