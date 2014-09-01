package com.s212021265.dylancoetzee.ContactsLog.repository.Impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Dylan 21/08/2014.
 */
public class DBAdapter extends SQLiteOpenHelper {

    public static final String TABLE_CONTACT = "contacts";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_CELL = "cell_number";
    public static final String COLUMN_ADDRESS = "home_address";

    public static final String DATABASE_NAME = "data.db";
    public static final int DATABASE_VERSION = 1;

    public static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_CONTACT + " ( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_FIRST_NAME + " text not null, "
            + COLUMN_LAST_NAME + " text not null, "
            + COLUMN_EMAIL + " text not null, "
            + COLUMN_CELL + " text not null, "
            + COLUMN_ADDRESS + " text not null);";

    public DBAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(DBAdapter.class.getName(), "Upgrading database from version "
                + oldVersion + " to "
                + newVersion +" which will destroy all old data");

        database.execSQL("DROP TABLE IF EXISTS" + TABLE_CONTACT);
        onCreate(database);
    }
}
