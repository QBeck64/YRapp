package com.example.kkado.yrapp.entity;

import com.example.kkado.yrapp.Enum.Status;

import java.util.Date;

public class GroupLeader {
    /**
     * Members
     */
    private int idGroupLeader;
    private String name;
    private int idLeader;
    private Date initialDate;
    private Date finalDate;

    /**
     * Constructor
     */
    public GroupLeader() {

    }

    /**
     * Gets
     */
    public int getIdGroupLeader() {
        return idGroupLeader;
    }
    public String getName() {
        return name;
    }

    public int getIdLeader() {
        return idLeader;
    }
    public Date getInitialDate() {
        return initialDate;
    }
    public Date getFinalDate() {
        return finalDate;
    }

    /**
     * Sets
     */
    public void setIdGroupLeader(int idGroupLeader) {
        this.idGroupLeader = idGroupLeader;
    }
    public void setIdLeader(int idLeader) {
        this.idLeader = idLeader;
    }
    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }
    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }


    public void setName(String name) {
        this.name = name;
    }
}
