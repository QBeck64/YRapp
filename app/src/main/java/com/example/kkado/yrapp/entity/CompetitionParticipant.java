package com.example.kkado.yrapp.entity;

import java.util.Date;

public class CompetitionParticipant {
    /**
     * Members
     */
    private int idCompetitionParticipant;
    private int idParticipant;
    private int idCompetition;
    private Date initialDate;
    private Date finalDate;
    private boolean prizeGiven;
    private Person person;
    private Competition competition;

    /**
     * Constructor
     */
    public CompetitionParticipant() {

    }

    public CompetitionParticipant(int idCompetitionParticipant, int idParticipant, int idCompetition, Date initialDate, Date finalDate, boolean prizeGiven) {
        this.idCompetitionParticipant = idCompetitionParticipant;
        this.idParticipant = idParticipant;
        this.idCompetition = idCompetition;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.prizeGiven = prizeGiven;
        this.competition = competition;
    }

    /**
     * Gets and Sets
     */
    public int getIdCompetitionParticipant() {
        return idCompetitionParticipant;
    }

    public void setIdCompetitionParticipant(int idCompetitionParticipant) {
        this.idCompetitionParticipant = idCompetitionParticipant;
    }

    public int getIdParticipant() {
        return idParticipant;
    }

    public void setIdParticipant(int idParticipant) {
        this.idParticipant = idParticipant;
    }

    public int getIdCompetition() {
        return idCompetition;
    }

    public void setIdCompetition(int idCompetition) {
        this.idCompetition = idCompetition;
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

    public boolean isPrizeGiven() {
        return prizeGiven;
    }

    public void setPrizeGiven(boolean prizeGiven) {
        this.prizeGiven = prizeGiven;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
}
