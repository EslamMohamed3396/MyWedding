package com.example.eslam.mywedding.Models.HallsModels;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.eslam.mywedding.DataBases.HallsConverts;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "listHall")
public class ListHallsModles implements Parcelable {

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @TypeConverters(HallsConverts.class)
    @SerializedName("halls")
    @Expose
    private List<Hall> halls = null;


    public ListHallsModles(int id, List<Hall> halls) {
        this.id = id;
        this.halls = halls;
    }

    public final static Parcelable.Creator<ListHallsModles> CREATOR = new Creator<ListHallsModles>() {

        @SuppressWarnings({
                "unchecked"
        })
        public ListHallsModles createFromParcel(Parcel in) {
            return new ListHallsModles(in);
        }

        public ListHallsModles[] newArray(int size) {
            return (new ListHallsModles[size]);
        }

    };

    protected ListHallsModles(Parcel in) {
        in.readList(this.halls, (Hall.class.getClassLoader()));
    }


    public List<Hall> getHalls() {
        return halls;
    }

    public void setHalls(List<Hall> halls) {
        this.halls = halls;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(halls);
    }

    public int describeContents() {
        return 0;
    }

}