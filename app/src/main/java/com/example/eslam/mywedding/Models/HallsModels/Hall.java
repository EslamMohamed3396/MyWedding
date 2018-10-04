package com.example.eslam.mywedding.Models.HallsModels;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.eslam.mywedding.DataBases.ImageConverts;
import com.example.eslam.mywedding.Models.ContryModel.Country_;
import com.example.eslam.mywedding.Models.ImageModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "hall")
public class Hall implements Parcelable {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("max_number")
    @Expose
    private int maxNumber;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("lat")
    @Expose
    private double lat;
    @SerializedName("lng")
    @Expose
    private double lng;
    @SerializedName("active")
    @Expose
    private int active;
    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @TypeConverters(ImageConverts.class)
    @SerializedName("images")
    @Expose
    private List<ImageModel> imageModels = new ArrayList<>();
    @Ignore
    @SerializedName("Country")
    @Expose
    private Country_ country;

    public Hall(int id, String name, String address, String phoneNumber, int maxNumber, String details, int price, List<ImageModel> imageModels) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.maxNumber = maxNumber;
        this.details = details;
        this.price = price;
        this.imageModels = imageModels;
    }

    public final static Parcelable.Creator<Hall> CREATOR = new Creator<Hall>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Hall createFromParcel(Parcel in) {
            return new Hall(in);
        }

        public Hall[] newArray(int size) {
            return (new Hall[size]);
        }

    };

    protected Hall(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.phoneNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.maxNumber = ((int) in.readValue((int.class.getClassLoader())));
        this.details = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((int) in.readValue((int.class.getClassLoader())));
        this.countryId = ((String) in.readValue((String.class.getClassLoader())));
        this.lat = ((double) in.readValue((double.class.getClassLoader())));
        this.lng = ((double) in.readValue((double.class.getClassLoader())));
        this.active = ((int) in.readValue((int.class.getClassLoader())));
        this.type = ((int) in.readValue((int.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.country = ((Country_) in.readValue((Country_.class.getClassLoader())));
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
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

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public List<ImageModel> getImageModels() {
        return imageModels;
    }

    public void setImageModels(List<ImageModel> imageModels) {
        this.imageModels = imageModels;
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
        dest.writeValue(address);
        dest.writeValue(phoneNumber);
        dest.writeValue(maxNumber);
        dest.writeValue(details);
        dest.writeValue(price);
        dest.writeValue(countryId);
        dest.writeValue(lat);
        dest.writeValue(lng);
        dest.writeValue(active);
        dest.writeValue(type);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeValue(country);
    }

    public int describeContents() {
        return 0;
    }

}
