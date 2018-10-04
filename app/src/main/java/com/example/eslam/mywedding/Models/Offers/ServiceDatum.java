
package com.example.eslam.mywedding.Models.Offers;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceDatum implements Parcelable
{

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
    public final static Creator<ServiceDatum> CREATOR = new Creator<ServiceDatum>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ServiceDatum createFromParcel(Parcel in) {
            return new ServiceDatum(in);
        }

        public ServiceDatum[] newArray(int size) {
            return (new ServiceDatum[size]);
        }

    }
    ;

    protected ServiceDatum(Parcel in) {
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
    }

    public ServiceDatum() {
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
    }

    public int describeContents() {
        return  0;
    }

}
