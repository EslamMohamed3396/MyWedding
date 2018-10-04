package com.example.eslam.mywedding.Activites;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.eslam.mywedding.Adapters.ListFavoriteHalls;
import com.example.eslam.mywedding.Models.HallsModels.Hall;
import com.example.eslam.mywedding.R;
import com.example.eslam.mywedding.ViewModels.MainFavViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import es.dmoral.toasty.Toasty;

public class FavoriteHalls extends AppCompatActivity implements ListFavoriteHalls.ListFavoriteHallsOnClick {

    @BindView(R.id.rv_fav_halls)
    RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private ListFavoriteHalls listFavoriteHalls;
    private static final String HALL_KEY_ID = "hall_id";
    private static final String HALL_KEY_IMAGE = "hall_image";
    private static final String HALL_KEY_NAME = "hall_name";
    private static final String HALL_KEY_PRICE = "hall_price";
    private static final String HALL_KEY_ADDRESS = "hall_address";
    private static final String HALL_KEY_PHONE = "hall_phone";
    private static final String HALL_KEY_NUMBER = "hall_number";
    private static final String HALL_KEY_DETAILS = "hall_detail";
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_halls);
        ButterKnife.bind(this);
        loadListHalls();
    }

    private void preapareTheRecyclreHalls() {
        listFavoriteHalls = new ListFavoriteHalls(new ArrayList<Hall>(), this);
        mGridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(listFavoriteHalls);
    }

    private void loadListHalls() {

        preapareTheRecyclreHalls();
        final AlertDialog progressDialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage(R.string.loading)
                .setCancelable(false)
                .build();
        progressDialog.show();
        MainFavViewModel mainFavViewModel = ViewModelProviders.of(this).get(MainFavViewModel.class);
        mainFavViewModel.getHall().observe(this, new Observer<List<Hall>>() {
            @Override
            public void onChanged(@Nullable List<Hall> halls) {
                progressDialog.dismiss();
                if (halls != null) {
                    listFavoriteHalls.setList(halls);
                } else {
                    if (mToast != null) {
                        mToast.cancel();
                    }
                    listFavoriteHalls.clear();
                    mToast = Toasty.info(FavoriteHalls.this,getString(R.string.empty), Toast.LENGTH_SHORT);
                    mToast.show();

                }
            }
        });
    }

    @Override
    public void onClick(Hall listHallsAdapters) {
        Intent intent = new Intent(this, DetailsHallsFavorite.class);

        intent.putParcelableArrayListExtra(HALL_KEY_IMAGE, (ArrayList<? extends Parcelable>) listHallsAdapters.getImageModels());
        intent.putExtra(HALL_KEY_NAME, listHallsAdapters.getName());
        intent.putExtra(HALL_KEY_ADDRESS, listHallsAdapters.getAddress());
        intent.putExtra(HALL_KEY_DETAILS, listHallsAdapters.getDetails());
        intent.putExtra(HALL_KEY_NUMBER, listHallsAdapters.getMaxNumber());
        intent.putExtra(HALL_KEY_PHONE, listHallsAdapters.getPhoneNumber());
        intent.putExtra(HALL_KEY_PRICE, listHallsAdapters.getPrice());
        intent.putExtra(HALL_KEY_ID, listHallsAdapters.getId());
        startActivity(intent);
    }
}
