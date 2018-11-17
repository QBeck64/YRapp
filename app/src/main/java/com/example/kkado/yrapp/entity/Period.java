package com.example.kkado.yrapp.entity;

import com.example.kkado.yrapp.Enum.Status;

import java.util.Date;

public class Period {
    /**
     * Members
     */
    private int idPeriod;
    private Date initialDate;
    private Date finalDate;

    /**
     * Constructor
     */
    public Period() {

    }

    public Period(int idPeriod, Date initialDate, Date finalDate) {
        this.idPeriod = idPeriod;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    /**
     * Get
     */
    public int getIdPeriod() {
        return idPeriod;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    /**
     * Set
     */
    public void setIdPeriod(int idCampaign) {
        this.idPeriod = idPeriod;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

}
