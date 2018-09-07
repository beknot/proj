package com.example.asterisk.maps;

import android.Manifest;
import android.Manifest.permission;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    private static final int REQUEST_CALL = 1;
    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;
    private static final int REQUEST_LOCATION = 1;

    List<DataModule> mydata = new ArrayList<>();
    Context c;
    public MyAdapter(IntentActivity mainActivity, List<DataModule> data) {
        c = mainActivity;
        mydata = data;
    }

    @Override
    public int getCount() {
        return mydata.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(c).inflate(R.layout.myadapter,null);

        TextView name = convertView.findViewById(R.id.name);
        DataModule m = mydata.get(position);
        String myName = m.getName(); //name.setText(mydata.get(position).getName());
        name.setText(myName);
        final String myPhone = m.getPhone(); //name.setText(mydata.get(position).getName());
        TextView phone = convertView.findViewById(R.id.phone);
        phone.setText(myPhone);
        ImageView call = convertView.findViewById(R.id.call);
        ImageView btnMessage = convertView.findViewById(R.id.btnMessage);
        final View finalConvertView = convertView;

        //String myAddress = m.getAddress(); //name.setText(mydata.get(position).getName());
        //TextView email= convertView.findViewById(R.id.email);
        //TextView address = convertView.findViewById(R.id.address);
        //address.setText(myAddress);
        //String myEmail = m.getEmail(); //name.setText(mydata.get(position).getName());
        //email.setText(myEmail);



        btnMessage.setEnabled(false);
        if (checkPermission(Manifest.permission.SEND_SMS)) {
            btnMessage.setEnabled(true);
        }  else {
            ActivityCompat.requestPermissions((Activity) finalConvertView.getContext(),new String[]{Manifest.permission.SEND_SMS},SEND_SMS_PERMISSION_REQUEST_CODE);
        }

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = myPhone.toString();
                String message = "Help";
                if (num == null || num.length() == 0 || message == null || message .length() == 0) {
                    return;
                }
                if(checkPermission(Manifest.permission.SEND_SMS)) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(num,null,message ,null,null);
                    Toast.makeText(c.getApplicationContext(),"Message sent",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(c.getApplicationContext(),"Message sending failed",Toast.LENGTH_SHORT).show();

                }
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }

            private void makePhoneCall() {
                String number = myPhone.toString();
                if (number.trim().length()>0) {
                    if (ContextCompat.checkSelfPermission(finalConvertView.getContext(), permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((Activity) finalConvertView.getContext(),new String[] {permission.CALL_PHONE},REQUEST_CALL);
                        Toast.makeText(finalConvertView.getContext(),"Not calling",Toast.LENGTH_SHORT).show();
                    } else {
                        String dial = "tel:"+number;
                        c.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                        Toast.makeText(finalConvertView.getContext(),"Calling",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(finalConvertView.getContext(),"Enter phone number",Toast.LENGTH_SHORT).show();
                }
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                View convertView = LayoutInflater.from(c).inflate(R.layout.activity_update,null);
                final EditText name = convertView.findViewById(R.id.name);
                //final EditText email = convertView.findViewById(R.id.email);
                //final EditText address = convertView.findViewById(R.id.address);
                final EditText phone = convertView.findViewById(R.id.phone);
                Button update = convertView.findViewById(R.id.update);
                Button delete = convertView.findViewById(R.id.delete);
                Button cancel = convertView.findViewById(R.id.cancel);

                AlertDialog.Builder alert = new AlertDialog.Builder(c);
                alert.setView(convertView);
                final AlertDialog dialog = alert.create();
                dialog.show();

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DataModule m = new DataModule();
                        m.setName(name.getText().toString());
                        //m.setEmail(email.getText().toString());
                        //m.setAddress(address.getText().toString());
                        m.setPhone(phone.getText().toString());
                        m.setId(mydata.get(position).getId());
                        MyDatabaseHelper db = new MyDatabaseHelper(c);
                        db.updateRecords(m);
                        dialog.dismiss();
                        IntentActivity ml = (IntentActivity)c;
                        ml.onResume();
                    }
                });
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyDatabaseHelper db = new MyDatabaseHelper(c);
                        db.deleteData(mydata.get(position).getId());
                        dialog.dismiss();
                        IntentActivity m = (IntentActivity) c;
                        m.onResume();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                return false;
            }
        });
        return convertView;
    }

    private boolean checkPermission(String sendSms) {
        int check = ContextCompat.checkSelfPermission(c.getApplicationContext(),sendSms);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

}
