
package com.example.eslam.mywedding.Models.NearbyLocations;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NearbyLocation implements Parcelable
{

    @SerializedName("listings")
    @Expose
    private List<Listing> listings = null;
    public final static Creator<NearbyLocation> CREATOR = new Creator<NearbyLocation>() {


        @SuppressWarnings({
            "unchecked"
        })
        public NearbyLocation createFromParcel(Parcel in) {
            return new NearbyLocation(in);
        }

        public NearbyLocation[] newArray(int size) {
            return (new NearbyLocation[size]);
        }

    }
    ;

    protected NearbyLocation(Parcel in) {
        in.readList(this.listings, (Listing.class.getClassLoader()));
    }

    public NearbyLocation() {
    }

    public List<Listing> getListings() {
        return listings;
    }

    public void setListings(List<Listing> listings) {
        this.listings = listings;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(listings);
    }

    public int describeContents() {
        return  0;
    }

}
