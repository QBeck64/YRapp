package com.example.kkado.yrapp.entity;

import com.example.kkado.yrapp.helper.Util;

import java.util.Date;

/**
 *
 */
public class Competition {

    /**
     * Members
     */
    private int idCompetition;
    private String description;
    private String goal;
    private Date initialDate;
    private Date finalDate;

    /**
     * Constructor
     */
    public Competition() {

    }

    /**
     * @param idCompetition
     * @param description
     * @param goal
     * @param initialDate
     * @param finalDate
     */
    public Competition(int idCompetition, String description, String goal, Date initialDate, Date finalDate) {
        this.idCompetition = idCompetition;
        this.description = description;
        this.goal = goal;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    /**
     * @param description
     * @param goal
     * @param initialDate
     * @param finalDate
     */
    public Competition(String description, String goal, Date initialDate, Date finalDate) {
        this.description = description;
        this.goal = goal;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    /**
     * Gets
     */
    public int getIdCompetition() {
        return idCompetition;
    }

    public String getDescription() {
        return description;
    }

    public String getGoal() {
        return goal;
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
    public void setIdCompetition(int idCompetition) {
        this.idCompetition = idCompetition;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return  description + " - " +
                goal + " (" +
                Util.ConvertDateToString(initialDate) +
                " - " + Util.ConvertDateToString(finalDate)+")";
    }


}
