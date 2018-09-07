package com.example.asterisk.maps;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

class MyAdapter2 extends BaseAdapter {
    Context c;
    String[] myn;
    public MyAdapter2(DisplayNear displayNear, String[] names) {
        c = displayNear;
        myn = names;
    }

    @Override
    public int getCount() {
        return myn.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(c).inflate(R.layout.nearlayout,null);
        TextView name=convertView.findViewById(R.id.tv1);
       // TextView number=convertView.findViewById(R.id.tv2);
        //TextView latitude=convertView.findViewById(R.id.tv3);
        //TextView longitude=convertView.findViewById(R.id.tv4);
        name.setText(String.valueOf(myn[position]));
        //number.setText(String.valueOf(myp[position]));
        //latitude.setText(String.valueOf(mylat[position]));
        //longitude.setText(String.valueOf(mylng[position]));
        return convertView;
    }
}
