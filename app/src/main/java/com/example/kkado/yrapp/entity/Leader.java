package com.example.kkado.yrapp.entity;

import com.example.kkado.yrapp.Enum.Status;

public class Leader extends Person {
    private int idLeader;
    private Status status;

    public Leader() {

    }

    public int getIdLeader() {
        return idLeader;
    }
    public Status getStatus() {
        return status;
    }

    public void setIdLeader(int idLeader) {
        this.idLeader = idLeader;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
