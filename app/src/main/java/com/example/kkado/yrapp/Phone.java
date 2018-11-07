package com.example.kkado.yrapp;

public class Phone {
    private int id;
    private int type;
    private String number;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Phone() {

    }

    public Phone(int id, int type, String number) {

        this.id = id;
        this.type = type;
        this.number = number;
    }
}
