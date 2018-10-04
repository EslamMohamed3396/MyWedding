
package com.example.eslam.mywedding.Models.MyReservation;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hotels implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("number")
    @Expose
    private String number;
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

    }
    ;

    protected Hotels(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((int) in.readValue((int.class.getClassLoader())));
        this.number = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Hotels() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(price);
        dest.writeValue(number);
    }

    public int describeContents() {
        return  0;
    }

}
