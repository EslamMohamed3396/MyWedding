package com.example.eslam.mywedding.Models.CarPreparation;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarPreparation implements Parcelable {

    @SerializedName("preparation")
    @Expose
    private List<Preparation> preparation = null;
    public final static Parcelable.Creator<CarPreparation> CREATOR = new Creator<CarPreparation>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CarPreparation createFromParcel(Parcel in) {
            return new CarPreparation(in);
        }

        public CarPreparation[] newArray(int size) {
            return (new CarPreparation[size]);
        }

    };

    protected CarPreparation(Parcel in) {
        in.readList(this.preparation, (com.example.eslam.mywedding.Models.CarPreparation.Preparation.class.getClassLoader()));
    }

    public CarPreparation() {
    }

    public List<Preparation> getPreparation() {
        return preparation;
    }

    public void setPreparation(List<Preparation> preparation) {
        this.preparation = preparation;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(preparation);
    }

    public int describeContents() {
        return 0;
    }

}