package com.example.eslam.mywedding.Adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.eslam.mywedding.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerAdapters extends PagerAdapter {
    Activity activity;
    ArrayList<String> image;
    LayoutInflater layoutInflater;
    private static final String LOAD_IMAGE_URL = "http://wed.filerolesys.com/";

    public ViewPagerAdapters(Activity activity, ArrayList<String> image) {
        this.activity = activity;
        this.image = image;
    }

    @Override
    public int getCount() {
        return image.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        (container).removeView((View) object);
    }

    @BindView(R.id.ima_view_pager)
    ImageView imageView;


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.image_view_pager, container, false);
        ButterKnife.bind(this, view);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        imageView.setMinimumHeight(height);
        imageView.setMinimumWidth(width);
        try {
            Picasso.get().load(LOAD_IMAGE_URL + image.get(position)).into(imageView);
        } catch (Exception e) {
            Log.v("pager", "" + e.getMessage());
        }
        container.addView(view);
        return view;
    }

    public void setImage(ArrayList<String> image) {
        this.image = image;
        notifyDataSetChanged();
    }
}
