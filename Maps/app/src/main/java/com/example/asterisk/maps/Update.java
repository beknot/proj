package com.example.asterisk.maps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class Update extends AppCompatActivity {
    Button update,delete,cancel;
    EditText name,address,email,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        cancel = findViewById(R.id.cancel);
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
    }
}
