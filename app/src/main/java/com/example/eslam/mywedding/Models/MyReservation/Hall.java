
package com.example.eslam.mywedding.Models.MyReservation;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hall implements Parcelable
{

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
    public final static Creator<Hall> CREATOR = new Creator<Hall>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Hall createFromParcel(Parcel in) {
            return new Hall(in);
        }

        public Hall[] newArray(int size) {
            return (new Hall[size]);
        }

    }
    ;

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
    }

    public Hall() {
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
    }

    public int describeContents() {
        return  0;
    }

}
