package com.example.adminapp.Model;

import java.io.Serializable;

public class Customers implements Serializable {

    //Model or POJo
    public int id;
    public String docId;
    public String name;
    public String phone;
    public String email;
    public int password;
    public int address;

    public Customers() {
    }

    public Customers(int id, String docId, String name, String phone, String email) {
        this.id = id;
        this.docId = docId;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        String message = "Name :" +name+ "\nPhone :"+phone+"\nEmail :"+email;
        return message;
    }
}
