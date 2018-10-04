
package com.example.eslam.mywedding.Models.MyReservation;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyReservation implements Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("client_id")
    @Expose
    private int clientId;
    @SerializedName("hall_id")
    @Expose
    private int hallId;
    @SerializedName("services")
    @Expose
    private Services services;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("offer")
    @Expose
    private int offer;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("celebration")
    @Expose
    private int celebration;
    @SerializedName("guest")
    @Expose
    private int guest;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("service")
    @Expose
    private Service_ service;
    @SerializedName("offer_data")
    @Expose
    private OfferData offerData;
    @SerializedName("hall")
    @Expose
    private Hall hall;
    public final static Creator<MyReservation> CREATOR = new Creator<MyReservation>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MyReservation createFromParcel(Parcel in) {
            return new MyReservation(in);
        }

        public MyReservation[] newArray(int size) {
            return (new MyReservation[size]);
        }

    }
    ;

    protected MyReservation(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.clientId = ((int) in.readValue((int.class.getClassLoader())));
        this.hallId = ((int) in.readValue((int.class.getClassLoader())));
        this.services = ((Services) in.readValue((Services.class.getClassLoader())));
        this.quantity = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((String) in.readValue((String.class.getClassLoader())));
        this.offer = ((int) in.readValue((int.class.getClassLoader())));
        this.date = ((String) in.readValue((String.class.getClassLoader())));
        this.time = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((int) in.readValue((int.class.getClassLoader())));
        this.celebration = ((int) in.readValue((int.class.getClassLoader())));
        this.guest = ((int) in.readValue((int.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.service = ((Service_) in.readValue((Service_.class.getClassLoader())));
        this.offerData = ((OfferData) in.readValue((OfferData.class.getClassLoader())));
        this.hall = ((Hall) in.readValue((Hall.class.getClassLoader())));
    }

    public MyReservation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getOffer() {
        return offer;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCelebration() {
        return celebration;
    }

    public void setCelebration(int celebration) {
        this.celebration = celebration;
    }

    public int getGuest() {
        return guest;
    }

    public void setGuest(int guest) {
        this.guest = guest;
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

    public Service_ getService() {
        return service;
    }

    public void setService(Service_ service) {
        this.service = service;
    }

    public OfferData getOfferData() {
        return offerData;
    }

    public void setOfferData(OfferData offerData) {
        this.offerData = offerData;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(clientId);
        dest.writeValue(hallId);
        dest.writeValue(services);
        dest.writeValue(quantity);
        dest.writeValue(price);
        dest.writeValue(offer);
        dest.writeValue(date);
        dest.writeValue(time);
        dest.writeValue(status);
        dest.writeValue(celebration);
        dest.writeValue(guest);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeValue(service);
        dest.writeValue(offerData);
        dest.writeValue(hall);
    }

    public int describeContents() {
        return  0;
    }

}
