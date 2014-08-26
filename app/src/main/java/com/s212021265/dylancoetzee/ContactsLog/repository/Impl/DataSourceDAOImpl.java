package com.s212021265.dylancoetzee.ContactsLog.repository.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.s212021265.dylancoetzee.ContactsLog.domain.Contact;
import com.s212021265.dylancoetzee.ContactsLog.repository.DataSourceDAO;

import java.util.List;

/**
 * Created by Kurvin Hendricks on 18/08/2014.
 */
public class DataSourceDAOImpl implements DataSourceDAO {

    private SQLiteDatabase database;
    private DBAdapter dbHelper;
    private int cursorSize = 0;

    public DataSourceDAOImpl(Context context){
        dbHelper = new DBAdapter(context);
    }

    public void open(){
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    @Override
    public void createContact(Contact contact) {
        open();
            ContentValues values = new ContentValues();

            Log.i("onCreateContact: ","inserting into the database here");

            values.put(DBAdapter.COLUMN_CELL_NUMBER, contact.getCellNumber());
            values.put(DBAdapter.COLUMN_LNAME, contact.getlName());
            values.put(DBAdapter.COLUMN_FNAME, contact.getfName());
            values.put(DBAdapter.COLUMN_EMAILADDRESS, contact.getEmailAddress());
            values.put(DBAdapter.COLUMN_HOMEADDRESS, contact.getHomeAddress());

            database.insert(DBAdapter.TABLE_CONTACTS, null, values);

            Log.i("onCreateContact: ","inserting into the database here");

        close();
    }

    @Override
    public void updateContact(Contact contact) {

    }

    @Override
    public Contact findContactByID(int id) {
        open();
        Cursor cursor = database.query(DBAdapter.TABLE_CONTACTS, new String[]{DBAdapter.COLUMN_ID, DBAdapter.COLUMN_LNAME,
                DBAdapter.COLUMN_CELL_NUMBER, DBAdapter.COLUMN_FNAME, DBAdapter.COLUMN_EMAILADDRESS,
                DBAdapter.COLUMN_HOMEADDRESS} , DBAdapter.COLUMN_ID + " =? "
        , new String[] {String.valueOf(id)}
        , null, null, null, null);



        if(cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact.Builder(cursor.getString(2))
                .setId(cursor.getInt(0))
                .setlName(cursor.getString(1))
                .setfName(cursor.getString(3))
                .setEmailAddress(cursor.getString(4))
                .setHomeAddress(cursor.getString(5))
                .build();

        cursor.moveToLast();
        cursorSize = cursor.getCount();

        close();

        return contact;
    }

    @Override
    public void deleteContact(Contact contact) {

    }

    @Override
    public Contact getContact() {
        return null;
    }

    @Override
    public List<Contact> getContactList() {
        return null;
    }

    @Override
    public int getCursor() {

        String selectQuery = "SELECT * FROM " + DBAdapter.TABLE_CONTACTS;

        open();
        Cursor cursor = database.rawQuery(selectQuery, null);
        cursorSize = cursor.getCount();
        close();

        return cursorSize;
    }
}
