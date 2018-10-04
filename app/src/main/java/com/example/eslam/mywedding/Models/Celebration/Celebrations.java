package com.example.eslam.mywedding.Models.Celebration;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Celebrations implements Parcelable {

    @SerializedName("celebrations")
    @Expose
    private List<Celebration> celebrations = null;
    public final static Parcelable.Creator<Celebrations> CREATOR = new Creator<Celebrations>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Celebrations createFromParcel(Parcel in) {
            return new Celebrations(in);
        }

        public Celebrations[] newArray(int size) {
            return (new Celebrations[size]);
        }

    };

    protected Celebrations(Parcel in) {
        in.readList(this.celebrations, (com.example.eslam.mywedding.Models.Celebration.Celebration.class.getClassLoader()));
    }

    public Celebrations() {
    }

    public List<Celebration> getCelebrations() {
        return celebrations;
    }

    public void setCelebrations(List<Celebration> celebrations) {
        this.celebrations = celebrations;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(celebrations);
    }

    public int describeContents() {
        return 0;
    }

}