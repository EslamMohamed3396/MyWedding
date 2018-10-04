package com.example.eslam.mywedding.Models.MyReservation;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReservatioCancel implements Parcelable {

    @SerializedName("status")
    @Expose
    private String status;
    public final static Parcelable.Creator<ReservatioCancel> CREATOR = new Creator<ReservatioCancel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ReservatioCancel createFromParcel(Parcel in) {
            return new ReservatioCancel(in);
        }

        public ReservatioCancel[] newArray(int size) {
            return (new ReservatioCancel[size]);
        }

    };

    protected ReservatioCancel(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ReservatioCancel() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
    }

    public int describeContents() {
        return 0;
    }

}