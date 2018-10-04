package com.example.eslam.mywedding.Models.Services;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageServices implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("service_id")
    @Expose
    private int serviceId;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    public final static Parcelable.Creator<ImageServices> CREATOR = new Creator<ImageServices>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ImageServices createFromParcel(Parcel in) {
            return new ImageServices(in);
        }

        public ImageServices[] newArray(int size) {
            return (new ImageServices[size]);
        }

    };

    protected ImageServices(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.serviceId = ((int) in.readValue((int.class.getClassLoader())));
        this.imageUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
        dest.writeValue(serviceId);
        dest.writeValue(imageUrl);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
    }

    public int describeContents() {
        return 0;
    }

}