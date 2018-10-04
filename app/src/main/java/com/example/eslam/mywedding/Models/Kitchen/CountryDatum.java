
package com.example.eslam.mywedding.Models.Kitchen;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryDatum implements Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ar_name")
    @Expose
    private String arName;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("currency_name")
    @Expose
    private String currencyName;
    @SerializedName("currency_code")
    @Expose
    private String currencyCode;
    @SerializedName("currency_symbol")
    @Expose
    private String currencySymbol;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("pivot")
    @Expose
    private Pivot pivot;
    public final static Creator<CountryDatum> CREATOR = new Creator<CountryDatum>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CountryDatum createFromParcel(Parcel in) {
            return new CountryDatum(in);
        }

        public CountryDatum[] newArray(int size) {
            return (new CountryDatum[size]);
        }

    }
    ;

    protected CountryDatum(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.arName = ((String) in.readValue((String.class.getClassLoader())));
        this.code = ((String) in.readValue((String.class.getClassLoader())));
        this.currencyName = ((String) in.readValue((String.class.getClassLoader())));
        this.currencyCode = ((String) in.readValue((String.class.getClassLoader())));
        this.currencySymbol = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.pivot = ((Pivot) in.readValue((Pivot.class.getClassLoader())));
    }

    public CountryDatum() {
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

    public String getArName() {
        return arName;
    }

    public void setArName(String arName) {
        this.arName = arName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
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

    public Pivot getPivot() {
        return pivot;
    }

    public void setPivot(Pivot pivot) {
        this.pivot = pivot;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(arName);
        dest.writeValue(code);
        dest.writeValue(currencyName);
        dest.writeValue(currencyCode);
        dest.writeValue(currencySymbol);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeValue(pivot);
    }

    public int describeContents() {
        return  0;
    }

}
