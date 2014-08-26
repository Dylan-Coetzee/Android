package com.s212021265.dylancoetzee.ContactsLog.repository.Impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Kurvin Hendricks on 18/08/2014.
 */
public class DBAdapter extends SQLiteOpenHelper{

    public static final String TABLE_CONTACTS = "contacts";

    /*private int id;
    private String fName;
    private String lName;
    private String emailAddress;
    private String cellNumber;
    private String homeAddress;*/

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FNAME = "fName";
    public static final String COLUMN_LNAME = "lName";
    public static final String COLUMN_EMAILADDRESS = "emailAddress";
    public static final String COLUMN_CELL_NUMBER = "cellNumber";
    public static final String COLUMN_HOMEADDRESS = "homeAddress";

    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 1;

    public static final String CREATE_CONTACTS_TABLE = "create table " + "IF NOT EXISTS "
            + TABLE_CONTACTS + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_LNAME + " text not null, "
            + COLUMN_CELL_NUMBER + " text not null, "
            + COLUMN_FNAME + " , "
            + COLUMN_EMAILADDRESS + " , "
            + COLUMN_HOMEADDRESS + " ); ";



   public DBAdapter(Context context){
       super(context, DATABASE_NAME, null, DATABASE_VERSION);
   }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(DBAdapter.class.getName(), "Upgrading database from version "
            + oldVersion + " to " + newVersion + " , which will destroy"
            + "all old data"
        );

        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        onCreate(db);
    }
}
