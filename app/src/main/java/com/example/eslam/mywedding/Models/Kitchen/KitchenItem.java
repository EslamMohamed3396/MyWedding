
package com.example.eslam.mywedding.Models.Kitchen;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KitchenItem implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("item_type")
    @Expose
    private String itemType;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("details")
    @Expose
    private String details;
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
    private List<Image> images = null;
    @SerializedName("country_data")
    @Expose
    private List<CountryDatum> countryData = null;

    private boolean isChecked;

    public boolean getChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public final static Creator<KitchenItem> CREATOR = new Creator<KitchenItem>() {


        @SuppressWarnings({
                "unchecked"
        })
        public KitchenItem createFromParcel(Parcel in) {
            return new KitchenItem(in);
        }

        public KitchenItem[] newArray(int size) {
            return (new KitchenItem[size]);
        }

    };

    protected KitchenItem(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.type = ((int) in.readValue((int.class.getClassLoader())));
        this.itemType = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.details = ((String) in.readValue((String.class.getClassLoader())));
        this.active = ((int) in.readValue((int.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.images, (Image.class.getClassLoader()));
        in.readList(this.countryData, (CountryDatum.class.getClassLoader()));
    }

    public KitchenItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<CountryDatum> getCountryData() {
        return countryData;
    }

    public void setCountryData(List<CountryDatum> countryData) {
        this.countryData = countryData;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(type);
        dest.writeValue(itemType);
        dest.writeValue(name);
        dest.writeValue(details);
        dest.writeValue(active);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeList(images);
        dest.writeList(countryData);
    }

    public int describeContents() {
        return 0;
    }

}
