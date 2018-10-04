package com.example.eslam.mywedding.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eslam.mywedding.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private static final String LOAD_IMAGE_URL = "http://wed.filerolesys.com/";
    private final View mWindow;
    private Context mContext;
    private String Mmarkers;

    public CustomInfoWindowAdapter(Context context, String markers) {
        mContext = context;
        Mmarkers = markers;
        mWindow = LayoutInflater.from(context).inflate(R.layout.custom_info_window, null);
    }

    @BindView(R.id.title)
    TextView tvTitle;

    @BindView(R.id.snippet)
    TextView tvSnippet;
    @BindView(R.id.im)
    ImageView imageView;

    private void rendowWindowText(Marker marker, View view) {

        ButterKnife.bind(this, view);

        String title = marker.getTitle();

        tvTitle.setText(title);

        String snippet = marker.getSnippet();

        tvSnippet.setText(snippet);

        Picasso.get().load(LOAD_IMAGE_URL + Mmarkers).into(imageView);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        rendowWindowText(marker, mWindow);
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        rendowWindowText(marker, mWindow);
        return mWindow;
    }
}