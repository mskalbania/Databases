package com.matuesz.shop;

public class UserExtendedInfo {

    private int isAdmin;
    private String address;
    private String phoneNumber;
    private String gender;

    public UserExtendedInfo(int isAdmin, String address, String phoneNumber, String gender) {
        this.isAdmin = isAdmin;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGender() {
        return gender;
    }
}
