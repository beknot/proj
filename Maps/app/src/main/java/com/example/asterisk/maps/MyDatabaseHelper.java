package com.example.asterisk.maps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASENAME = "mydatabase";
    public MyDatabaseHelper(Context context) {
        super(context, DATABASENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS datatable (id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,address VARCHAR,phone VARCHAR,email VARCHAR)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS datababse");
        onCreate(db);

    }

    public void addRecords(DataModule m) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",m.getName());
        cv.put("address",m.getAddress());
        cv.put("email",m.getEmail());
        cv.put("phone",m.getPhone());
        db.insert("datatable",null,cv);
        db.close();
    }

    public List<DataModule> readRecords() {
        List<DataModule> mydata = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM datatable",null);
        if (cursor.getCount()!=0) {
            if (cursor.moveToFirst()) {
                do {
                    DataModule m = new DataModule();
                    m.setId(cursor.getInt(0));
                    m.setName(cursor.getString(cursor.getColumnIndex("name")));
                    m.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                    m.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                    m.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                    mydata.add(m);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        db.close();
        return mydata;
    }

    public void deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM datatable where id="+id);
        db.close();
    }

    public void updateRecords(DataModule m) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",m.getName());
        cv.put("address",m.getAddress());
        cv.put("email",m.getEmail());
        cv.put("phone",m.getPhone());
        db.update("datatable",cv,"id="+m.getId(),null);
        db.close();
    }
}
