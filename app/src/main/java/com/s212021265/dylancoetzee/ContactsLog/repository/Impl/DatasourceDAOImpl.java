package com.s212021265.dylancoetzee.ContactsLog.repository.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.s212021265.dylancoetzee.ContactsLog.domain.Contact;
import com.s212021265.dylancoetzee.ContactsLog.repository.DataSourceDAO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dylan on 30/08/2014.
 */
public class DatasourceDAOImpl implements DataSourceDAO {

    private SQLiteDatabase database;
    private DBAdapter dbHelper;

    public DatasourceDAOImpl(Context context) {
        dbHelper = new DBAdapter(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    @Override
    public void createContact(Contact contact) {
        try {
            open();
            ContentValues values = new ContentValues();
            values.put(DBAdapter.COLUMN_FIRST_NAME, contact.getFirstName());
            values.put(DBAdapter.COLUMN_LAST_NAME, contact.getLastName());
            values.put(DBAdapter.COLUMN_EMAIL, contact.getEmailAddress());
            values.put(DBAdapter.COLUMN_CELL, contact.getCellNumber());
            values.put(DBAdapter.COLUMN_ADDRESS, contact.getHomeAddress());

            database.insert(DBAdapter.TABLE_CONTACT, null, values);
            close();
        }
        catch(SQLException sql) {
            sql.printStackTrace();
        }


    }

    @Override
    public void updateContact(Contact contact) {
        try {
            open();
            ContentValues values = new ContentValues();
            values.put(DBAdapter.COLUMN_FIRST_NAME, contact.getFirstName());
            values.put(DBAdapter.COLUMN_LAST_NAME, contact.getLastName());
            values.put(DBAdapter.COLUMN_EMAIL, contact.getEmailAddress());
            values.put(DBAdapter.COLUMN_CELL, contact.getCellNumber());
            values.put(DBAdapter.COLUMN_ADDRESS, contact.getHomeAddress());
            database.update(DBAdapter.TABLE_CONTACT, values, DBAdapter.COLUMN_ID + " = ?", new String[] {String.valueOf(contact.getId())});
            close();
        }
        catch(SQLException sql) {
            sql.printStackTrace();
        }
    }

    @Override
    public Contact findUserById(int id) {
        Contact contact = null;
        try {
            open();
            Cursor cursor = database.query(DBAdapter.TABLE_CONTACT,
                    new String[]{DBAdapter.COLUMN_ID,
                            DBAdapter.COLUMN_FIRST_NAME,
                            DBAdapter.COLUMN_LAST_NAME,
                            DBAdapter.COLUMN_EMAIL,
                            DBAdapter.COLUMN_CELL,
                            DBAdapter.COLUMN_ADDRESS},
                    DBAdapter.COLUMN_ID + " = ?",
                    new String[] {String.valueOf(id)},
                    null, null, null, null);

            if(cursor != null ) {
                cursor.moveToFirst();
            }
            contact = new Contact.Builder(cursor.getString(1))
                    .lastName(cursor.getString(2))
                    .emailAddress(cursor.getString(3))
                    .cellNumber(cursor.getString(4))
                    .homeAddress(cursor.getString(5))
                    .id(cursor.getInt(0))
                    .build();
            close();
        }
        catch(SQLException sql) {
            sql.printStackTrace();
        }
        return contact;
    }

    @Override
    public void deleteContact(Contact contact) {
        try {
            open();
            database.delete(DBAdapter.TABLE_CONTACT, DBAdapter.COLUMN_ID + " = ?", new String[] {String.valueOf(contact.getId())});
            close();
        }
        catch(SQLException sql) {
            sql.printStackTrace();
        }
    }

    @Override
    public Contact getContact() {
        String selectQuery = "SELECT * FROM " + DBAdapter.TABLE_CONTACT;
        Contact contact = null;
        try {
            open();
            Cursor cursor = database.rawQuery(selectQuery, null);
            if(cursor.moveToFirst()) {
                do {
                    contact = new Contact.Builder(cursor.getString(1))
                            .lastName(cursor.getString(2))
                            .emailAddress(cursor.getString(3))
                            .cellNumber(cursor.getString(4))
                            .homeAddress(cursor.getString(5))
                            .id(cursor.getInt(0))
                            .build();

                }while(cursor.moveToNext());
            }
            close();
        }
        catch(SQLException sql) {
            sql.printStackTrace();
        }
        return contact;
    }

    @Override
    public ArrayList<Contact> findAll() {
        String selectQuery = "SELECT * FROM " + DBAdapter.TABLE_CONTACT;
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        Contact contact;
        try {
            open();
            Cursor cursor = database.rawQuery(selectQuery, null);
            if(cursor.moveToFirst()) {
                do {
                    contact = new Contact.Builder(cursor.getString(1))
                            .lastName(cursor.getString(2))
                            .emailAddress(cursor.getString(3))
                            .cellNumber(cursor.getString(4))
                            .homeAddress(cursor.getString(5))
                            .id(cursor.getInt(0))
                            .build();
                    contacts.add(contact);
                    contact = null;

                }while(cursor.moveToNext());
            }
            close();
        }
        catch(SQLException sql) {
            sql.printStackTrace();
        }
        return contacts;
    }
}