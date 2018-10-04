package com.example.eslam.mywedding.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpMessageModel implements Parcelable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("token")
    @Expose
    private String token;
    public final static Parcelable.Creator<SignUpMessageModel> CREATOR = new Creator<SignUpMessageModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SignUpMessageModel createFromParcel(Parcel in) {
            return new SignUpMessageModel(in);
        }

        public SignUpMessageModel[] newArray(int size) {
            return (new SignUpMessageModel[size]);
        }

    };

    protected SignUpMessageModel(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.token = ((String) in.readValue((String.class.getClassLoader())));
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
        dest.writeValue(token);
    }

    public int describeContents() {
        return 0;
    }

}