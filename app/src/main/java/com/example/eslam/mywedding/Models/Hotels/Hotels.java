
package com.example.eslam.mywedding.Models.Hotels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hotels implements Parcelable {

    @SerializedName("hotels")
    @Expose
    private List<Hotel> hotels = null;
    public final static Creator<Hotels> CREATOR = new Creator<Hotels>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Hotels createFromParcel(Parcel in) {
            return new Hotels(in);
        }

        public Hotels[] newArray(int size) {
            return (new Hotels[size]);
        }

    };

    protected Hotels(Parcel in) {
        in.readList(this.hotels, (Hotel.class.getClassLoader()));
    }

    public Hotels() {
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(hotels);
    }

    public int describeContents() {
        return 0;
    }

}
