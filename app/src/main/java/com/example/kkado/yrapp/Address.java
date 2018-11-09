package com.example.kkado.yrapp;

public class Address {
    private int id;
    private String street;
    private int houseNumber;
    private String city;
    private String province;
    private String country;
    private String zipCode;

    public Address(String street, int houseNumber, String city, String province, String zipCode) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.province = province;
        this.zipCode = zipCode;
        this.country = "Italia";
    }

    public Address(String street, int houseNumber, String city, String province, String country, String zipCode) {

        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.province = province;
        this.country = country;
        this.zipCode = zipCode;
    }

    public Address() {

    }

    public Address(int id, String street, int houseNumber, String city, String province, String country, String zipCode) {
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.province = province;
        this.country = country;
        this.zipCode = zipCode;
    }

    public Address(int id, String street, int houseNumber, String city, String province, String zipCode) {
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.province = province;
        this.zipCode = zipCode;
        this.country = "Italia";
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getId() {
        return id;
    }
}
