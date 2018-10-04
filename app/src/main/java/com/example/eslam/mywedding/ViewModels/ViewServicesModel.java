package com.example.eslam.mywedding.ViewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.eslam.mywedding.API.ApiClient.ApiClient;
import com.example.eslam.mywedding.API.ApiInterFace.ApiInterface;
import com.example.eslam.mywedding.Models.Services.ListServciesModel;
import com.example.eslam.mywedding.Models.Services.Servcy;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewServicesModel extends ViewModel {
    private MutableLiveData<List<Servcy>> mutableLiveData;
    private ApiInterface mApiInterface;

    public LiveData<List<Servcy>> getService() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<List<Servcy>>();
            loadServices();
        }
        return mutableLiveData;
    }

    private void loadServices() {
        mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ListServciesModel> listCall = mApiInterface.getServices();
        listCall.enqueue(new Callback<ListServciesModel>() {
            @Override
            public void onResponse(Call<ListServciesModel> call, Response<ListServciesModel> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body().getServcies());
                }
            }

            @Override
            public void onFailure(Call<ListServciesModel> call, Throwable t) {
                Log.v("url", "" + t.toString());

            }
        });
    }
}
