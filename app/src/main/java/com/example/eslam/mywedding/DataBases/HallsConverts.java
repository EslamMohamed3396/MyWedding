package com.example.eslam.mywedding.DataBases;

import android.arch.persistence.room.TypeConverter;

import com.example.eslam.mywedding.Models.HallsModels.Hall;
import com.example.eslam.mywedding.Models.HallsModels.ListHallsModles;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class HallsConverts {
    @TypeConverter
    public String fromOptionValuesList(List<Hall> listHallsModles) {
        if (listHallsModles == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Hall>>() {
        }.getType();
        String json = gson.toJson(listHallsModles, type);
        return json;
    }

    @TypeConverter
    public List<Hall> toOptionValuesList(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Hall>>() {
        }.getType();
        List<Hall> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }
}
