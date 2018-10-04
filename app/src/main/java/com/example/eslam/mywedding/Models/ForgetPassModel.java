package com.example.eslam.mywedding.Models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgetPassModel implements Parcelable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<ForgetPassModel> CREATOR = new Creator<ForgetPassModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ForgetPassModel createFromParcel(Parcel in) {
            return new ForgetPassModel(in);
        }

        public ForgetPassModel[] newArray(int size) {
            return (new ForgetPassModel[size]);
        }

    };

    protected ForgetPassModel(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ForgetPassModel() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }

}