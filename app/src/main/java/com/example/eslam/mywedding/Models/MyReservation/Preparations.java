
package com.example.eslam.mywedding.Models.MyReservation;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Preparations implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("price")
    @Expose
    private int price;
    public final static Creator<Preparations> CREATOR = new Creator<Preparations>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Preparations createFromParcel(Parcel in) {
            return new Preparations(in);
        }

        public Preparations[] newArray(int size) {
            return (new Preparations[size]);
        }

    }
    ;

    protected Preparations(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((int) in.readValue((int.class.getClassLoader())));
    }

    public Preparations() {
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(price);
    }

    public int describeContents() {
        return  0;
    }

}
