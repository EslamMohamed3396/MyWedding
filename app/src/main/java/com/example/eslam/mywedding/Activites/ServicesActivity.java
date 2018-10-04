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

import com.example.eslam.mywedding.Adapters.List_Services_Adapter;
import com.example.eslam.mywedding.HelperMethod.ConnectionDetector;
import com.example.eslam.mywedding.Models.Services.Servcy;
import com.example.eslam.mywedding.R;
import com.example.eslam.mywedding.ViewModels.ViewServicesModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class ServicesActivity extends AppCompatActivity implements List_Services_Adapter.ServicesAdapterOnClick {
    private List_Services_Adapter listServicesAdapter;
    @BindView(R.id.rv_list_service)
    RecyclerView mRvServices;
    @BindView(R.id.progressBar_service)
    ProgressBar progressBar;
    private GridLayoutManager mGridLayoutManager;
    private static final String IMAGE = "image";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DETAILS = "details";
    private static final String PRICE = "price";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle(R.string.the_services);
        if (ConnectionDetector.isConnectingToInternet(this)) {
            loadListServices();
        }else {
            Toasty.error(this, getString(R.string.error), Toast.LENGTH_LONG).show();
        }
    }

    private void prepareTheRecylcerServices() {
        listServicesAdapter = new List_Services_Adapter(new ArrayList<Servcy>(), this);
        mGridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        mRvServices.setLayoutManager(mGridLayoutManager);
        mRvServices.setAdapter(listServicesAdapter);
    }

    private void loadListServices() {
        prepareTheRecylcerServices();
        progressBar.setVisibility(View.VISIBLE);
        ViewServicesModel servicesModel = ViewModelProviders.of(this).get(ViewServicesModel.class);
        servicesModel.getService().observe(this, new Observer<List<Servcy>>() {
            @Override
            public void onChanged(@Nullable List<Servcy> servcyList) {
                if (servcyList != null) {
                    progressBar.setVisibility(View.GONE);
                    listServicesAdapter.setList(servcyList);

                } else {
                    Toasty.error(ServicesActivity.this, "" + R.string.error, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(Servcy servcy) {
        Intent intent = new Intent(ServicesActivity.this, DetailsServices.class);
        intent.putParcelableArrayListExtra(IMAGE, (ArrayList<? extends Parcelable>) servcy.getImages());
        intent.putExtra(ID, servcy.getId());
        intent.putExtra(NAME, servcy.getName());
        intent.putExtra(DETAILS, servcy.getDetailsAr());
        intent.putExtra(PRICE, servcy.getPrice());
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
