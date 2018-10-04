
package com.example.eslam.mywedding.Models.Kitchen;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pivot implements Parcelable
{

    @SerializedName("item_id")
    @Expose
    private int itemId;
    @SerializedName("country_id")
    @Expose
    private int countryId;
    @SerializedName("price")
    @Expose
    private int price;
    public final static Creator<Pivot> CREATOR = new Creator<Pivot>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Pivot createFromParcel(Parcel in) {
            return new Pivot(in);
        }

        public Pivot[] newArray(int size) {
            return (new Pivot[size]);
        }

    }
    ;

    protected Pivot(Parcel in) {
        this.itemId = ((int) in.readValue((int.class.getClassLoader())));
        this.countryId = ((int) in.readValue((int.class.getClassLoader())));
        this.price = ((int) in.readValue((int.class.getClassLoader())));
    }

    public Pivot() {
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(itemId);
        dest.writeValue(countryId);
        dest.writeValue(price);
    }

    public int describeContents() {
        return  0;
    }

}
