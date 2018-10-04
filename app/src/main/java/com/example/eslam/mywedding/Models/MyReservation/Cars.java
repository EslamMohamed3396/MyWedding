
package com.example.eslam.mywedding.Models.MyReservation;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cars implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("price")
    @Expose
    private int price;
    public final static Creator<Cars> CREATOR = new Creator<Cars>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Cars createFromParcel(Parcel in) {
            return new Cars(in);
        }

        public Cars[] newArray(int size) {
            return (new Cars[size]);
        }

    }
    ;

    protected Cars(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((int) in.readValue((int.class.getClassLoader())));
    }

    public Cars() {
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
