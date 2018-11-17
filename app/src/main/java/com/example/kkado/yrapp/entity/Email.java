package com.example.kkado.yrapp.entity;

import com.example.kkado.yrapp.Enum.TypeEmail;

public class Email {
	/**
	 * Members
	 */
	private int idEmail;
	private TypeEmail type;
	private String address;
	private Person person;
	private int idPerson;

	/**
	 * Constructor
	 */
	public Email() {
	}

	public Email(int idEmail, TypeEmail type, String address, Person person) {

		this.idEmail = idEmail;
		this.type = type;
		this.address = address;
		this.person = person;
	}

	/**
	 * Gets
	 */
	public int getIdEmail() {
		return this.idEmail;
	}
    public TypeEmail getType() {
        return type;
    }
	public String getAddress() {
		return this.address;
	}
	public Person getPerson() {
		return this.person;
	}
	public int getIdPerson() {
		return idPerson;
	}
	/**
	 * Sets
	 */
	public void setIdEmail(int idEmail) {
		this.idEmail = idEmail;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }



    public void setType(TypeEmail type) {
        this.type = type;
    }
}
