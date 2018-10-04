package com.example.eslam.mywedding.Activites;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.eslam.mywedding.Adapters.List_Rests_Adapters;
import com.example.eslam.mywedding.HelperMethod.ConnectionDetector;
import com.example.eslam.mywedding.Models.Rests.Rests;
import com.example.eslam.mywedding.R;
import com.example.eslam.mywedding.ViewModels.ViewRestingModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class RestsActivity extends AppCompatActivity implements List_Rests_Adapters.RestsAdapterOnClick {
    @BindView(R.id.rv_list_rests)
    RecyclerView mRvRests;
    @BindView(R.id.progressBar_rest)
    ProgressBar progressBar;
    private List_Rests_Adapters list_rests_adapters;
    private GridLayoutManager mGridLayoutManager;
    private static final String IMAGE = "image";
    private static final String REST_KEY_NAME = "name";
    private static final String REST_KEY_ADDRESS = "address";
    private static final String REST_KEY_DETAILS = "details";
    private static final String REST_KEY_ID = "id";
    private static final String REST_KEY_PRICE = "price";
    private static final String REST_KEY_PHONE = "phone";
    private static final String REST_KEY_NUMBER = "number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rests);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle(R.string.rests);
        if (ConnectionDetector.isConnectingToInternet(this)) {
            loadListResting();
        }else {
            Toasty.error(this, getString(R.string.error), Toast.LENGTH_LONG).show();
        }

    }

    private void prepareTheRecylcerResting() {
        list_rests_adapters = new List_Rests_Adapters(new ArrayList<Rests>(), this);
        mGridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        mRvRests.setLayoutManager(mGridLayoutManager);
        mRvRests.setAdapter(list_rests_adapters);
    }

    private void loadListResting() {
        prepareTheRecylcerResting();
        progressBar.setVisibility(View.VISIBLE);
        ViewRestingModel restingModel = ViewModelProviders.of(this).get(ViewRestingModel.class);
        restingModel.getResting().observe(this, new Observer<List<Rests>>() {
            @Override
            public void onChanged(@Nullable List<Rests> rests) {
                if (rests != null) {
                    progressBar.setVisibility(View.GONE);
                    list_rests_adapters.setList(rests);

                } else {
                    Toasty.error(RestsActivity.this, "" + R.string.error, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(Rests rests) {
        Intent intent = new Intent(RestsActivity.this, ReservationResting.class);
        intent.putParcelableArrayListExtra(IMAGE, (ArrayList<? extends Parcelable>) rests.getImages());
        intent.putExtra(REST_KEY_NAME, rests.getName());
        intent.putExtra(REST_KEY_ADDRESS, rests.getAddress());
        intent.putExtra(REST_KEY_DETAILS, rests.getDetails());
        intent.putExtra(REST_KEY_NUMBER, rests.getMaxNumber());
        intent.putExtra(REST_KEY_PHONE, rests.getPhoneNumber());
        intent.putExtra(REST_KEY_PRICE, rests.getPrice());
        intent.putExtra(REST_KEY_ID, rests.getId());
        startActivity(intent);
    }
}
