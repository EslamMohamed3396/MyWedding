package com.example.eslam.mywedding.Models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileModle implements Parcelable {

    @SerializedName("profile")
    @Expose
    private Profile profile;
    public final static Parcelable.Creator<ProfileModle> CREATOR = new Creator<ProfileModle>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProfileModle createFromParcel(Parcel in) {
            return new ProfileModle(in);
        }

        public ProfileModle[] newArray(int size) {
            return (new ProfileModle[size]);
        }

    };

    protected ProfileModle(Parcel in) {
        this.profile = ((Profile) in.readValue((Profile.class.getClassLoader())));
    }


    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(profile);
    }

    public int describeContents() {
        return 0;
    }

}