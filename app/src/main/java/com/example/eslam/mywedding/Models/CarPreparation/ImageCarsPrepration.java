package com.example.eslam.mywedding.Models.CarPreparation;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageCarsPrepration implements Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("preparation_id")
    @Expose
    private int preparationId;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    public final static Parcelable.Creator<ImageCarsPrepration> CREATOR = new Creator<ImageCarsPrepration>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ImageCarsPrepration createFromParcel(Parcel in) {
            return new ImageCarsPrepration(in);
        }

        public ImageCarsPrepration[] newArray(int size) {
            return (new ImageCarsPrepration[size]);
        }

    }
            ;

    protected ImageCarsPrepration(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.preparationId = ((int) in.readValue((int.class.getClassLoader())));
        this.imageUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ImageCarsPrepration() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPreparationId() {
        return preparationId;
    }

    public void setPreparationId(int preparationId) {
        this.preparationId = preparationId;
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
        dest.writeValue(preparationId);
        dest.writeValue(imageUrl);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
    }

    public int describeContents() {
        return 0;
    }

}