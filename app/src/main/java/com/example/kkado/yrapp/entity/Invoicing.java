package com.example.kkado.yrapp.entity;

import com.example.kkado.yrapp.Enum.StatusCandidate;

public class Invoicing  {

	/**
	 * Members
	 */
	private int idInvoicing;
	private float invoicing;
	private Period period;
	private Person person;
	private int idPerson;
	private int idPeriod;

	/**
	 * Constructor
	 */
	public Invoicing() {

	}

	/**
	 * Gets
	 */
	public int getIdInvoicing() {
		return idInvoicing;
	}
	public float getInvoicing() {
		return invoicing;
	}
	public Period getPeriod() {
		return period;
	}
	public Person getPerson() {
		return this.person;
	}
	public int getIdPeriod() {
		return idPeriod;
	}
	public int getIdPerson() {
		return idPerson;
	}

	/**
	 * Sets
	 */
	public void setIdInvoicing(int idInvoicing) {
		this.idInvoicing = idInvoicing;
	}
	public void setPeriod(Period period) {
		this.period = period;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}
	public void setIdPeriod(int idPeriod) {
		this.idPeriod = idPeriod;
	}

	public void setInvoicing(float invoicing) {
		this.invoicing = invoicing;
	}
}
