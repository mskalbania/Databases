package com.matuesz.shop.user;

import javax.persistence.*;

@Entity
@Table(name = "users_extra_info")
public class UserExtraInfo {

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Id
    @Column(name = "user_id")
    private int id;
    private int isAdmin;
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;

    public UserExtraInfo() {
    }

    public UserExtraInfo(int isAdmin, String address, String phoneNumber, Gender gender) {
        this.isAdmin = isAdmin;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public void setId(int id) {
        this.id = id;
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
        return gender.getName();
    }
}
