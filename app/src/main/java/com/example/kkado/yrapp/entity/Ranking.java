package com.example.kkado.yrapp.entity;

public class Ranking {

	private int idRanking;
	private SalesRepresentative salesRepresentative;
	private Target target;

    public Ranking() {

    }

	public int getIdRanking() {
		return idRanking;
	}
    public SalesRepresentative getSalesRepresentative() {
        return salesRepresentative;
    }
    public Target getTarget() {
        return target;
    }

    public void setIdRanking(int idRanking) {
        this.idRanking = idRanking;
    }
    public void setSalesRepresentative(SalesRepresentative salesRepresentative) {
        this.salesRepresentative = salesRepresentative;
    }
    public void setTarget(Target target) {
        this.target = target;
    }
}
