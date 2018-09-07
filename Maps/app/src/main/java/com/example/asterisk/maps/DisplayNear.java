package com.example.asterisk.maps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class DisplayNear extends AppCompatActivity {
    String []names={"n","a","m","e"};
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_near);

        ListView displaylist  = findViewById(R.id.displaylist);
        displaylist.setAdapter(new MyAdapter2(DisplayNear.this,names));


        final int EARTH_RADIUS = 6371;
        Double []lat={27.00};
        Double []lng={30.00};

        Double startLat=0.00;
        Double startLng=0.00;
        Double endLat;
        Double endLng;
        Double dist=0.0000,distance=0.0000;
        Double result=0.0000,shortest=0.0000;
        int j=0;

        for(int i=0;i<lat.length;i++) {
            endLat = lat[i];
            endLng = lng[i];
            Double dLat = Math.toRadians((lat[i] - startLat));
            Double dLng = Math.toRadians((lng[i] - startLng));

            double startLats = Math.toRadians(startLat);
            System.out.println("startLat "+startLats);
            double endLats = Math.toRadians(endLat);
            System.out.println("endLat "+endLats);
            double hLat= Math.pow(Math.sin(dLat/2),2);
            double hLng= Math.pow(Math.sin(dLng/2),2);

            dist = hLat + Math.cos(startLats) * Math.cos(endLats * hLng);

            System.out.println("dist "+dist);
            distance = 2 * Math.atan2(Math.sqrt(dist), Math.sqrt(1 - dist));
            System.out.println("distance "+distance);
            System.out.println(""+lat[i]+" "+lng[i]);
            result = EARTH_RADIUS*distance;
            System.out.println("result "+result);
            if (i==0) {
                shortest = distance;
                j=i;
            } else if(shortest<distance){
                j=i;
            }

        }
        System.out.println("Closest: "+shortest+"index: "+j);
        //TextView tv = findViewById(R.id.tv);
        //tv.setText(String.valueOf(shortest));
        }
}