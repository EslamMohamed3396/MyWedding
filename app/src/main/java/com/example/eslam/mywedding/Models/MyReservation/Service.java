
package com.example.eslam.mywedding.Models.MyReservation;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Service implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("price")
    @Expose
    private int price;
    public final static Creator<Service> CREATOR = new Creator<Service>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Service createFromParcel(Parcel in) {
            return new Service(in);
        }

        public Service[] newArray(int size) {
            return (new Service[size]);
        }

    }
    ;

    protected Service(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((int) in.readValue((int.class.getClassLoader())));
    }

    public Service() {
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
