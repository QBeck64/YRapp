package com.example.kkado.yrapp.entity;

import com.example.kkado.yrapp.Enum.Status;

import java.util.Date;

/**
 *
 */
public class GroupLeader {
    /**
     * Members
     */
    private int idGroupLeader;
    private String groupName;
    private int idPersonLeader;
    private Date initialDate;
    private Date finalDate;
    private Person PersonLeader;

    /**
     * Constructor
     */
    public GroupLeader() {

    }

    public GroupLeader(int idGroupLeader, String groupName, int idPersonLeader, Date initialDate, Date finalDate) {
        this.idGroupLeader = idGroupLeader;
        this.groupName = groupName;
        this.idPersonLeader = idPersonLeader;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    /**
     * Gets and Sets
     */
    public int getIdGroupLeader() {
        return idGroupLeader;
    }

    public void setIdGroupLeader(int idGroupLeader) {
        this.idGroupLeader = idGroupLeader;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getIdPersonLeader() {
        return idPersonLeader;
    }

    public void setIdPersonLeader(int idPersonLeader) {
        this.idPersonLeader = idPersonLeader;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public Person getPersonLeader() {
        return PersonLeader;
    }

    public void setPersonLeader(Person personLeader) {
        PersonLeader = personLeader;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "GroupLeader{" +
                "groupName='" + groupName + '\'' +
                ", initialDate=" + initialDate +
                ", finalDate=" + finalDate +
                ", PersonLeader=" + PersonLeader +
                '}';
    }
}
