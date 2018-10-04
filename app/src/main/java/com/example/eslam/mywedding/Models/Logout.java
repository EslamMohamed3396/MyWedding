package com.example.eslam.mywedding.Models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Logout implements Parcelable {

    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<Logout> CREATOR = new Creator<Logout>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Logout createFromParcel(Parcel in) {
            return new Logout(in);
        }

        public Logout[] newArray(int size) {
            return (new Logout[size]);
        }

    };

    protected Logout(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Logout() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }

}