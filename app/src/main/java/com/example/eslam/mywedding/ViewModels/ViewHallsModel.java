package com.example.eslam.mywedding.ViewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.eslam.mywedding.API.ApiClient.ApiClient;
import com.example.eslam.mywedding.API.ApiInterFace.ApiInterface;
import com.example.eslam.mywedding.Models.HallsModels.Hall;
import com.example.eslam.mywedding.Models.HallsModels.ListHallsModles;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewHallsModel extends ViewModel {

    private MutableLiveData<List<Hall>> mutableLiveData;
    private ApiInterface mApiInterface;

    public LiveData<List<Hall>> getHalls() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<List<Hall>>();
            loadHalls();
        }
        return mutableLiveData;
    }

    private void loadHalls() {
        mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ListHallsModles> listCall = mApiInterface.getListHalls();
        listCall.enqueue(new Callback<ListHallsModles>() {
            @Override
            public void onResponse(Call<ListHallsModles> call, Response<ListHallsModles> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body().getHalls());
                }
            }

            @Override
            public void onFailure(Call<ListHallsModles> call, Throwable t) {
                Log.v("url", "" + t.toString());

            }
        });
    }
}
