package com.example.kkado.yrapp.entity;

import com.example.kkado.yrapp.Enum.Gender;

import java.util.Date;

public class Person {
    /**
     * Members
     */
    private int idPerson;
    private String name;
    private String surname;
    private Date birthday;
    private Gender gender;

    /**
     * Constructor
     */
    public Person() {

    }

    public Person(int idPerson, String name, String surname, Date birthday, Gender gender) {
        this.idPerson = idPerson;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.gender = gender;
    }

    public Person(String name, String surname, Date birthday, Gender gender) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.gender = gender;
    }

    /**
     * Gets
     */
    public int getIdPerson() {
        return this.idPerson;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public Gender getGender() {
        return this.gender;
    }

    /**
     * Sets
     */
    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return  idPerson +
                " - '" + surname  +
                ", " + name  ;
    }
}
