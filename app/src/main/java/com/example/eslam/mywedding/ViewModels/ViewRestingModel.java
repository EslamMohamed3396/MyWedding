package com.example.eslam.mywedding.ViewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.eslam.mywedding.API.ApiClient.ApiClient;
import com.example.eslam.mywedding.API.ApiInterFace.ApiInterface;
import com.example.eslam.mywedding.Models.HallsModels.Hall;
import com.example.eslam.mywedding.Models.Rests.ListRestsModles;
import com.example.eslam.mywedding.Models.Rests.Rests;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewRestingModel extends ViewModel {
    private MutableLiveData<List<Rests>> mutableLiveData;
    private ApiInterface mApiInterface;

    public LiveData<List<Rests>> getResting() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<List<Rests>>();
            loadResting();
        }
        return mutableLiveData;
    }

    private void loadResting() {
        mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ListRestsModles> listCall = mApiInterface.getRestsHalls();
        listCall.enqueue(new Callback<ListRestsModles>() {
            @Override
            public void onResponse(Call<ListRestsModles> call, Response<ListRestsModles> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body().getResting());
                }
            }

            @Override
            public void onFailure(Call<ListRestsModles> call, Throwable t) {
                Log.v("url", "" + t.toString());

            }
        });
    }
}
