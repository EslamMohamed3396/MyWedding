
package com.example.eslam.mywedding.Models.Offers;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.example.eslam.mywedding.Models.HallsModels.Hall;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Offer implements Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("hall_id")
    @Expose
    private int hallId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("images")
    @Expose
    private String images;
    @SerializedName("active")
    @Expose
    private int active;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("service_data")
    @Expose
    private List<ServiceDatum> serviceData = null;
    @SerializedName("hall")
    @Expose
    private Hall hall;
    public final static Creator<Offer> CREATOR = new Creator<Offer>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Offer createFromParcel(Parcel in) {
            return new Offer(in);
        }

        public Offer[] newArray(int size) {
            return (new Offer[size]);
        }

    }
    ;

    protected Offer(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.hallId = ((int) in.readValue((int.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.details = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((int) in.readValue((int.class.getClassLoader())));
        this.images = ((String) in.readValue((String.class.getClassLoader())));
        this.active = ((int) in.readValue((int.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.hall = ((Hall) in.readValue((Hall.class.getClassLoader())));
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<ServiceDatum> getServiceData() {
        return serviceData;
    }

    public void setServiceData(List<ServiceDatum> serviceData) {
        this.serviceData = serviceData;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(hallId);
        dest.writeValue(name);
        dest.writeValue(details);
        dest.writeValue(price);
        dest.writeValue(images);
        dest.writeValue(active);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeValue(hall);
    }

    public int describeContents() {
        return  0;
    }

}
