package com.example.eslam.mywedding.Models.CarsDetails;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.eslam.mywedding.Models.ContryModel.Country_;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarsDeepDetails implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("model_id")
    @Expose
    private int modelId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("images")
    @Expose
    private List<CarsImages> images = null;
    @SerializedName("country_data")
    @Expose
    private Country_ countryData;
    public final static Parcelable.Creator<CarsDeepDetails> CREATOR = new Creator<CarsDeepDetails>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CarsDeepDetails createFromParcel(Parcel in) {
            return new CarsDeepDetails(in);
        }

        public CarsDeepDetails[] newArray(int size) {
            return (new CarsDeepDetails[size]);
        }

    };

    protected CarsDeepDetails(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.modelId = ((int) in.readValue((int.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((int) in.readValue((int.class.getClassLoader())));
        this.country = ((String) in.readValue((String.class.getClassLoader())));
        this.details = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.images, (CarsImages.class.getClassLoader()));
        this.countryData = ((Country_) in.readValue((Country_.class.getClassLoader())));
    }

    public CarsDeepDetails() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    public List<CarsImages> getImages() {
        return images;
    }

    public void setImages(List<CarsImages> images) {
        this.images = images;
    }

    public Country_ getCountryData() {
        return countryData;
    }

    public void setCountryData(Country_ countryData) {
        this.countryData = countryData;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(modelId);
        dest.writeValue(name);
        dest.writeValue(price);
        dest.writeValue(country);
        dest.writeValue(details);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeList(images);
        dest.writeValue(countryData);
    }

    public int describeContents() {
        return 0;
    }

}

