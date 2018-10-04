package com.example.eslam.mywedding.Models.Services;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListServciesModel implements Parcelable {

    @SerializedName("servcies")
    @Expose
    private List<Servcy> servcies = null;
    public final static Parcelable.Creator<ListServciesModel> CREATOR = new Creator<ListServciesModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ListServciesModel createFromParcel(Parcel in) {
            return new ListServciesModel(in);
        }

        public ListServciesModel[] newArray(int size) {
            return (new ListServciesModel[size]);
        }

    };

    protected ListServciesModel(Parcel in) {
        in.readList(this.servcies, (Servcy.class.getClassLoader()));
    }

    public ListServciesModel() {
    }

    public List<Servcy> getServcies() {
        return servcies;
    }

    public void setServcies(List<Servcy> servcies) {
        this.servcies = servcies;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(servcies);
    }

    public int describeContents() {
        return 0;
    }

}