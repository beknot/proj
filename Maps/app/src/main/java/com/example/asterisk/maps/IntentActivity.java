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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asterisk.maps.Model.Location;

import java.util.ArrayList;
import java.util.List;

public class IntentActivity extends AppCompatActivity {
    private static final int REQUEST_LOCATION = 1;
    private Button add;
    private TextView gps,ms;
    private ListView lv;
    String latLng,msg,message;
    String phoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        add = findViewById(R.id.add);
        lv = findViewById(R.id.lv);
        //tv = findViewById(R.id.total);

        gps = findViewById(R.id.gps);
        ms = findViewById(R.id.ms);

        Bundle b = new Bundle();
        b = getIntent().getExtras();
        latLng = b.getString("latLng");
        msg = b.getString("message");
        gps.setText(latLng);
        ms.setText(msg);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View convertView = LayoutInflater.from(IntentActivity.this).inflate(R.layout.activity_add_data,null);
                final EditText name = convertView.findViewById(R.id.name);
                //final EditText email = convertView.findViewById(R.id.email);
                //final EditText address = convertView.findViewById(R.id.address);
                final EditText phone = convertView.findViewById(R.id.phone);
                Button save = convertView.findViewById(R.id.save);
                Button cancel = convertView.findViewById(R.id.cancel);



                //ActivityCompat.requestPermissions(IntentActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);

                AlertDialog.Builder alert = new AlertDialog.Builder(IntentActivity.this);
                alert.setView(convertView);
                final AlertDialog dialog = alert.create();
                dialog.show();

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DataModule m = new DataModule();
                        m.setName(name.getText().toString());
                        //m.setEmail(email.getText().toString());
                        //m.setAddress(address.getText().toString());
                        m.setPhone(phone.getText().toString());
                        phoneNo = phone.getText().toString();
                        MyDatabaseHelper db = new MyDatabaseHelper(IntentActivity.this);
                        db.addRecords(m);
                        Toast.makeText(IntentActivity.this,"Contact added successfully",Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                        onResume();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<DataModule> data = new ArrayList<>();
        MyDatabaseHelper db = new MyDatabaseHelper(IntentActivity.this);
        data = db.readRecords();
        //tv.setText("Total records = " +data.size());
        lv.setAdapter(new MyAdapter(IntentActivity.this,data));
    }

}
