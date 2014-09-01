package com.s212021265.dylancoetzee.ContactsLog.repository;

import com.s212021265.dylancoetzee.ContactsLog.domain.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dylan 21/08/2014.
 */
public interface DataSourceDAO {

    public void createContact(Contact contact);
    public void updateContact(Contact contact);
    public Contact findUserById(int id);
    public void deleteContact(Contact contact);
    public Contact getContact();
    public ArrayList<Contact> findAll();
}


