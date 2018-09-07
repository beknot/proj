package com.example.asterisk.maps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MapHospital extends AppCompatActivity {

    String[] names={"Tilganga Eye Hospital","Korea Nepal Friendship Hospital","Civil Service Hospital","Kist Medical College(Sinamangal)",
            "Bir Hospital","TU Teaching Hospital","Bhaktapur Cancer Hospital",
            "Medicare National Hospital & Research Center","Kathmandu Clinic Of Cosmetic Surgery","Nepal Dental Hospital",
            "Patan Hospital", "Kathmandu model Hospital",
            "Nepal Orthopaedic Hospital","Kantipur Hospital,New Baneshwor",
            "Norvic International Hospital", "Bhaktapur Redcross",
            "Om Hospital",};
    String[] contact={"014423684","016633442","014107000","015201496",
            "014240805","014412404","014253276","016611532",
            "014467067","014771045","014433697",
            "015522295","014240805",
            "014493725", "014498757",
            "014258554", "016612266",
            "014418430"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_hospital);
    }
}
