package com.example.kkado.yrapp.entity;

import java.util.Date;

/**
 *
 */
public class TeamGroupLeader {

    /**
     * Members
     */
    private int idTeamGroupLeader;
    private Person person;
    private GroupLeader groupLeader;
    private Date initialDate;
    private Date finalDate;
    private int idPerson;
    private int idGroupLeader;

    /**
     * Constructor
     */
    public TeamGroupLeader() {

    }

    /**
     * @param idTeamGroupLeader
     * @param person
     * @param groupLeader
     * @param initialDate
     * @param finalDate
     * @param idPerson
     */
    public TeamGroupLeader(int idTeamGroupLeader, Person person, GroupLeader groupLeader, Date initialDate, Date finalDate, int idPerson) {
        this.idTeamGroupLeader = idTeamGroupLeader;
        this.person = person;
        this.groupLeader = groupLeader;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.idPerson = idPerson;
    }

    /**
     * @param idTeamGroupLeader
     * @param initialDate
     * @param finalDate
     * @param idPerson
     */
    public TeamGroupLeader(int idTeamGroupLeader, Date initialDate, Date finalDate, int idPerson) {
        this.idTeamGroupLeader = idTeamGroupLeader;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.idPerson = idPerson;
    }

    /**
     * Gets
     */
    public int getIdTeamGroupLeader() {
        return idTeamGroupLeader;
    }

    public void setIdTeamGroupLeader(int idTeamGroupLeader) {
        this.idTeamGroupLeader = idTeamGroupLeader;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public GroupLeader getGroupLeader() {
        return groupLeader;
    }

    public void setGroupLeader(GroupLeader groupLeader) {
        this.groupLeader = groupLeader;
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

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    @Override
    public String toString() {
        return "TeamGroupLeader{" +
                "person=" + person +
                ", groupLeader=" + groupLeader +
                ", initialDate=" + initialDate +
                ", finalDate=" + finalDate +
                '}';
    }
}
