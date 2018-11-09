package com.example.kkado.yrapp;

public class Email {
    private int id;
    private int type;
    private String address;

    public Email() {
    }

    public Email(int id, int type, String address) {

        this.id = id;
        this.type = type;
        this.address = address;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
