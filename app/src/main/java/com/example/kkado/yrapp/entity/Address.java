package com.example.kkado.yrapp.entity;

import com.example.kkado.yrapp.Enum.TypeAddress;

public class Address {
    /**
     * Members
     */
    private int idAddress;
    private TypeAddress type;
    private String nameAddress;
    private int numberAddress;
    private String complement;
    private String province;
    private String city;
    private String country;
    private String postalCode;
    private int idPerson;

    /**
     * Constructor
     */
    public Address() {

    }

    /**
     * @param type
     * @param nameAddress
     * @param numberAddress
     * @param complement
     * @param province
     * @param city
     * @param postalCode
     * @param idPerson
     */
    public Address(TypeAddress type, String nameAddress, int numberAddress, String complement, String province, String city, String postalCode, int idPerson) {
        this.type = type;
        this.nameAddress = nameAddress;
        this.numberAddress = numberAddress;
        this.complement = complement;
        this.province = province;
        this.city = city;
        this.country = "Italia";
        this.postalCode = postalCode;
        this.idPerson = idPerson;
    }

    /**
     * @param type
     * @param nameAddress
     * @param numberAddress
     * @param complement
     * @param province
     * @param city
     * @param postalCode
     */
    public Address(TypeAddress type, String nameAddress, int numberAddress, String complement, String province, String city, String postalCode) {
        this.type = type;
        this.nameAddress = nameAddress;
        this.numberAddress = numberAddress;
        this.complement = complement;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.country = "Italia";
    }

    /**
     * @param idAddress
     * @param type
     * @param nameAddress
     * @param numberAddress
     * @param complement
     * @param province
     * @param city
     * @param postalCode
     */
    public Address(int idAddress, TypeAddress type, String nameAddress, int numberAddress, String complement, String province, String city, String postalCode) {
        this.type = type;
        this.idAddress = idAddress;
        this.nameAddress = nameAddress;
        this.numberAddress = numberAddress;
        this.complement = complement;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.country = "Italia";
    }

    /**
     * @param nameAddress
     * @param type
     * @param numberAddress
     * @param complement
     * @param province
     * @param city
     * @param country
     * @param postalCode
     */
    public Address(String nameAddress, TypeAddress type, int numberAddress, String complement, String province, String city, String country, String postalCode) {
        this.type = type;
        this.nameAddress = nameAddress;
        this.numberAddress = numberAddress;
        this.complement = complement;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
    }

    /**
     * @param idAddress
     * @param type
     * @param nameAddress
     * @param numberAddress
     * @param complement
     * @param province
     * @param city
     * @param country
     * @param postalCode
     */
    public Address(int idAddress, TypeAddress type, String nameAddress, int numberAddress, String complement, String province, String city, String country, String postalCode) {
        this.type = type;
        this.idAddress = idAddress;
        this.nameAddress = nameAddress;
        this.numberAddress = numberAddress;
        this.complement = complement;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
    }

    /**
     * @param idAddress
     * @param type
     * @param nameAddress
     * @param numberAddress
     * @param complement
     * @param province
     * @param city
     * @param country
     * @param postalCode
     * @param idPerson
     */
    public Address(int idAddress, TypeAddress type, String nameAddress, int numberAddress, String complement, String province, String city, String country, String postalCode, int idPerson) {
        this.idAddress = idAddress;
        this.type = type;
        this.nameAddress = nameAddress;
        this.numberAddress = numberAddress;
        this.complement = complement;
        this.province = province;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.idPerson = idPerson;
    }


    /**
     * Gets
     */
    public int getIdAddress() {
        return this.idAddress;
    }

    public TypeAddress getType() {
        return type;
    }

    public String getNameAddress() {
        return nameAddress;
    }

    public int getNumberAddress() {
        return numberAddress;
    }

    public String getComplement() {
        return complement;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public int getIdPerson() {
        return idPerson;
    }

    /**
     * Sets
     */
    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public void setType(TypeAddress type) {
        this.type = type;
    }

    public void setNameAddress(String nameAddress) {
        this.nameAddress = nameAddress;
    }

    public void setNumberAddress(int numberAddress) {
        this.numberAddress = numberAddress;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }


    /**
     * @return
     */
    @Override
    public String toString() {
        return "Address{" +
                "idAddress=" + idAddress +
                ", type=" + type +
                ", nameAddress='" + nameAddress + '\'' +
                ", numberAddress=" + numberAddress +
                ", complement='" + complement + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", idPerson=" + idPerson +
                '}';
    }
}
