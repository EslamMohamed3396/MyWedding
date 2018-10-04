package com.example.eslam.mywedding.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eslam.mywedding.Adapters.ViewPagerAdapters;
import com.example.eslam.mywedding.DataBases.AppExecutor;
import com.example.eslam.mywedding.DataBases.SetupDataBase;
import com.example.eslam.mywedding.Models.ImageModel;
import com.example.eslam.mywedding.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class DetailsHallsFavorite extends AppCompatActivity {
    private ViewPagerAdapters mViewPagerAdapters;
    @BindView(R.id.view_page_fav)
    ViewPager mViewPager;
    @BindView(R.id.tvGuestMaxNamber_fav)
    TextView mMax;
    @BindView(R.id.tvPrice_fav)
    TextView mPrice;
    @BindView(R.id.tvPhone_fav)
    TextView mPhone;
    @BindView(R.id.tvAdress_fav)
    TextView mAddress;
    @BindView(R.id.tvDescription_fav)
    TextView mDetails;
    @BindView(R.id.SliderDots_fav)
    LinearLayout sliderDots;
    @BindView(R.id.toolbar_halls_fav)
    Toolbar toolbar;
    @BindView(R.id.fb_favorit_fav)
    FloatingActionButton mFloatingActionButton;
    private int dotCounts;
    private ImageView[] dots;
    private ArrayList<String> image = new ArrayList<>();
    private Intent intent;
    private List<ImageModel> imageModels;
    private String name;
    private String address;
    private String details;
    private int number;
    private String phone;
    private int price;
    private int hallId;

    private static final String HALL_KEY_ID = "hall_id";
    private static final String HALL_KEY_IMAGE = "hall_image";
    private static final String HALL_KEY_NAME = "hall_name";
    private static final String HALL_KEY_PRICE = "hall_price";
    private static final String HALL_KEY_ADDRESS = "hall_address";
    private static final String HALL_KEY_PHONE = "hall_phone";
    private static final String HALL_KEY_NUMBER = "hall_number";
    private static final String HALL_KEY_DETAILS = "hall_detail";
    private SetupDataBase mDataBaseHalls;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_halls_favorite);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        intent = getIntent();
        mViewPagerAdapters = new ViewPagerAdapters(this, new ArrayList<String>());
        mViewPager.setAdapter(mViewPagerAdapters);
        getIntentImage();
        getIntentHall();
        mDataBaseHalls = mDataBaseHalls.getINSTANCE(getApplicationContext());
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delteMovie();
                finish();
                if (mToast != null) {
                    mToast.cancel();
                }
                mToast = Toasty.success(DetailsHallsFavorite.this, getString(R.string.delete_fav), Toast.LENGTH_SHORT);
                mToast.show();

            }
        });
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new myTaskTimer(), 2000, 4000);
    }

    private void getIntentImage() {
        imageModels = intent.getParcelableArrayListExtra(HALL_KEY_IMAGE);
        for (int i = 0; i < imageModels.size(); i++) {
            image.add(imageModels.get(i).getImageUrl());
        }
        mViewPagerAdapters.setImage(image);
        dotCounts = mViewPagerAdapters.getCount();
        dots = new ImageView[dotCounts];
        for (int i = 0; i < dotCounts; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            sliderDots.addView(dots[i], params);
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotCounts; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void getIntentHall() {
        name = intent.getStringExtra(HALL_KEY_NAME);
        address = intent.getStringExtra(HALL_KEY_ADDRESS);
        details = intent.getStringExtra(HALL_KEY_DETAILS);
        number = intent.getIntExtra(HALL_KEY_NUMBER, 0);
        phone = intent.getStringExtra(HALL_KEY_PHONE);
        price = intent.getIntExtra(HALL_KEY_PRICE, 0);
        hallId = intent.getIntExtra(HALL_KEY_ID, 0);
        getSupportActionBar().setTitle("\t \t " + name);
        mMax.setText(String.valueOf(number));
        mPrice.setText(String.valueOf(price));
        mPhone.setText(String.valueOf(phone));
        mAddress.setText(String.valueOf(address));
        mDetails.setText(String.valueOf(details));
    }
    private void delteMovie() {
        mFloatingActionButton.setImageResource(R.drawable.ic_favorite_border);

        AppExecutor.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDataBaseHalls.daoHalls().deleteHallById(hallId);
            }
        });

    }
    private class myTaskTimer extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < dotCounts; i++) {
                        if (mViewPager.getCurrentItem() == i) {
                            mViewPager.setCurrentItem(++i);
                        }
                    }
                }
            });
        }
    }

}
