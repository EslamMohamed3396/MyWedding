package com.example.eslam.mywedding.Activites;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.eslam.mywedding.API.ApiClient.ApiClient;
import com.example.eslam.mywedding.API.ApiInterFace.ApiInterface;
import com.example.eslam.mywedding.Adapters.ViewPagerAdapters;
import com.example.eslam.mywedding.HelperMethod.ConnectionDetector;
import com.example.eslam.mywedding.HelperMethod.HelperMetodsValidation;
import com.example.eslam.mywedding.HelperMethod.Shared_Preferences;
import com.example.eslam.mywedding.Models.ImageModel;
import com.example.eslam.mywedding.Models.Services.ImageServices;
import com.example.eslam.mywedding.Models.UpdateProfileModel;
import com.example.eslam.mywedding.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsServices extends AppCompatActivity {
    private static final String IMAGE = "image";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DETAILS = "details";
    private static final String PRICE = "price";
    private static final String AUTH_BEARER = "Bearer ";

    private ViewPagerAdapters mViewPagerAdapters;
    @BindView(R.id.view_page_services)
    ViewPager mViewPager;
    @BindView(R.id.btMakeBook_services)
    Button makeBook;
    @BindView(R.id.edStartDate_services)
    EditText mStartDate;
    @BindView(R.id.edStartTime_services)
    EditText mStartTime;
    @BindView(R.id.SliderDots_services)
    LinearLayout sliderDots;
    @BindView(R.id.toolbar_halls_services)
    Toolbar toolbar;
    @BindView(R.id.tvPrice_services)
    TextView mPrice;
    @BindView(R.id.tvDescription_services)
    TextView mDetails;
    private int dotCounts;
    private ImageView[] dots;
    private Intent intent;
    private ArrayList<String> image = new ArrayList<>();
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;

    private ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_services);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewPagerAdapters = new ViewPagerAdapters(this, new ArrayList<String>());

        mViewPager.setAdapter(mViewPagerAdapters);
        intent = getIntent();
        getIntentImage();
        getIntentServices();
        startDate();
        startTime();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new myTaskTimer(), 2000, 4000);
        makeBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ConnectionDetector.isConnectingToInternet(DetailsServices.this)) {
                    validateTexet();
                } else {
                    Toasty.error(DetailsServices.this, getString(R.string.error), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void validateTexet() {
        if (HelperMetodsValidation.ValidBooking(mStartDate, this)
                && HelperMetodsValidation.ValidBooking(mStartTime, this)) {
            makeReservation();
        }

    }

    private void getIntentImage() {
        final List<ImageServices> imageModels = intent.getParcelableArrayListExtra(IMAGE);
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

    private void getIntentServices() {
        String name = intent.getStringExtra(NAME);
        String details = intent.getStringExtra(DETAILS);
        int price = intent.getIntExtra(PRICE, 0);

        getSupportActionBar().setTitle("\t \t " + name);
        mPrice.setText(String.valueOf(price));
        mDetails.setText(String.valueOf(details));
    }

    private void startDate() {

        myCalendar = Calendar.getInstance();

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        mStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(DetailsServices.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        mStartDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void startTime() {
        mStartTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(DetailsServices.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        mStartTime.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }

    private void makeReservation() {

        String date = mStartDate.getText().toString();
        String time = mStartTime.getText().toString();
        Integer servicesId = intent.getIntExtra(ID, 0);
        String auth = AUTH_BEARER + Shared_Preferences.getAuth(this);
        mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<UpdateProfileModel> message;

        message = mApiInterface.makeReservationServices(auth,
                date,
                time,
                servicesId);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(R.string.loading);
        progressDialog.show();

        message.enqueue(new Callback<UpdateProfileModel>() {
            @Override
            public void onResponse(Call<UpdateProfileModel> call, Response<UpdateProfileModel> response) {
                progressDialog.dismiss();

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus().equals("1")) {
                            Toasty.success(DetailsServices.this, "" + response.body().getMessage(), Toast.LENGTH_LONG).show();
                        } else {
                            Toasty.error(DetailsServices.this, "" + response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toasty.error(DetailsServices.this, /*getString(R.string.error)*/"" + response, Toast.LENGTH_LONG).show();
                    Log.d("reservation", "" + response);

                }
            }

            @Override
            public void onFailure(Call<UpdateProfileModel> call, Throwable t) {
                Log.d("reservation", "" + t.getCause());
                progressDialog.dismiss();

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
