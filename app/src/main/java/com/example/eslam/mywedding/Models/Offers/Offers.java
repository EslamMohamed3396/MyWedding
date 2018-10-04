
package com.example.eslam.mywedding.Models.Offers;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Offers implements Parcelable
{

    @SerializedName("offers")
    @Expose
    private List<Offer> offers = null;
    public final static Creator<Offers> CREATOR = new Creator<Offers>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Offers createFromParcel(Parcel in) {
            return new Offers(in);
        }

        public Offers[] newArray(int size) {
            return (new Offers[size]);
        }

    }
    ;

    protected Offers(Parcel in) {
        in.readList(this.offers, (Offer.class.getClassLoader()));
    }

    public Offers() {
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(offers);
    }

    public int describeContents() {
        return  0;
    }

}
