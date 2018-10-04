
package com.example.eslam.mywedding.Models.Kitchen;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kitchen implements Parcelable
{

    @SerializedName("kitchen_items")
    @Expose
    private List<KitchenItem> kitchenItems = null;
    public final static Creator<Kitchen> CREATOR = new Creator<Kitchen>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Kitchen createFromParcel(Parcel in) {
            return new Kitchen(in);
        }

        public Kitchen[] newArray(int size) {
            return (new Kitchen[size]);
        }

    }
    ;

    protected Kitchen(Parcel in) {
        in.readList(this.kitchenItems, (KitchenItem.class.getClassLoader()));
    }

    public Kitchen() {
    }

    public List<KitchenItem> getKitchenItems() {
        return kitchenItems;
    }

    public void setKitchenItems(List<KitchenItem> kitchenItems) {
        this.kitchenItems = kitchenItems;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(kitchenItems);
    }

    public int describeContents() {
        return  0;
    }

}
