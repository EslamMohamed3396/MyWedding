package com.example.eslam.mywedding.Models.Rests;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.eslam.mywedding.Models.HallsModels.Hall;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListRestsModles implements Parcelable {

    @SerializedName("resting")
    @Expose
    private List<Rests> resting = null;
    public final static Parcelable.Creator<ListRestsModles> CREATOR = new Creator<ListRestsModles>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ListRestsModles createFromParcel(Parcel in) {
            return new ListRestsModles(in);
        }

        public ListRestsModles[] newArray(int size) {
            return (new ListRestsModles[size]);
        }

    };

    protected ListRestsModles(Parcel in) {
        in.readList(this.resting, (Hall.class.getClassLoader()));
    }

    public ListRestsModles() {
    }

    public List<Rests> getResting() {
        return resting;
    }

    public void setResting(List<Rests> resting) {
        this.resting = resting;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(resting);
    }

    public int describeContents() {
        return 0;
    }

}