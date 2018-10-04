package com.example.eslam.mywedding.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogInModel implements Parcelable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("token")
    @Expose
    private String token;
    public final static Parcelable.Creator<LogInModel> CREATOR = new Creator<LogInModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public LogInModel createFromParcel(Parcel in) {
            return new LogInModel(in);
        }

        public LogInModel[] newArray(int size) {
            return (new LogInModel[size]);
        }

    };

    protected LogInModel(Parcel in) {
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