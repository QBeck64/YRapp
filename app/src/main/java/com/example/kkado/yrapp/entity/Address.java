package com.example.kkado.yrapp.entity;
import com.example.kkado.yrapp.Enum.TypeAddress;

public class Address {

	private int idAddress;
	private TypeAddress type;
	private String nameAddress;
	private int numberAddress;
	private String complement;
	private String province;
	private String city;
	private String country;
	private int postalCode;
	private Person person;

	public Address() {

	}

	public Address(TypeAddress type,String nameAddress, int numberAddress,String complement,String province, String city, int postalCode, Person person) {
		this.type=type;
		this.nameAddress = nameAddress;
		this.numberAddress = numberAddress;
		this.complement = complement;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
		this.country = "Italia";
		this.person = person;
	}

	public Address(int idAddress,TypeAddress type, String nameAddress, int numberAddress,String complement,String province, String city, int postalCode, Person person) {
		this.type=type;
		this.idAddress = idAddress;
		this.nameAddress = nameAddress;
		this.numberAddress = numberAddress;
		this.complement = complement;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
		this.country = "Italia";
		this.person = person;
	}

	public Address(String nameAddress,TypeAddress type, int numberAddress,String complement,String province, String city, String country, int postalCode, Person person) {
		this.type=type;
		this.nameAddress = nameAddress;
		this.numberAddress = numberAddress;
		this.complement = complement;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
		this.country = country;
		this.person = person;
	}

	public Address(int idAddress,TypeAddress type, String nameAddress, int numberAddress,String complement,String province, String city, String country, int postalCode, Person person) {
		this.type=type;
		this.idAddress = idAddress;
		this.nameAddress = nameAddress;
		this.numberAddress = numberAddress;
		this.complement = complement;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
		this.country = country;
		this.person = person;
	}

	public int getIdAddress() {
		return this.idAddress;
	}
	public TypeAddress getTypes() {
		return this.type;
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
	public int getPostalCode() {
		return postalCode;
	}
	public String getCountry() {
		return country;
	}
	public Person getPerson() {
		return person;
	}

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
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setPerson(Person person) {
		this.person = person;
	}

}