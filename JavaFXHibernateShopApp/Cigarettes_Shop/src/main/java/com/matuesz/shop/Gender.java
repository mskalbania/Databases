package com.matuesz.shop;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "genders")
public class Gender implements Serializable{

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
