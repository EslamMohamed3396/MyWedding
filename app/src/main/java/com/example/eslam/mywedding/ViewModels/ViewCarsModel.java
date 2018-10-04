package com.example.eslam.mywedding.ViewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.eslam.mywedding.API.ApiClient.ApiClient;
import com.example.eslam.mywedding.API.ApiInterFace.ApiInterface;
import com.example.eslam.mywedding.Models.CarsDetails.CarDetials;
import com.example.eslam.mywedding.Models.CarsDetails.CarsDeepDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCarsModel extends ViewModel {

    private MutableLiveData<List<CarsDeepDetails>> mutableLiveData;
    private ApiInterface mApiInterface;

    public LiveData<List<CarsDeepDetails>> getHalls(String id) {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
            loadHalls(id);
        }
        return mutableLiveData;
    }

    private void loadHalls(String id) {
        mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<CarDetials> listCall = mApiInterface.getCarsbyId(id);
        listCall.enqueue(new Callback<CarDetials>() {
            @Override
            public void onResponse(Call<CarDetials> call, Response<CarDetials> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body().getCars());
                }
            }
            @Override
            public void onFailure(Call<CarDetials> call, Throwable t) {
                Log.v("url", "" + t.toString());

            }
        });
    }
}
