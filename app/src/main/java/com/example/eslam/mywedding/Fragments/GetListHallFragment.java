package com.example.eslam.mywedding.Fragments;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eslam.mywedding.Activites.DetailsHallsActivity;
import com.example.eslam.mywedding.Adapters.List_Halls_Adapters;
import com.example.eslam.mywedding.HelperMethod.ConnectionDetector;
import com.example.eslam.mywedding.Models.HallsModels.Hall;
import com.example.eslam.mywedding.R;
import com.example.eslam.mywedding.Service.MyJopService;
import com.example.eslam.mywedding.ViewModels.ViewHallsModel;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dmax.dialog.SpotsDialog;
import es.dmoral.toasty.Toasty;

public class GetListHallFragment extends Fragment implements List_Halls_Adapters.HallsAdapterOnClick {
    View rootView;
    @BindView(R.id.rv_list_halls)
    RecyclerView mRvHalls;

    private Unbinder unbinder;

    private List_Halls_Adapters listHallsAdapters;


    private GridLayoutManager mGridLayoutManager;

    private static final String HALL_KEY_ID = "hall_id";
    private static final String HALL_KEY_IMAGE = "hall_image";
    private static final String HALL_KEY_NAME = "hall_name";
    private static final String HALL_KEY_PRICE = "hall_price";
    private static final String HALL_KEY_ADDRESS = "hall_address";
    private static final String HALL_KEY_PHONE = "hall_phone";
    private static final String HALL_KEY_NUMBER = "hall_number";
    private static final String HALL_KEY_DETAILS = "hall_detail";

    private FirebaseJobDispatcher mFirebaseJobDispatcher;
    private static final String MY_TAG = "my_job_tag";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.display_content_home_, container, false);

        mFirebaseJobDispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(getContext()));
        unbinder = ButterKnife.bind(this, rootView);
        preapareTheRecyclreHalls();
        getService();
        if (ConnectionDetector.isConnectingToInternet(getContext())) {
            loadListHalls();

        } else {
            Toasty.error(getContext(), getString(R.string.error), Toast.LENGTH_LONG).show();
        }

        return rootView;
    }

    private void getService() {
        Job job = mFirebaseJobDispatcher.newJobBuilder()
                .setService(MyJopService.class)
                .setLifetime(Lifetime.FOREVER)
                .setTag(MY_TAG)
                .setTrigger(Trigger.executionWindow(10, 12))
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setReplaceCurrent(false).build();
        mFirebaseJobDispatcher.mustSchedule(job);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void preapareTheRecyclreHalls() {
        listHallsAdapters = new List_Halls_Adapters(new ArrayList<Hall>(), this);
        mGridLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        mRvHalls.setLayoutManager(mGridLayoutManager);
        mRvHalls.setAdapter(listHallsAdapters);
    }



    private void loadListHalls() {
        final AlertDialog progressDialog = new SpotsDialog.Builder()
                .setContext(getContext())
                .setMessage(R.string.loading)
                .setCancelable(false)
                .build();
        progressDialog.show();
        ViewHallsModel model = ViewModelProviders.of(this).get(ViewHallsModel.class);
        model.getHalls().observe(getActivity(), new Observer<List<Hall>>() {
            @Override
            public void onChanged(@Nullable List<Hall> halls) {
                progressDialog.dismiss();
                if (halls != null) {
                    listHallsAdapters.clear();
                    listHallsAdapters.setList(halls);
                } else {
                    Toast.makeText(getContext(), "", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(Hall listHallsAdapters) {
        Intent intent = new Intent(getContext(), DetailsHallsActivity.class);

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
