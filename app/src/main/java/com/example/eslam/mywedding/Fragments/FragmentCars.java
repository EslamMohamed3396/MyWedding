package com.example.eslam.mywedding.Fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eslam.mywedding.API.ApiClient.ApiClient;
import com.example.eslam.mywedding.API.ApiInterFace.ApiInterface;
import com.example.eslam.mywedding.Models.CarsDetails.CarDetials;
import com.example.eslam.mywedding.Models.CarsDetails.CarsDeepDetails;
import com.example.eslam.mywedding.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCars extends DialogFragment {


    private ApiInterface mApiInterface;
    @BindView(R.id.tvCarName)
    TextView carName;
    @BindView(R.id.tvCarPrice)
    TextView price;
    @BindView(R.id.im_Car)
    ImageView imCar;
    private static final String LOAD_IMAGE_URL = "http://wed.filerolesys.com/";
    private CarsDeepDetails carsDeepDetails;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.list_cars_prepration, container, false);

        unbinder=ButterKnife.bind(this, rootView);


        String id = getArguments().getString("id");


        this.getDialog().setTitle("Cars");

        loadHalls(id);

        return rootView;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    private void loadHalls(String id) {

        mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<CarDetials> listCall = mApiInterface.getCarsbyId(id);
        listCall.enqueue(new Callback<CarDetials>() {
            @Override
            public void onResponse(Call<CarDetials> call, Response<CarDetials> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCars().size() > 0) {
                        carsDeepDetails = response.body().getCars().get(0);
                        carName.setText(carsDeepDetails.getName());
                        price.setText(String.valueOf(carsDeepDetails.getPrice()));
                        String image = carsDeepDetails.getImages().get(0).getImageUrl();
                        Picasso.get().load(LOAD_IMAGE_URL + image).into(imCar);
                    } else {
                        Toasty.info(getActivity(), "This Car is not allow in this time ", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.v("response", "" + response.toString());
                }
            }

            @Override
            public void onFailure(Call<CarDetials> call, Throwable t) {
                Log.v("url", "" + t.toString());

            }
        });
    }
}
