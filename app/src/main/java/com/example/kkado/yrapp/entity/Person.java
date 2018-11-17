package com.example.kkado.yrapp.entity;

import com.example.kkado.yrapp.Enum.Gender;
import com.example.kkado.yrapp.Enum.TypePerson;

import java.util.Date;

/**
 *
 */
public class Person {
    /**
     * Members
     */
    private int idPerson;
    private String name;
    private String surname;
    private Date birthday;
    private Gender gender;
    private int level;
    private String email;
    private String phoneNumber;
    private Integer idPersonParent;
    private Person personParent;
    private TypePerson type;

    /**
     * Constructor
     */
    public Person() {

    }

    public Person(int idPerson, String name, String surname, Date birthday, Gender gender, int level, String email, String phoneNumber, Integer idPersonParent, TypePerson type) {
        this.idPerson = idPerson;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.gender = gender;
        this.level = level;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.idPersonParent = idPersonParent;
        this.type = type;
    }

    /**
     * Gets and Sets
     */
    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getIdPersonParent() {
        return idPersonParent;
    }

    public void setIdPersonParent(Integer idPersonParent) {
        this.idPersonParent = idPersonParent;
    }

    public Person getPersonParent() {
        return personParent;
    }

    public void setPersonParent(Person personParent) {
        this.personParent = personParent;
    }

    public TypePerson getType() {
        return type;
    }

    public void setType(TypePerson type) {
        this.type = type;
    }

    /**
     * toString represents object Person
     */
    @Override
    public String toString() {
        return idPerson +
                " - '" + surname +
                ", " + name+
                " (" + email+
                ")"
                ;
    }
}
