package com.youssef1emad.google_map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class InfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    Context context ;
    View view;
    @Override
    public View getInfoWindow(Marker marker) {
        initView(marker,view);
        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {

        return null;

    }

    public InfoWindowAdapter(Context context) {
        this.context = context ;

        view = LayoutInflater.from(context).inflate(R.layout.clickable_form,null);

    }

    private void initView(Marker marker , View view){
        EditText edt = view.findViewById(R.id.editText2);
        Button btnn = view.findViewById(R.id.btn);

        edt.setText("magdy "+marker.getTitle());
        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "hghgdulhjv", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
