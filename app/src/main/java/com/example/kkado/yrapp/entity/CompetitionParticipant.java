package com.example.kkado.yrapp.entity;

import java.util.Date;

public class CompetitionParticipant {
    /**
     * Members
     */
    private int idCompetitionParticipant;
    private int idParticipante;
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

    /**
     * Gets
     */
    public int getIdCompetitionParticipant() {
        return idCompetitionParticipant;
    }
    public int getIdParticipante() {
        return idParticipante;
    }
    public int getIdCompetition() {
        return idCompetition;
    }
    public Date getInitialDate() {
        return initialDate;
    }
    public Date getFinalDate() {
        return finalDate;
    }
    public boolean getprizeGiven() {
        return prizeGiven;
    }
    public Person getPerson() {
        return person;
    }
    public Competition getCompetition() {
        return competition;
    }

    /**
     * Sets
     */
    public void setIdCompetitionParticipant(int idCompetitionParticipant) {
        this.idCompetitionParticipant = idCompetitionParticipant;
    }
    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }
    public void setIdCompetition(int idCompetition) {
        this.idCompetition = idCompetition;
    }
    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }
    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }
    public void setPrizeGiven(boolean prizeGiven) {
        this.prizeGiven = prizeGiven;
    }
    public void setPerson(Person person) {
        this.person = person;
    }
    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public boolean isPrizeGiven() {
        return prizeGiven;
    }
}
