package com.example.eslam.mywedding.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModle implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("fname")
    @Expose
    private String fname;
    @SerializedName("lname")
    @Expose
    private String lname;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("active")
    @Expose
    private int active;
    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("is_admin")
    @Expose
    private String isAdmin;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    private int countryId;

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public final static Parcelable.Creator<UserModle> CREATOR = new Creator<UserModle>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UserModle createFromParcel(Parcel in) {
            return new UserModle(in);
        }

        public UserModle[] newArray(int size) {
            return (new UserModle[size]);
        }

    };

    public UserModle(String fname, String lname, String country, int countryId, String email, String phone, String address, String image) {
        this.fname = fname;
        this.lname = lname;
        this.country = country;
        this.countryId = countryId;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.image = image;
    }

    protected UserModle(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.fname = ((String) in.readValue((String.class.getClassLoader())));
        this.lname = ((String) in.readValue((String.class.getClassLoader())));
        this.birthday = ((String) in.readValue((String.class.getClassLoader())));
        this.country = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.password = ((String) in.readValue((String.class.getClassLoader())));
        this.phone = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.active = ((int) in.readValue((int.class.getClassLoader())));
        this.apiToken = ((String) in.readValue((String.class.getClassLoader())));
        this.isAdmin = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
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
        dest.writeValue(fname);
        dest.writeValue(lname);
        dest.writeValue(birthday);
        dest.writeValue(country);
        dest.writeValue(email);
        dest.writeValue(password);
        dest.writeValue(phone);
        dest.writeValue(address);
        dest.writeValue(image);
        dest.writeValue(active);
        dest.writeValue(apiToken);
        dest.writeValue(isAdmin);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
    }

    public int describeContents() {
        return 0;
    }


}