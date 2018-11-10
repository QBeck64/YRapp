package com.example.kkado.yrapp.entity;

import com.example.kkado.yrapp.Enum.Status;

class Campaign  {

	private int idCampaign ;
	private String name;
	private Status status;

	public Campaign() {

	}

	public Campaign(int idCampaign, String name, Status status) {
		this.idCampaign=idCampaign;
		this.name = name;
		this.status=status;
	}

	public int getIdCampaign() {
		return idCampaign;
	}
	public String getName() {
		return name;
	}
	public Status getStatus() {
		return status;
	}

	public void setIdCampaign(int idCampaign) {
		this.idCampaign = idCampaign;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}
