package com.example.kkado.yrapp.entity;

import  java.util.Date;

public class TeamGroupLeader {

	/**
	 * Members
	 */
	private int idTeamGroupLeader;
	private Person person;
	private GroupLeader groupLeader;
	private Date initialDate;
	private Date finalDate;
	private int idPerson;

	/**
	 * Constructor
	 */
	public TeamGroupLeader() {

	}

	/**
	 * Gets
	 */
	public int getIdTeamGroupLeader() {
		return idTeamGroupLeader;
	}
	public Person getLeader() {
		return person;
	}
	public GroupLeader getGroupLeader() {
		return groupLeader;
	}
	public Date getInitialDate() {
		return initialDate;
	}
	public Date getFinalDate() {
		return finalDate;
	}
	public int getIdPerson() {
		return idPerson;
	}
	public Person getPerson() {
		return person;
	}
	/**
	 * Sets
	 */
	public void setIdTeamGroupLeader(int idTeamGroupLeader) {
		this.idTeamGroupLeader = idTeamGroupLeader;
	}
	public void setLeader(Person person) {
		this.person = person;
	}
	public void setGroupLeader(GroupLeader groupLeader) {
		this.groupLeader = groupLeader;
	}
	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}
	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}
	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
}
