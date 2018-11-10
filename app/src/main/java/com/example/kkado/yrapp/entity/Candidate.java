package com.example.kkado.yrapp.entity;

import com.example.kkado.yrapp.Enum.StatusCandidate;

public class Candidate extends Person {

	private int idCandidate;
	private StatusCandidate statusCandidate;

	public Candidate() {

	}

	public int getIdCandidate() {
		return idCandidate;
	}
	public StatusCandidate getStatusCandidate() {
		return statusCandidate;
	}

	public void setIdCandidate(int idCandidate) {
		this.idCandidate = idCandidate;
	}
	public void setStatusCandidate(StatusCandidate statusCandidate) {
		this.statusCandidate = statusCandidate;
	}
}
