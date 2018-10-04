
package com.example.eslam.mywedding.Models.MyReservation;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kitchen implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    public final static Creator<Kitchen> CREATOR = new Creator<Kitchen>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Kitchen createFromParcel(Parcel in) {
            return new Kitchen(in);
        }

        public Kitchen[] newArray(int size) {
            return (new Kitchen[size]);
        }

    }
    ;

    protected Kitchen(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((int) in.readValue((int.class.getClassLoader())));
        this.quantity = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Kitchen() {
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(price);
        dest.writeValue(quantity);
    }

    public int describeContents() {
        return  0;
    }

}
