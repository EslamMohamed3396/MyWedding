
package com.example.eslam.mywedding.Models.Hotels;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.eslam.mywedding.Models.ContryModel.Country_;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hotel implements Parcelable {

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
    @SerializedName("rooms_number")
    @Expose
    private int roomsNumber;
    @SerializedName("starts_number")
    @Expose
    private int startsNumber;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("lat")
    @Expose
    private double lat;
    @SerializedName("lng")
    @Expose
    private double lng;
    @SerializedName("active")
    @Expose
    private int active;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("imageHotels")
    @Expose
    private List<ImageHotels> imageHotels = null;
    @SerializedName("country")
    @Expose
    private Country_ country;


    private String editTextValue="";

    public String getEditTextValue() {
        return editTextValue;
    }

    public void setEditTextValue(String editTextValue) {
        this.editTextValue = editTextValue;
    }

    private boolean isChecked;

    public boolean getChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }


    public final static Creator<Hotel> CREATOR = new Creator<Hotel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Hotel createFromParcel(Parcel in) {
            return new Hotel(in);
        }

        public Hotel[] newArray(int size) {
            return (new Hotel[size]);
        }

    };

    protected Hotel(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.phoneNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.roomsNumber = ((int) in.readValue((int.class.getClassLoader())));
        this.startsNumber = ((int) in.readValue((int.class.getClassLoader())));
        this.countryId = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((int) in.readValue((int.class.getClassLoader())));
        this.details = ((String) in.readValue((String.class.getClassLoader())));
        this.lat = ((double) in.readValue((double.class.getClassLoader())));
        this.lng = ((double) in.readValue((double.class.getClassLoader())));
        this.active = ((int) in.readValue((int.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.editTextValue = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.imageHotels, (ImageHotels.class.getClassLoader()));
        this.country = ((Country_) in.readValue((Country_.class.getClassLoader())));
        this.isChecked = ((boolean) in.readValue((boolean.class.getClassLoader())));
    }

    public Hotel() {
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

    public int getRoomsNumber() {
        return roomsNumber;
    }

    public void setRoomsNumber(int roomsNumber) {
        this.roomsNumber = roomsNumber;
    }

    public int getStartsNumber() {
        return startsNumber;
    }

    public void setStartsNumber(int startsNumber) {
        this.startsNumber = startsNumber;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    public List<ImageHotels> getImageHotels() {
        return imageHotels;
    }

    public void setImageHotels(List<ImageHotels> imageHotels) {
        this.imageHotels = imageHotels;
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
        dest.writeValue(roomsNumber);
        dest.writeValue(startsNumber);
        dest.writeValue(countryId);
        dest.writeValue(price);
        dest.writeValue(details);
        dest.writeValue(lat);
        dest.writeValue(lng);
        dest.writeValue(active);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeList(imageHotels);
        dest.writeValue(country);
        dest.writeValue(isChecked);
        dest.writeValue(editTextValue);
    }

    public int describeContents() {
        return 0;
    }

}
