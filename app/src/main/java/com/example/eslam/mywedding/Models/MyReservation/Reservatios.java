
package com.example.eslam.mywedding.Models.MyReservation;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Reservatios implements Parcelable {

    @SerializedName("reservations")
    @Expose
    private List<MyReservation> reservations = null;
    public final static Creator<Reservatios> CREATOR = new Creator<Reservatios>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Reservatios createFromParcel(Parcel in) {
            return new Reservatios(in);
        }

        public Reservatios[] newArray(int size) {
            return (new Reservatios[size]);
        }

    };

    protected Reservatios(Parcel in) {
        in.readList(this.reservations, (MyReservation.class.getClassLoader()));
    }

    public Reservatios() {
    }

    public List<MyReservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<MyReservation> reservations) {
        this.reservations = reservations;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(reservations);
    }

    public int describeContents() {
        return 0;
    }

}
