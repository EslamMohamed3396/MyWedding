
package com.example.eslam.mywedding.Models.Hotels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageHotels implements Parcelable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("hotel_id")
    @Expose
    private int hotelId;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    public final static Creator<ImageHotels> CREATOR = new Creator<ImageHotels>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ImageHotels createFromParcel(Parcel in) {
            return new ImageHotels(in);
        }

        public ImageHotels[] newArray(int size) {
            return (new ImageHotels[size]);
        }

    };

    protected ImageHotels(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.hotelId = ((int) in.readValue((int.class.getClassLoader())));
        this.imageUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ImageHotels() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
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
        dest.writeValue(hotelId);
        dest.writeValue(imageUrl);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
    }

    public int describeContents() {
        return 0;
    }

}
