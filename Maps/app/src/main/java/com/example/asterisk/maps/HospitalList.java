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

public class HospitalList extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;
    ListView listView;
    TextView tv1,tv2;
    ImageView call;

    String[] names={"Nepal Eye Bank","Nepal Eye Hospital", "Tilganga Eye Hospital","Bhaktapur Cancer Hospital","Madhyapur Hospital",
            "Grande City Hospital","Siddhi Memorial Hospital","Korea Nepal friendship Hospital","Civil Service Hospital","Kist Medical College(Sinamangal)",
            "Medicare National Hospital & Research Center","Kathmandu Clinic Of Cosmetic Surgery","Nepal Dental Hospital",
            "Bir Hospital","Nepal Police Hospital","TU Teaching Hospital","Maternity hospital", "Teku Hospital",
            "Patan Hospital","Bhaktapur Hospital","Mental Hospital","Kanti Children Hospital", "Kathmandu model Hospital",
            "B&B Hospital","Bir Hospital","Cancer Hospital","Heart Disease Hospital", "Homeopathic Hospital",
            "Ayurvedic Hospital","Nepal Orthopaedic Hospital","Kathmandu Medical College(Teaching Sinamangal)","Nepal Medical Hospital Maharajgunj","Kantipur Hospital,New Baneshwor",
            "Norvic International Hospital","Martyr Gangalal Hospital","Miteri Hospital","Capital Hospital", "Bhaktapur Redcross",
            "National Kidney Center","Annapurna Nursing home","Everest Nursing Home","Himalaya Nursing home", "Kathmandu Medical College",
            "Kathmandu model Hospital","Iwamura Memorial Hospital","Summit Hospital Pvt. LTD.","Om Hospital","Nagarik Community Teaching Hospital","Blood Bank"};
    String[] contact={"014493684","014250691", "014423684","016611532","016631250",
            "014163500","016616589","016633442"," 014107000","015201496","014433697",
            "014467067","014771045",
            "014240805","014412430","014412404","014253276","014253396",
            "015522295","016610676","015521333","014400550","014240805",
            "015533206","014221988","016614430","015522092","015522092",
            "014259182","014493725", "014476152", "014476152","014498757",
            "014258554","014371322","014280555", "014244022", "016612266",
            "014429866","014222573","014482294","014415076", "014476152",
            "014240805","016612705","016631736","014418430","016637700","014225344"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital_list);

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
            convertView = getLayoutInflater().inflate(R.layout.hospital_layout, null);
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
                        if (ContextCompat.checkSelfPermission(HospitalList.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(HospitalList.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                            Toast.makeText(HospitalList.this, "Not calling", Toast.LENGTH_SHORT).show();
                        } else {
                            String dial = "tel:" + number;
                            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                            Toast.makeText(HospitalList.this, "Calling", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(HospitalList.this, "Enter phone number", Toast.LENGTH_SHORT).show();
                    }
                }
                public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                    if (requestCode == REQUEST_CALL) {
                        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                            makePhoneCall();
                        } else {
                            Toast.makeText(HospitalList.this, "Permission denied", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            return convertView;
        }}
}
