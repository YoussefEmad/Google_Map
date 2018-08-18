package com.youssef1emad.google_map;

import android.Manifest;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import static android.app.PendingIntent.getActivity;
import static java.security.AccessController.getContext;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    EditText editText2;
    Button btn;
    ImageView img1;

    /**youssef Emad android developer**/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        initializeDialog();
        img1 = (ImageView)findViewById(R.id.img1);

    }
    Dialog dialog;

    public void initializeDialog() {
        View viewDialog = LayoutInflater.from(this).inflate(
                R.layout.clickable_form, null);
        btn = viewDialog.findViewById(R.id.btn);
        AlertDialog.Builder builderRate = new AlertDialog.Builder(this, R.style.CustomDialog);
        builderRate.setCancelable(true);
        builderRate.setView(viewDialog);
        dialog = builderRate.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MapsActivity.this, "saved", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
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

        if (mMap != null) {
//            mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
//                @Override
//                public View getInfoWindow(Marker marker) {
//
//                    return null;
//                }
//
//                @Override
//                public View getInfoContents(Marker marker) {
//                    View v = getLayoutInflater().inflate(R.layout.clickable_form, null);
//                    // TextView tvLocality = v.findViewById(R.id.tv_locality);
//                    //TextView tvLat = v.findViewById(R.id.tv_lat);
//
//                    //TextView tvLog = v.findViewById(R.id.tv_lng);
//                    //TextView tvSnippet = v.findViewById(R.id.tv_snippet);
//                    EditText etc = (EditText) v.findViewById(R.id.editText2);
//                    Button btn = (Button) v.findViewById(R.id.btn);
//
//
//                    LatLng ll = marker.getPosition();
//                    //tvLocality.setText(marker.getTitle());
//                    //tvLat.setText("latitude" + ll.latitude);
//                    //tvLog.setText("longitude" + ll.longitude);
//                    //tvSnippet.setText(marker.getSnippet());
//
//                    return v;
//                }
//            });

        }


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

    public void upload(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        //Toast.makeText(this, "photo has been saved", Toast.LENGTH_SHORT).show();
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null){
            try {
                Uri uri = data.getData();
                img1.setImageURI(uri);
            }catch (Exception e){
                e.printStackTrace();
            }

        }


    }

    public class MarkerDemoActivity extends AppCompatActivity implements
            GoogleMap.OnInfoWindowClickListener,
            OnMapReadyCallback {

        private GoogleMap mMap;

        @Override
        public void onMapReady(GoogleMap map) {
            mMap = map;
    // Add markers to the map and do other map setup.
            if (mMap != null) {
//                mMap.setInfoWindowAdapter(new InfoWindowAdapter(MapsActivity.this));

                /*new GoogleMap.InfoWindowAdapter() {
                    @Override
                    public View getInfoWindow(Marker marker) {

                        return null;
                    }

                    @Override
                    public View getInfoContents(Marker marker) {
                        View v = getLayoutInflater().inflate(R.layout.clickable_form, null);
                        // TextView tvLocality = v.findViewById(R.id.tv_locality);
                        //TextView tvLat = v.findViewById(R.id.tv_lat);

                        //TextView tvLog = v.findViewById(R.id.tv_lng);
                        //TextView tvSnippet = v.findViewById(R.id.tv_snippet);
                        EditText etc = (EditText) v.findViewById(R.id.editText2);
                        Button btn = (Button) v.findViewById(R.id.btn);


                        LatLng ll = marker.getPosition();
                        //tvLocality.setText(marker.getTitle());
                        //tvLat.setText("latitude" + ll.latitude);
                        //tvLog.setText("longitude" + ll.longitude);
                        //tvSnippet.setText(marker.getSnippet());

                        return v;
                    }
                }*/

            }


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

            // Set a listener for info window events.
            mMap.setOnInfoWindowClickListener(this);
        }

        @Override
        public void onInfoWindowClick(Marker marker) {
            Toast.makeText(this, "Info window clicked",
                    Toast.LENGTH_SHORT).show();

        }
    }


        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).




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

    //Marker marker;
    public void geoLocate(View view)  throws IOException
    {Marker marker;
        EditText stopPort = findViewById(R.id.editText);
        if (stopPort.getText().toString().length() > 0)
        {
            String location = stopPort.getText().toString();
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

                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        dialog.show();
                        return false;
                    }
                });
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

    public void save (View view){
        Marker marker;
        Button button = (Button)findViewById(R.id.btn);
        EditText editText = (EditText)findViewById(R.id.editText2);
        Toast.makeText(MapsActivity.this, "fff", Toast.LENGTH_SHORT).show();

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {
                Toast.makeText(MapsActivity.this, "fff", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
