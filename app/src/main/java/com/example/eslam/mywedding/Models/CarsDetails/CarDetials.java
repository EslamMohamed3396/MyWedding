package com.example.eslam.mywedding.Models.CarsDetails;


import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarDetials implements Parcelable
{

    @SerializedName("cars")
    @Expose
    private List<CarsDeepDetails> cars = null;
    public final static Parcelable.Creator<CarDetials> CREATOR = new Creator<CarDetials>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CarDetials createFromParcel(Parcel in) {
            return new CarDetials(in);
        }

        public CarDetials[] newArray(int size) {
            return (new CarDetials[size]);
        }

    }
            ;

    protected CarDetials(Parcel in) {
        in.readList(this.cars, (CarsDeepDetails.class.getClassLoader()));
    }

    public CarDetials() {
    }

    public List<CarsDeepDetails> getCars() {
        return cars;
    }

    public void setCars(List<CarsDeepDetails> cars) {
        this.cars = cars;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(cars);
    }

    public int describeContents() {
        return 0;
    }

}