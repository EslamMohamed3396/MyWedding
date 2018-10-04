
package com.example.eslam.mywedding.Models.MyReservation;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Services implements Parcelable
{

    @SerializedName("services")
    @Expose
    private List<Service> services = null;
    @SerializedName("cars")
    @Expose
    private Cars cars;
    @SerializedName("preparations")
    @Expose
    private Preparations preparations;
    @SerializedName("kitchens")
    @Expose
    private List<Kitchen> kitchens = null;
    @SerializedName("hotels")
    @Expose
    private Hotels hotels;
    public final static Creator<Services> CREATOR = new Creator<Services>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Services createFromParcel(Parcel in) {
            return new Services(in);
        }

        public Services[] newArray(int size) {
            return (new Services[size]);
        }

    }
    ;

    protected Services(Parcel in) {
        in.readList(this.services, (Service.class.getClassLoader()));
        this.cars = ((Cars) in.readValue((Cars.class.getClassLoader())));
        this.preparations = ((Preparations) in.readValue((Preparations.class.getClassLoader())));
        in.readList(this.kitchens, (Kitchen.class.getClassLoader()));
        this.hotels = ((Hotels) in.readValue((Hotels.class.getClassLoader())));
    }

    public Services() {
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public Cars getCars() {
        return cars;
    }

    public void setCars(Cars cars) {
        this.cars = cars;
    }

    public Preparations getPreparations() {
        return preparations;
    }

    public void setPreparations(Preparations preparations) {
        this.preparations = preparations;
    }

    public List<Kitchen> getKitchens() {
        return kitchens;
    }

    public void setKitchens(List<Kitchen> kitchens) {
        this.kitchens = kitchens;
    }

    public Hotels getHotels() {
        return hotels;
    }

    public void setHotels(Hotels hotels) {
        this.hotels = hotels;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(services);
        dest.writeValue(cars);
        dest.writeValue(preparations);
        dest.writeList(kitchens);
        dest.writeValue(hotels);
    }

    public int describeContents() {
        return  0;
    }

}
