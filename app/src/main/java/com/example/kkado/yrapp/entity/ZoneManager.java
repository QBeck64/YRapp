package com.example.kkado.yrapp.entity;

import com.example.kkado.yrapp.Enum.Status;

public class ZoneManager extends Person {
    private int idZoneManager;
    private Status status;

    public ZoneManager() {

    }

    public int getIdZoneManager() {
        return idZoneManager;
    }
    public Status getStatus() {
        return status;
    }

    public void setIdZoneManager(int idZoneManager) {
        this.idZoneManager = idZoneManager;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
