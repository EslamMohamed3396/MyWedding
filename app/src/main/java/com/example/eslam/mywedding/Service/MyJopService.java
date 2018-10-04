package com.example.eslam.mywedding.Service;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.example.eslam.mywedding.API.ApiClient.ApiClient;
import com.example.eslam.mywedding.API.ApiInterFace.ApiInterface;
import com.example.eslam.mywedding.Adapters.List_Halls_Adapters;
import com.example.eslam.mywedding.Models.HallsModels.Hall;
import com.example.eslam.mywedding.Models.HallsModels.ListHallsModles;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class MyJopService extends JobService implements List_Halls_Adapters.HallsAdapterOnClick {
    public ApiInterface mApiInterface;
    public List<Hall> hall = new ArrayList<>();
    public BackGroundTaak backGroundTaak;
    private List_Halls_Adapters listHallsAdapters;

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
        listHallsAdapters = new List_Halls_Adapters(new ArrayList<Hall>(), this);
        backGroundTaak = new BackGroundTaak() {
            @Override
            protected void onPostExecute(List<Hall> halls) {
                if (halls.size() > 0 || !halls.isEmpty()) {
                    listHallsAdapters.clear();
                    listHallsAdapters.setList(halls);
                } else {
                    Log.v("servicesRunung", "noooooooooo");
                }
                jobFinished(jobParameters, false);
            }
        };

        backGroundTaak.execute();

        return true;
    }

    public void loadHalls() {

        mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ListHallsModles> listCall = mApiInterface.getListHalls();
        listCall.enqueue(new Callback<ListHallsModles>() {
            @Override
            public void onResponse(Call<ListHallsModles> call, Response<ListHallsModles> response) {
                if (response.isSuccessful()) {
                    hall = response.body().getHalls();
                }
            }

            @Override
            public void onFailure(Call<ListHallsModles> call, Throwable t) {
                Log.v("url", "" + t.toString());
            }
        });


    }

    @Override
    public void onClick(Hall listHallsAdapters) {

    }

    public class BackGroundTaak extends AsyncTask<Void, Void, List<Hall>> {

        @Override
        protected List<Hall> doInBackground(Void... voids) {
            loadHalls();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return hall;
        }
    }


    @Override
    public boolean onStopJob(JobParameters job) {
        if (backGroundTaak != null) {
            backGroundTaak.cancel(true);
        }
        return true;
    }
}
