package com.example.asterisk.maps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ViewData extends AppCompatActivity {
    TextView id,name,email,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
    }
}
