package com.example.kkado.yrapp.entity;

import  java.util.Date;

public class Target {

	private int idTarget;
	private String description;
	private int goal;
	private Date  initialDate;
	private Date finalDate;

	public Target() {

	}

	public int getIdTarget() {
		return idTarget;
	}
	public String getDescription() {
		return description;
	}
	public int getGoal() {
		return goal;
	}
	public Date getInitialDate() {
		return initialDate;
	}
	public Date getFinalDate() {
		return finalDate;
	}

	public void setIdTarget(int idTarget) {
		this.idTarget = idTarget;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setGoal(int goal) {
		this.goal = goal;
	}
	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}
	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}


}
