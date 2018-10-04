package com.example.eslam.mywedding.Models.ContryModel;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "country")
public class Country_ implements Parcelable {

    @PrimaryKey
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

    public Country_(int id, String name, String arName, String code, String currencyName, String currencyCode, String currencySymbol) {
        this.id = id;
        this.name = name;
        this.arName = arName;
        this.code = code;
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
        this.currencySymbol = currencySymbol;
    }

    public final static Parcelable.Creator<Country_> CREATOR = new Creator<Country_>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Country_ createFromParcel(Parcel in) {
            return new Country_(in);
        }

        public Country_[] newArray(int size) {
            return (new Country_[size]);
        }

    };

    protected Country_(Parcel in) {
        this.id = ((int) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.arName = ((String) in.readValue((String.class.getClassLoader())));
        this.code = ((String) in.readValue((String.class.getClassLoader())));
        this.currencyName = ((String) in.readValue((String.class.getClassLoader())));
        this.currencyCode = ((String) in.readValue((String.class.getClassLoader())));
        this.currencySymbol = ((String) in.readValue((String.class.getClassLoader())));
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(arName);
        dest.writeValue(code);
        dest.writeValue(currencyName);
        dest.writeValue(currencyCode);
        dest.writeValue(currencySymbol);
    }

    public int describeContents() {
        return 0;
    }

}
