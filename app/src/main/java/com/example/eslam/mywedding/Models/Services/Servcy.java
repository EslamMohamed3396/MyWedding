package com.example.eslam.mywedding.Models.Services;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.eslam.mywedding.Models.ContryModel.Country_;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Servcy implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_en")
    @Expose
    private String nameEn;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("country_id")
    @Expose
    private int countryId;
    @SerializedName("details_ar")
    @Expose
    private String detailsAr;
    @SerializedName("details_en")
    @Expose
    private String detailsEn;
    @SerializedName("active")
    @Expose
    private int active;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("images")
    @Expose
    private List<ImageServices> images = null;
    @SerializedName("country")
    @Expose
    private Country_ country;


    private boolean isChecked;

    public boolean getChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }


    public final static Creator<Servcy> CREATOR = new Creator<Servcy>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Servcy createFromParcel(Parcel in) {
            return new Servcy(in);
        }

        public Servcy[] newArray(int size) {
            return (new Servcy[size]);
        }

    };

    protected Servcy(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.nameEn = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((int) in.readValue((int.class.getClassLoader())));
        this.countryId = ((int) in.readValue((int.class.getClassLoader())));
        this.detailsAr = ((String) in.readValue((String.class.getClassLoader())));
        this.detailsEn = ((String) in.readValue((String.class.getClassLoader())));
        this.active = ((int) in.readValue((int.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.images, (ImageServices.class.getClassLoader()));
        this.country = ((Country_) in.readValue((Country_.class.getClassLoader())));
        this.isChecked = ((boolean) in.readValue((boolean.class.getClassLoader())));
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

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getDetailsAr() {
        return detailsAr;
    }

    public void setDetailsAr(String detailsAr) {
        this.detailsAr = detailsAr;
    }

    public String getDetailsEn() {
        return detailsEn;
    }

    public void setDetailsEn(String detailsEn) {
        this.detailsEn = detailsEn;
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

    public List<ImageServices> getImages() {
        return images;
    }

    public void setImages(List<ImageServices> images) {
        this.images = images;
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
        dest.writeValue(nameEn);
        dest.writeValue(price);
        dest.writeValue(countryId);
        dest.writeValue(detailsAr);
        dest.writeValue(detailsEn);
        dest.writeValue(active);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeList(images);
        dest.writeValue(country);
        dest.writeValue(isChecked);
    }

    public int describeContents() {
        return 0;
    }

}
