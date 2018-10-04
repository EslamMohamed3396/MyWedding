package com.example.eslam.mywedding.Models.Cars;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cars implements Parcelable {

    @SerializedName("brands")
    @Expose
    private List<Brand> brands = null;
    public final static Parcelable.Creator<Cars> CREATOR = new Creator<Cars>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Cars createFromParcel(Parcel in) {
            return new Cars(in);
        }

        public Cars[] newArray(int size) {
            return (new Cars[size]);
        }

    };

    protected Cars(Parcel in) {
        in.readList(this.brands, (com.example.eslam.mywedding.Models.Cars.Brand.class.getClassLoader()));
    }

    public Cars() {
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(brands);
    }

    public int describeContents() {
        return 0;
    }

}