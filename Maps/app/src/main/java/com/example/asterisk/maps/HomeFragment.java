package com.example.asterisk.maps;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends AppCompatActivity {
    private static final int REQUEST_LOCATION = 1;

    Button panic, hospital, ambulance, police, fire;
    LocationManager locationManager;
    String lat,lng,msg,getMsg,getM;
    EditText etMsg;
    Spinner spinner;
    String[] listMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_fragment);

        panic = findViewById(R.id.btnPanic);
        hospital = findViewById(R.id.btnHospital);
        ambulance = findViewById(R.id.btnAmbulance);
        police = findViewById(R.id.btnPolice);
        fire = findViewById(R.id.btnFire);
        etMsg = findViewById(R.id.etMsg);
        spinner = findViewById(R.id.spinner);

        listMsg =getResources().getStringArray(R.array.messages);
        ArrayAdapter<String> msgList = new ArrayAdapter<String>(HomeFragment.this,android.R.layout.simple_list_item_1,listMsg);
        msgList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(msgList);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0:
                        msg = listMsg[position];
                        break;
                    case 1:
                        msg= listMsg[position];
                        break;
                    case 2:
                        msg = listMsg[position];
                        break;
                    case 3 :
                        msg = listMsg[position];
                        break;
                    case 4 :
                        msg = listMsg[position];
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        panic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    buildAlertMessageNoOps();
                } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    getLocation();
                }
                //Intent i = new Intent(HomeFragment.this,IntentActivity.class);
                //startActivity(i);
            }
            private void buildAlertMessageNoOps() {
                final AlertDialog.Builder builder = new AlertDialog.Builder(HomeFragment.this);
                builder.setMessage("Please turn ON your GPS").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                final AlertDialog alert = builder.create();
                alert.show();
            }
            private void getLocation() {
                if (ActivityCompat.checkSelfPermission(HomeFragment.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                        (HomeFragment.this,Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(HomeFragment.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);
                } else {
                    android.location.Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (location != null) {
                        double latitide = location.getLatitude();
                        double longitude = location.getLongitude();
                        lat = String.valueOf(latitide);
                        lng = String.valueOf(longitude);

                        getM = etMsg.getText().toString();
                        if (getM.length() == 0 && msg.equals("Select messages")) {
                            getMsg = "I need help";
                        } else if(getM.length() != 0 && msg.equals("Select messages")) {
                            getMsg = getM;
                        } else { getMsg = (getM+" "+msg); }

                        String latLng = (lat+", "+lng);
                        Intent intent=new Intent(HomeFragment.this,IntentActivity.class);
                        intent.putExtra("latLng", latLng);
                        intent.putExtra("message", getMsg);
                        startActivity(intent);

                    } else {
                        Toast.makeText(HomeFragment.this,"Unable to fetch your location",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeFragment.this,HospitalView.class);
                startActivity(i);
            }
        });
        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeFragment.this,AmbulanceView.class);
                startActivity(i);
            }
        });
        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeFragment.this,PoliceView.class);
                startActivity(i);
            }
        });
        fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeFragment.this,FireView.class);
                startActivity(i);
            }
        });
    }
}
