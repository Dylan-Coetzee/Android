package com.s212021265.dylancoetzee.ContactsLog.domain;

/**
 * Created by Dylan 21/08/2014.
 */
public class Contact {
    private int id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String cellNumber;
    private String homeAddress;

    private Contact() {

    }

    private Contact(Builder builder) {
        id = builder.id;
        firstName = builder.firstName;
        lastName = builder.lastName;
        emailAddress = builder.emailAddress;
        cellNumber = builder.cellNumber;
        homeAddress = builder.homeAddress;
    }

    public static class Builder {
        private int id;
        private String firstName;
        private String lastName;
        private String emailAddress;
        private String cellNumber;
        private String homeAddress;

        public Builder(String firstName) {
            this.firstName = firstName;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder emailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder cellNumber(String cellNumber) {
            this.cellNumber = cellNumber;
            return this;
        }

        public Builder homeAddress(String homeAddress) {
            this.homeAddress = homeAddress;
            return this;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder contact(Contact contact) {
            id = contact.getId();
            firstName = contact.getFirstName();
            lastName = contact.getLastName();
            emailAddress = contact.getEmailAddress();
            cellNumber = contact.getCellNumber();
            homeAddress = contact.getHomeAddress();
            return this;
        }

        public Contact build() {
            return new Contact(this);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public String getHomeAddress() {
        return homeAddress;
    }
}