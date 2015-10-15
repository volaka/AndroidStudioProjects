package com.volaka.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by volaka on 24.07.2015.
 */
public class DatabaseContext extends SQLiteOpenHelper {

    /*
    Variables
     */
    public static final String DATABASE_NAME = "database.db";
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_1 = "ID";
    public static final String COLUMN_2 = "USERNAME";
    public static final String COLUMN_3 = "PASSWORD";


    //Constructer
    public DatabaseContext(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    /*
    Create database if not exists or update if exists
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "USERNAME TEXT, " +
                "PASSWORD TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
    /*
    checks if 'username' exists in database
    and returns true or false
     */
    public boolean doesExist(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE USERNAME = '"+username+"'",new String[]{});
        if(cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /*
    adds user if not exists in database
     */
    public Integer addUser(String username, String password) {
        boolean res = doesExist(username);  //checks if exists
        try {
            if (!res) {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_2, username);
                contentValues.put(COLUMN_3, password);
                db.insert(TABLE_NAME,null,contentValues);
                return 1;
            } else {
                return 0;
            }
        }
        catch (Exception ex) {
            return -1;
        }

    }

    /*
    Searches if entered username and password exist in database
    method returns a Cursor object
    if they not exist it returns null
     */
    public Cursor loginMatch(String username, String password) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_2 + " = '" + username + "' AND " + COLUMN_3 + " = '" + password+"'", new String[]{});
            return cursor;
        }
        catch (Exception ex) {
            return null;
        }
    }

    /*
    gets all data from database and returns a Cursor
     */
    public Cursor getAllData() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
            return res;
        }
        catch (Exception ex) {
            return null;
        }
    }
}