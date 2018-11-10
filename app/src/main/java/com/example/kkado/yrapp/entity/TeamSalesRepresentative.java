package com.example.kkado.yrapp.entity;

import  java.util.Date;

public class TeamSalesRepresentative {

	private int idTeam;
	private Leader leader;
	private SalesRepresentative salesRepresentative;
	private Date initialDate;
	private Date finalDate;

	public TeamSalesRepresentative() {

	}

	public int getIdTeam() {
		return idTeam;
	}
	public Leader getLeader() {
		return leader;
	}
	public SalesRepresentative getSalesRepresentative() {
		return salesRepresentative;
	}
	public Date getInitialDate() {
		return initialDate;
	}
	public Date getFinalDate() {
		return finalDate;
	}

	public void setIdTeam(int idTeam) {
		this.idTeam = idTeam;
	}
	public void setLeader(Leader leader) {
		this.leader = leader;
	}
	public void setSalesRepresentative(SalesRepresentative salesRepresentative) {
		this.salesRepresentative = salesRepresentative;
	}
	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}
	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}


}
