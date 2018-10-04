package com.example.eslam.mywedding.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.eslam.mywedding.DataBases.SetupDataBase;
import com.example.eslam.mywedding.Models.HallsModels.Hall;

import java.util.List;

public class MainFavViewModel extends AndroidViewModel {
    private LiveData<List<Hall>> data;

    public MainFavViewModel(@NonNull Application application) {
        super(application);
        SetupDataBase dataBaseMovie = SetupDataBase.getINSTANCE(this.getApplication());
        data = dataBaseMovie.daoHalls().loadAllHalls();
    }

    public LiveData<List<Hall>> getHall() {
        return data;
    }

}