package com.matuesz.shop.user;

import javax.persistence.*;

@Entity
@Table(name = "genders")
public class Gender{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String gender;

    public Gender(){
    }

    public Gender(String name){
        gender = name;
    }

    public String getName(){
        return gender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }
}
