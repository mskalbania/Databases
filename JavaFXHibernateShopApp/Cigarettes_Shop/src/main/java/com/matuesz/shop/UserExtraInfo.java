package com.matuesz.shop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users_extra_info")
public class UserExtraInfo {

    @Id
    @Column(name = "user_id")
    private int id;
    private int isAdmin;
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;

    private String gender;

    public UserExtraInfo() {
    }

    public UserExtraInfo(int isAdmin, String address, String phoneNumber, String gender) {
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
