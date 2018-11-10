package com.example.kkado.yrapp.entity;

import com.example.kkado.yrapp.Enum.Status;

public class SalesRepresentative extends Person {
    private int idSalesRepresentative;
    private Status status;

    public SalesRepresentative() {

    }

    public int getIdSalesRepresentative() {
        return idSalesRepresentative;
    }
    public Status getStatus() {
        return status;
    }

    public void setIdSalesRepresentative(int idSalesRepresentative) {
        this.idSalesRepresentative = idSalesRepresentative;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
