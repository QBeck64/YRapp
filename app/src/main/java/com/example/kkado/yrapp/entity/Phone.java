package com.example.kkado.yrapp.entity;

import com.example.kkado.yrapp.Enum.TypePhone;

public class Phone {

	private int idPhone;
	private TypePhone type;
	private String phoneNumber;
	private Person person;

	public Phone() {

	}

	public int getIdPhone() {
		return idPhone;
	}
	public TypePhone getType() {
		return type;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public Person getPerson() {
		return this.person;
	}

	public void setIdPhone(int idPhone) {
		this.idPhone = idPhone;
	}
	public void setType(TypePhone type) {
		this.type = type;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
}
