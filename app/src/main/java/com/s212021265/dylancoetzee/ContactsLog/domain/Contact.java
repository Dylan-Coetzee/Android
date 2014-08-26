package com.s212021265.dylancoetzee.ContactsLog.domain;

/**
 * Created by dylan.
 */
public class Contact {

    private int id;
    private String fName;
    private String lName;
    private String emailAddress;
    private String cellNumber;
    private String homeAddress;

    public Contact(){};

    public Contact(Builder builder) {
        this.id = builder.id;
        this.fName = builder.fName;
        this.lName = builder.lName;
        this.emailAddress = builder.emailAddress;
        this.cellNumber = builder.cellNumber;
        this.homeAddress = builder.homeAddress;
    }

    public int getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
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

    public static class Builder {
        public int id;
        public String fName;
        public String lName;
        public String emailAddress;
        public String cellNumber;
        public String homeAddress;

        public Builder(String cellPhone){
            cellNumber = cellPhone;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setfName(String fName) {
            this.fName = fName;
            return this;
        }

        public Builder setlName(String lName) {
            this.lName = lName;
            return this;
        }

        public Builder setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder setCellNumber(String cellNumber) {
            this.cellNumber = cellNumber;
            return this;
        }

        public Builder setHomeAddress(String homeAddress) {
            this.homeAddress = homeAddress;
            return this;
        }

        public Builder Contact(Contact contact){
            this.setId(contact.getId());
            this.setCellNumber(contact.getCellNumber());
            this.setEmailAddress(contact.getEmailAddress());
            this.setfName(contact.getfName());
            this.setlName(contact.getlName());
            this.setHomeAddress(contact.getHomeAddress());
            return this;
        }

        public Contact build(){
            return new Contact(this);
        }
    }


}
