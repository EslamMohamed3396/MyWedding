package com.example.eslam.mywedding.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eslam.mywedding.API.ApiClient.ApiClient;
import com.example.eslam.mywedding.API.ApiInterFace.ApiInterface;
import com.example.eslam.mywedding.Adapters.ListReservationAdapter;
import com.example.eslam.mywedding.HelperMethod.Shared_Preferences;
import com.example.eslam.mywedding.Models.MyReservation.MyReservation;
import com.example.eslam.mywedding.Models.MyReservation.Reservatios;
import com.example.eslam.mywedding.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyReservationFragment extends Fragment {
    View rootView;
    @BindView(R.id.rv_my_reservation)
    RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private ListReservationAdapter listReservation;
    private ApiInterface mApiInterface;
    private static final String AUTH_BEARER = "Bearer ";
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.my_reservation_fragment, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        getReservationFromApi();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void bindRecycler() {
        listReservation = new ListReservationAdapter(new ArrayList<MyReservation>(), getActivity());
        mGridLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mGridLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(listReservation);
    }

    private void getReservationFromApi() {
        bindRecycler();
        mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        String auth = AUTH_BEARER + Shared_Preferences.getAuth(getContext());
        Call<Reservatios> myReservationCall = mApiInterface.myReservation(auth);
        myReservationCall.enqueue(new Callback<Reservatios>() {
            @Override
            public void onResponse(Call<Reservatios> call, Response<Reservatios> response) {
                if (response.isSuccessful()) {
                    if (response.body().getReservations() != null) {
                        listReservation.setListServices(response.body().getReservations());
                    } else {
                        Toasty.info(getContext(), "You Don't Have Reservation yet", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Reservatios> call, Throwable t) {
                Log.d("urlReservation", "" + t.getMessage());
            }
        });

    }
}
