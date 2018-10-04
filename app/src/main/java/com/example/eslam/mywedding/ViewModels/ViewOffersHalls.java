package com.example.eslam.mywedding.ViewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.eslam.mywedding.API.ApiClient.ApiClient;
import com.example.eslam.mywedding.API.ApiInterFace.ApiInterface;
import com.example.eslam.mywedding.Models.HallsModels.Hall;
import com.example.eslam.mywedding.Models.HallsModels.ListHallsModles;
import com.example.eslam.mywedding.Models.Offers.Offer;
import com.example.eslam.mywedding.Models.Offers.Offers;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewOffersHalls extends ViewModel {

    private MutableLiveData<List<Offer>> mutableLiveData;
    private ApiInterface mApiInterface;

    public LiveData<List<Offer>> getOffers() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<List<Offer>>();
            loadHalls();
        }
        return mutableLiveData;
    }

    private void loadHalls() {
        mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Offers> listCall = mApiInterface.getListOffers();
        listCall.enqueue(new Callback<Offers>() {
            @Override
            public void onResponse(Call<Offers> call, Response<Offers> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body().getOffers());
                }
            }

            @Override
            public void onFailure(Call<Offers> call, Throwable t) {
                Log.v("url", "" + t.toString());

            }
        });
    }
}
