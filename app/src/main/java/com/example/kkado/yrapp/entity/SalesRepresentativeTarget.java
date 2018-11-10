package com.example.kkado.yrapp.entity;

public class SalesRepresentativeTarget {

    private int idSalesRepresentativeTarget;
    private SalesRepresentative salesRepresentative;

    public SalesRepresentativeTarget() {

    }

    public int getIdSalesRepresentativeTarget() {
        return idSalesRepresentativeTarget;
    }
    public SalesRepresentative getSalesRepresentative() {
        return salesRepresentative;
    }

    public void setIdSalesRepresentativeTarget(int idSalesRepresentativeTarget) {
        this.idSalesRepresentativeTarget = idSalesRepresentativeTarget;
    }
    public void setSalesRepresentative(SalesRepresentative salesRepresentative) {
        this.salesRepresentative = salesRepresentative;
    }
}
