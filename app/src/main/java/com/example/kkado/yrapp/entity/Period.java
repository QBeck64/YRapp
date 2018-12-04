package com.example.kkado.yrapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.kkado.yrapp.Enum.Status;

import java.util.Date;

public class Period implements Parcelable {
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
    //Parcelable implementation


    protected Period(Parcel in) {
        idPeriod = in.readInt();
        long tmpInitialDate = in.readLong();
        initialDate = tmpInitialDate != -1 ? new Date(tmpInitialDate) : null;
        long tmpFinalDate = in.readLong();
        finalDate = tmpFinalDate != -1 ? new Date(tmpFinalDate) : null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idPeriod);
        dest.writeLong(initialDate != null ? initialDate.getTime() : -1L);
        dest.writeLong(finalDate != null ? finalDate.getTime() : -1L);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Period> CREATOR = new Parcelable.Creator<Period>() {
        @Override
        public Period createFromParcel(Parcel in) {
            return new Period(in);
        }

        @Override
        public Period[] newArray(int size) {
            return new Period[size];
        }
    };
}