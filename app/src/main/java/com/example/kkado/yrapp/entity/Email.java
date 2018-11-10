package com.example.kkado.yrapp.entity;

import com.example.kkado.yrapp.Enum.TypeEmail;

public class Email {

	private int idEmail;
	private TypeEmail type;
	private String address;
	private Person person;

	public Email() {
	}

	public Email(int idEmail, TypeEmail type, String address, Person person) {

		this.idEmail = idEmail;
		this.type = type;
		this.address = address;
		this.person = person;
	}

	public int getIdEmail() {
		return this.idEmail;
	}
	public TypeEmail getTypeEmail() {
		return this.type;
	}
	public String getAddress() {
		return this.address;
	}
	public Person getPerson() {
		return this.person;
	}

	public void setIdEmail(int idEmail) {
		this.idEmail = idEmail;
	}
	public void setTypeEmail(TypeEmail type) {
		this.type = type;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPerson(Person person) {
		this.person = person;
	}

}
