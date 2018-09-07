package com.example.asterisk.maps;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AmbulanceView extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;
    ListView listView;
    TextView tv1,tv2;
    ImageView call;

    String[] names={"Paropakar Ambulance Service","Lalitpur Redcross Ambulance","Bishal Bazar Ambulance Service","Redcross Ambulance Service", "Agrawal Sewa Center",
            "Aasara Drug Rehabilitation", "Jantipur Ambublance","Maiti Nepal Ambulance","Marwadi Sewa Samiti", "Model Ambulance",
            "Nepal Chamber Ambulance", "Satya Sai Ambulance", "Thimi Ambulance",};
    String[] contact={"014260859","015545666","014244121","014228094","014424875",
            "014384881","014473488","014494816","014255540","014384881",
            "014228094","014498035","016631000"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);

        listView = findViewById(R.id.listView);

        CustomerAdapter customerAdapter = new CustomerAdapter();
        listView.setAdapter(customerAdapter);
    }

    class CustomerAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int position) {
            return 0;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.ambulance_layout, null);
            tv1 = convertView.findViewById(R.id.name);
            tv2 = convertView.findViewById(R.id.phone);
            call = convertView.findViewById(R.id.call);

            tv1.setText(names[position]);
            tv2.setText(contact[position]);

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    makePhoneCall();
                }
                private void makePhoneCall() {
                    String number = contact[position].toString();
                    if (number.trim().length() > 0) {
                        if (ContextCompat.checkSelfPermission(AmbulanceView.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(AmbulanceView.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                            Toast.makeText(AmbulanceView.this, "Not calling", Toast.LENGTH_SHORT).show();
                        } else {
                            String dial = "tel:" + number;
                            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                            Toast.makeText(AmbulanceView.this, "Calling", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AmbulanceView.this, "Enter phone number", Toast.LENGTH_SHORT).show();
                    }
                }
                public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                    if (requestCode == REQUEST_CALL) {
                        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                            makePhoneCall();
                        } else {
                            Toast.makeText(AmbulanceView.this, "Permission denied", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            return convertView;
        }}





}
