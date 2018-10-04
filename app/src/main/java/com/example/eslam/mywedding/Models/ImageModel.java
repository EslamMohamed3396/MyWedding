package com.example.eslam.mywedding.Models;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "image")
public class ImageModel implements Parcelable {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("hall_id")
    @Expose
    private int hallId;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public ImageModel(int id, int hallId, String imageUrl, String createdAt, String updatedAt) {
        this.id = id;
        this.hallId = hallId;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public final static Parcelable.Creator<ImageModel> CREATOR = new Creator<ImageModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ImageModel createFromParcel(Parcel in) {
            return new ImageModel(in);
        }

        public ImageModel[] newArray(int size) {
            return (new ImageModel[size]);
        }

    };

    protected ImageModel(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.hallId = ((int) in.readValue((int.class.getClassLoader())));
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

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
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
        dest.writeValue(hallId);
        dest.writeValue(imageUrl);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
    }

    public int describeContents() {
        return 0;
    }

}