package com.example.kkado.yrapp.entity;

/**
 *
 */
public class Invoicing {

    /**
     * Members
     */
    private int idInvoicing;
    private float invoicing;
    private Period period;
    private Person person;

    public Invoicing(float invoicing, int idPerson, int idPeriod) {
        this.invoicing = invoicing;
        this.idPerson = idPerson;
        this.idPeriod = idPeriod;
    }

    private int idPerson;
    private int idPeriod;

    /**
     * Constructor
     */
    public Invoicing() {

    }

    /**
     * @param idInvoicing
     * @param invoicing
     * @param idPerson
     * @param idPeriod
     */
    public Invoicing(int idInvoicing, float invoicing, int idPerson, int idPeriod) {
        this.idInvoicing = idInvoicing;
        this.invoicing = invoicing;
        this.period = period;
        this.person = person;
        this.idPerson = idPerson;
        this.idPeriod = idPeriod;
    }

    /**
     * Gets and Sets
     */
    public int getIdInvoicing() {
        return idInvoicing;
    }

    public void setIdInvoicing(int idInvoicing) {
        this.idInvoicing = idInvoicing;
    }

    public float getInvoicing() {
        return invoicing;
    }

    public void setInvoicing(float invoicing) {
        this.invoicing = invoicing;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public int getIdPeriod() {
        return idPeriod;
    }

    public void setIdPeriod(int idPeriod) {
        this.idPeriod = idPeriod;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "Invoicing{" +
                "invoicing=" + invoicing +
                ", period=" + period +
                ", person=" + person +
                '}';
    }
}
