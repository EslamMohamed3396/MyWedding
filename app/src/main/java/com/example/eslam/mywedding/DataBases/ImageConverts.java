package com.example.eslam.mywedding.DataBases;

import android.arch.persistence.room.TypeConverter;

import com.example.eslam.mywedding.Models.ImageModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ImageConverts {
    @TypeConverter
    public String fromOptionValuesList(List<ImageModel> imageModels) {
        if (imageModels == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ImageModel>>() {
        }.getType();
        String json = gson.toJson(imageModels, type);
        return json;
    }

    @TypeConverter
    public List<ImageModel> toOptionValuesList(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ImageModel>>() {
        }.getType();
        List<ImageModel> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }
}
