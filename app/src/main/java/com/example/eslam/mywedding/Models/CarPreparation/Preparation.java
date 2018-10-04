package com.example.eslam.mywedding.Models.CarPreparation;


import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.eslam.mywedding.Models.ContryModel.Country_;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Preparation implements Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("active")
    @Expose
    private int active;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("imageCarPreprations")
    @Expose
    private List<ImageCarsPrepration> imageCarPreprations = null;
    @SerializedName("country")
    @Expose
    private Country_ country;
    public final static Parcelable.Creator<Preparation> CREATOR = new Creator<Preparation>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Preparation createFromParcel(Parcel in) {
            return new Preparation(in);
        }

        public Preparation[] newArray(int size) {
            return (new Preparation[size]);
        }

    }
            ;

    protected Preparation(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.countryId = ((String) in.readValue((String.class.getClassLoader())));
        this.details = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((int) in.readValue((int.class.getClassLoader())));
        this.active = ((int) in.readValue((int.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.imageCarPreprations, (ImageCarsPrepration.class.getClassLoader()));
        this.country = ((Country_) in.readValue((Country_.class.getClassLoader())));
    }

    public Preparation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
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

    public List<ImageCarsPrepration> getImageCarPreprations() {
        return imageCarPreprations;
    }

    public void setImageCarPreprations(List<ImageCarsPrepration> imageCarPreprations) {
        this.imageCarPreprations = imageCarPreprations;
    }

    public Country_ getCountry() {
        return country;
    }

    public void setCountry(Country_ country) {
        this.country = country;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(countryId);
        dest.writeValue(details);
        dest.writeValue(price);
        dest.writeValue(active);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeList(imageCarPreprations);
        dest.writeValue(country);
    }

    public int describeContents() {
        return 0;
    }

}