package com.example.eslam.mywedding.Models.ContryModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContryModel implements Parcelable {

    @SerializedName("countries")
    @Expose
    private List<Country_> countries = null;
    public final static Parcelable.Creator<ContryModel> CREATOR = new Creator<ContryModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ContryModel createFromParcel(Parcel in) {
            return new ContryModel(in);
        }

        public ContryModel[] newArray(int size) {
            return (new ContryModel[size]);
        }

    };

    protected ContryModel(Parcel in) {
        in.readList(this.countries, (Country_.class.getClassLoader()));
    }


    public List<Country_> getCountries() {
        return countries;
    }

    public void setCountries(List<Country_> countries) {
        this.countries = countries;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(countries);
    }

    public int describeContents() {
        return 0;
    }

}
