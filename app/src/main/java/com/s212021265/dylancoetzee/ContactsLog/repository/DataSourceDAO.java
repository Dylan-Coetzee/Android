package com.s212021265.dylancoetzee.ContactsLog.repository;

import com.s212021265.dylancoetzee.ContactsLog.domain.Contact;

import java.util.List;

/**
 * Created by dylan
 */
public interface DataSourceDAO {

    public void createContact(Contact contact);
    public void updateContact(Contact contact);
    public Contact findContactByID(int id);
    public void deleteContact(Contact contact);
    public Contact getContact();
    public List<Contact> getContactList();
    public int getCursor();

}
