package com.example.eslam.mywedding.Activites;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.eslam.mywedding.API.ApiClient.ApiClient;
import com.example.eslam.mywedding.API.ApiInterFace.ApiInterface;
import com.example.eslam.mywedding.Adapters.ListHotelsInHallsAdapter;
import com.example.eslam.mywedding.Adapters.ListKitchenInHallsAdapter;
import com.example.eslam.mywedding.Adapters.ListServicesInHallsAdapter;
import com.example.eslam.mywedding.Adapters.ViewPagerAdapters;
import com.example.eslam.mywedding.HelperMethod.ConnectionDetector;
import com.example.eslam.mywedding.HelperMethod.HelperMetodsValidation;
import com.example.eslam.mywedding.HelperMethod.Shared_Preferences;
import com.example.eslam.mywedding.Models.CarPreparation.CarPreparation;
import com.example.eslam.mywedding.Models.CarPreparation.Preparation;
import com.example.eslam.mywedding.Models.Cars.Brand;
import com.example.eslam.mywedding.Models.Cars.Cars;
import com.example.eslam.mywedding.Models.Celebration.Celebration;
import com.example.eslam.mywedding.Models.Celebration.Celebrations;
import com.example.eslam.mywedding.Models.Hotels.Hotel;
import com.example.eslam.mywedding.Models.Hotels.Hotels;
import com.example.eslam.mywedding.Models.ImageModel;
import com.example.eslam.mywedding.Models.Kitchen.CountryDatum;
import com.example.eslam.mywedding.Models.Kitchen.Kitchen;
import com.example.eslam.mywedding.Models.Kitchen.KitchenItem;
import com.example.eslam.mywedding.Models.Services.ListServciesModel;
import com.example.eslam.mywedding.Models.Services.Servcy;
import com.example.eslam.mywedding.Models.UpdateProfileModel;
import com.example.eslam.mywedding.Models.UserModle;
import com.example.eslam.mywedding.R;
import com.rm.rmswitch.RMSwitch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationResting extends AppCompatActivity {
    private ViewPagerAdapters mViewPagerAdapters;
    @BindView(R.id.view_page_resting)
    ViewPager mViewPager;
    @BindView(R.id.tvGuestMaxNamber_resting)
    TextView mMax;
    @BindView(R.id.tvPrice_resting)
    TextView mPrice;
    @BindView(R.id.tvPhone_resting)
    TextView mPhone;
    @BindView(R.id.tvAdress_resting)
    TextView mAddress;
    @BindView(R.id.tvDescription_resting)
    TextView mDetails;
    @BindView(R.id.spinner_kind_celebration_resting)
    Spinner mSpinnerKindCelebration;
    @BindView(R.id.edStartDate_resting)
    EditText mStartDate;
    @BindView(R.id.edStartTime_resting)
    EditText mStartTime;
    @BindView(R.id.edGuestIncerment_resting)
    Button mIncerament;
    @BindView(R.id.edGuestDecerment_resting)
    Button mDecerament;
    @BindView(R.id.edGuest_resting)
    EditText mGuest;
    @BindView(R.id.SliderDots_resting)
    LinearLayout sliderDots;
    @BindView(R.id.spinnerCars_resting)
    Spinner spCars;
    @BindView(R.id.switchCars_resting)
    RMSwitch mSwitchCar;
    @BindView(R.id.spinnerCarsPrepration_resting)
    Spinner spCarsPrepration;
    @BindView(R.id.switchCarsPreperation_resting)
    RMSwitch switchCarsPreperation;
    @BindView(R.id.rvHallParty_resting)
    RecyclerView mRecyclerParty;
    @BindView(R.id.switchParty_resting)
    RMSwitch switchParty;
    @BindView(R.id.radioButtonsGroup_resting)
    RadioGroup radioGroup;
    @BindView(R.id.rvHotels_resting)
    RecyclerView mRecyclerHotels;
    ListHotelsInHallsAdapter listHotelInHallsAdapter;
    @BindView(R.id.switchHotles_resting)
    RMSwitch switchHotel;
    List<Hotel> HotelList;
    @BindView(R.id.spinner_hotels_stars_resting)
    Spinner spHotelStars;
    @BindView(R.id.toolbar_halls_resting)
    Toolbar toolbar;
    @BindView(R.id.rbMale_resting)
    RadioButton rbMale;
    @BindView(R.id.rbFeMale_resting)
    RadioButton rbFemale;
    @BindView(R.id.rbBoth_resting)
    RadioButton rbBoth;
    @BindView(R.id.btMakeBook_resting)
    Button makeBook;
    @BindView(R.id.rvKitchen_resting)
    RecyclerView mRecyclerFood;
    ListKitchenInHallsAdapter listKitchenInHallsAdapter;
    @BindView(R.id.switchKitchen_resting)
    RMSwitch switchKitchen;
    List<KitchenItem> itemList;
    @BindView(R.id.spinner_kind_kitchen_resting)
    Spinner spKindKitchen;
    @BindView(R.id.spinner_kind_of_food_resting)
    Spinner spKindkindOfFood;
    @BindView(R.id.textstars_resting)
    TextView startText;
    private String mHotelSelected;
    private String[] hotelsStars;
    List<Hotel> hotels = new ArrayList<>();
    private int k;

    private int dotCounts;
    private ImageView[] dots;

    private static int inc;
    private static int dec;

    private int chooseTheGuest = 1;

    private static final String IMAGE = "image";
    private static final String REST_KEY_NAME = "name";
    private static final String REST_KEY_ADDRESS = "address";
    private static final String REST_KEY_DETAILS = "details";
    private static final String REST_KEY_ID = "id";
    private static final String REST_KEY_PRICE = "price";
    private static final String REST_KEY_PHONE = "phone";
    private static final String REST_KEY_NUMBER = "number";
    private static final String AUTH_BEARER = "Bearer ";

    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;

    private ApiInterface mApiInterface;

    ListServicesInHallsAdapter listServicesInHallsAdapter;
    List<Servcy> servcyList = new ArrayList<>();
    private boolean getCheckedServices;
    List<Preparation> preparations;
    private int mPreparationsSelected = 0;

    List<Celebration> celebrationsModels;
    private int mCelebrationsSelected;

    List<Brand> brandModels;
    private int mCarsSelected = 0;
    private String serviceId;
    private String kitchenId;
    private Intent intent;

    private String countryUser;
    private int countryId;
    private UserModle userModle;
    private String ContryHotelArName;
    private String ContryHotelEnName;
    private boolean getCheckedHotel;
    private int hotelId = 0;
    private String daysHotel = "0";
    private ArrayList<String> image = new ArrayList<>();


    private int mKindofKitchenSelected[] = {1, 2, 3};
    private int mSelectedKitchen;
    List<KitchenItem> kitchenItems = new ArrayList<>();
    private String kindselected;
    private List<String> foodType;

    AdapterView.OnItemSelectedListener kitchenSelectedListener =
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    mSelectedKitchen = mKindofKitchenSelected[position];
                    getlistFoodsFromApi();
                    Log.v("selected", "" + mSelectedKitchen);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            };
    AdapterView.OnItemSelectedListener onKindFoodSelectedListener =
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    kindselected = foodType.get(position);
                    getlistFoodsFromApi();
                    Log.v("kindselecteds3", "action happen " + kindselected);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            };


    AdapterView.OnItemSelectedListener onCelebrationSelectedListener =
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    mCelebrationsSelected = celebrationsModels.get(position).getId();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            };

    AdapterView.OnItemSelectedListener carOnItemSelectedListener =
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    mCarsSelected = brandModels.get(i).getId();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            };

    AdapterView.OnItemSelectedListener onHotelSelectedListener =
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    mHotelSelected = hotelsStars[position];
                    getlistHotelsFromApi();
                    Log.v("selected", "" + mHotelSelected);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            };

    AdapterView.OnItemSelectedListener onPreorationCarSelectedListener =
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    mPreparationsSelected = preparations.get(position).getId();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_resting);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent = getIntent();

        userModle = Shared_Preferences.getUser(this);

        countryUser = userModle.getCountry();
        countryId = userModle.getCountryId();
        mViewPagerAdapters = new ViewPagerAdapters(this, new ArrayList<String>());

        mViewPager.setAdapter(mViewPagerAdapters);

        bindRecyclerParty();
        getIntentImage();
        getIntentRest();
        startDate();
        startTime();
        setmIncerament();
        setmDecerament();
        switchCar();
        SwitchCarsPreperation();
        bindRecyclerHotels();
        prepareRecyclerFood();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new myTaskTimer(), 2000, 4000);

        mSpinnerKindCelebration.setOnItemSelectedListener(onCelebrationSelectedListener);

        spCars.setOnItemSelectedListener(carOnItemSelectedListener);

        spCarsPrepration.setOnItemSelectedListener(onPreorationCarSelectedListener);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rbMale:
                        chooseTheGuest = 1;
                        break;
                    case R.id.rbFeMale:
                        chooseTheGuest = 2;
                        break;
                    case R.id.rbBoth:
                        chooseTheGuest = 3;
                        break;
                }
            }
        });

        makeBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ConnectionDetector.isConnectingToInternet(ReservationResting.this)) {
                    validateTexet();
                } else {
                    Toasty.error(ReservationResting.this, getString(R.string.error), Toast.LENGTH_LONG).show();
                }
            }
        });

        new FillSpinnerTaskCelebrations().execute();

    }

    private void validateTexet() {
        if (HelperMetodsValidation.ValidBooking(mStartDate, this)
                && HelperMetodsValidation.ValidBooking(mStartTime, this)
                && !mGuest.getText().toString().equals("0")) {
            makeReservation();
        }

    }

    private void getServices() {
        List<String> requestServices = new ArrayList<>();
        if (servcyList.size() > 0) {
            for (Servcy servcy : servcyList) {
                getCheckedServices = servcy.getChecked();
                if (getCheckedServices) {
                    requestServices.add(String.valueOf(servcy.getId()));

                }
            }
            if (requestServices != null && requestServices.size() > 0) {
                String[] temp = requestServices.toArray(new String[0]);
                serviceId = Arrays.toString(temp);
            }
        }

    }

    private void getKitchen() {
        List<String> requestKitchen = new ArrayList<>();

        if (kitchenItems.size() > 0) {
            for (KitchenItem kitchenItem : kitchenItems) {
                getCheckedServices = kitchenItem.getChecked();
                if (getCheckedServices) {
                    requestKitchen.add(String.valueOf(kitchenItem.getId()));
                }
            }
            if (requestKitchen != null && requestKitchen.size() > 0) {
                String[] temp = requestKitchen.toArray(new String[0]);
                kitchenId = Arrays.toString(temp);
            }
        }

    }

    private void prepareRecyclerFood() {
        listKitchenInHallsAdapter = new ListKitchenInHallsAdapter(new ArrayList<KitchenItem>(), "", this);
        mRecyclerFood.setLayoutManager(new LinearLayoutManager(ReservationResting.this));
        mRecyclerFood.setAdapter(listKitchenInHallsAdapter);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(mRecyclerFood.getContext(),
                        new LinearLayoutManager(ReservationResting.this).getOrientation());
        mRecyclerFood.addItemDecoration(dividerItemDecoration);


        switchKitchen.addSwitchObserver(new RMSwitch.RMSwitchObserver() {
            @Override
            public void onCheckStateChange(RMSwitch switchView, boolean isChecked) {
                if (isChecked) {
                    mRecyclerFood.setVisibility(View.VISIBLE);
                    spKindKitchen.setVisibility(View.VISIBLE);
                    fillTheSpinnerKindOfKithcen();
                    fillTheSpinneKindFood();

                } else {
                    mRecyclerFood.setVisibility(View.GONE);
                    spKindKitchen.setVisibility(View.GONE);
                    spKindkindOfFood.setVisibility(View.GONE);
                }
            }
        });

        spKindKitchen.setOnItemSelectedListener(kitchenSelectedListener);

        spKindkindOfFood.setOnItemSelectedListener(onKindFoodSelectedListener);

    }

    private void getlistFoodsFromApi() {
        kitchenItems = new ArrayList<>();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Kitchen> kitchens = apiInterface.getKitchens();
        kitchens.enqueue(new Callback<Kitchen>() {
            @Override
            public void onResponse(Call<Kitchen> call, Response<Kitchen> response) {
                if (response.isSuccessful()) {
                    listKitchenInHallsAdapter.clear();
                    itemList = response.body().getKitchenItems();
                    if (itemList.size() > 0) {
                        for (int i = 0; i < itemList.size(); i++) {
                            if (itemList.get(i).getType() == mSelectedKitchen || mSelectedKitchen == 3) {
                                if (itemList.get(i).getItemType().equals(kindselected)) {
                                    for (CountryDatum countryDatum : itemList.get(i).getCountryData())
                                        if (countryDatum.getName().contains(countryUser) || countryDatum.getArName().contains(countryUser)) {
                                            kitchenItems.add(itemList.get(i));
                                        }
                                }
                            }
                        }
                    }
                    listKitchenInHallsAdapter.setListServices(kitchenItems, kindselected);
                } else {
                    Toast.makeText(ReservationResting.this, "No Kitchen", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Kitchen> call, Throwable t) {
                Log.v("url", "" + t.getMessage());
            }
        });
    }

    private void getHotelsDays() {
        if (hotels.size() > 0) {
            for (Hotel hotel : hotels) {
                getCheckedHotel = hotel.getChecked();
                if (getCheckedHotel) {
                    hotelId = hotel.getId();
                    daysHotel = hotel.getEditTextValue();
                }
            }
        }
    }

    public void fillTheSpinneKindFood() {
        spKindkindOfFood.setVisibility(View.VISIBLE);
        foodType = new ArrayList<>();
        foodType.add("لحوم");
        foodType.add("طيور");
        foodType.add("اسماك");
        foodType.add("حلويات");
        foodType.add("معجنات");
        foodType.add("سلطات");
        foodType.add("فاكهه");
        foodType.add("عصائر");
        foodType.add("مشروبات");
        foodType.add("مثلجات");
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, foodType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKindkindOfFood.setAdapter(adapter);
    }

    public void fillTheSpinnerKindOfKithcen() {
        String[] KitchenKind = new String[]{
                ReservationResting.this.getResources().getString(R.string.food_east),
                ReservationResting.this.getResources().getString(R.string.food_western),
                ReservationResting.this.getResources().getString(R.string.open_buffet)};
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, KitchenKind);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKindKitchen.setAdapter(adapter);
    }

    public void fillTheSpinnerStars() {
        spHotelStars.setVisibility(View.VISIBLE);
        hotelsStars = new String[]{"1", "2", "3", "4", "5", "6", "7"};
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, hotelsStars);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spHotelStars.setAdapter(adapter);
    }

    private void bindRecyclerHotels() {
        listHotelInHallsAdapter = new ListHotelsInHallsAdapter(new ArrayList<Hotel>(), 0, this);
        mRecyclerHotels.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerHotels.setAdapter(listHotelInHallsAdapter);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(mRecyclerHotels.getContext(),
                        new LinearLayoutManager(this).getOrientation());
        mRecyclerHotels.addItemDecoration(dividerItemDecoration);

        spHotelStars.setOnItemSelectedListener(onHotelSelectedListener);

        switchHotel.addSwitchObserver(new RMSwitch.RMSwitchObserver() {
            @Override
            public void onCheckStateChange(RMSwitch switchView, boolean isChecked) {
                if (isChecked) {
                    startText.setVisibility(View.VISIBLE);
                    fillTheSpinnerStars();
                } else {
                    mRecyclerHotels.setVisibility(View.GONE);
                    spHotelStars.setVisibility(View.GONE);
                }
            }
        });
    }

    private void getlistHotelsFromApi() {
        hotels = new ArrayList<>();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Hotels> servicesCall = apiInterface.getHotels();
        servicesCall.enqueue(new Callback<Hotels>() {
            @Override
            public void onResponse(Call<Hotels> call, Response<Hotels> response) {
                if (response.isSuccessful()) {
                    listHotelInHallsAdapter.clear();
                    HotelList = response.body().getHotels();
                    if (HotelList != null) {
                        mRecyclerHotels.setVisibility(View.VISIBLE);
                        for (int i = 0; i < HotelList.size(); i++) {
                            k = HotelList.get(i).getStartsNumber();
                            ContryHotelArName = HotelList.get(i).getCountry().getArName();
                            ContryHotelEnName = HotelList.get(i).getCountry().getName();
                            if ((k == Integer.parseInt(mHotelSelected) && ContryHotelArName.equals(countryUser))
                                    || (k == Integer.parseInt(mHotelSelected) && ContryHotelEnName.equals(countryUser))) {
                                hotels.add(HotelList.get(i));
                            }
                        }
                        listHotelInHallsAdapter.setListServices(hotels, Integer.parseInt(mHotelSelected));
                    } else {
                        Toast.makeText(ReservationResting.this, "No Hotel Available ", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Hotels> call, Throwable t) {
                Log.v("url", "" + t.getMessage());
            }
        });
    }

    private void bindRecyclerParty() {
        listServicesInHallsAdapter = new ListServicesInHallsAdapter(new ArrayList<Servcy>(), this);
        mRecyclerParty.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerParty.setAdapter(listServicesInHallsAdapter);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(mRecyclerParty.getContext(),
                        new LinearLayoutManager(this).getOrientation());
        mRecyclerParty.addItemDecoration(dividerItemDecoration);

        switchParty.addSwitchObserver(new RMSwitch.RMSwitchObserver() {
            @Override
            public void onCheckStateChange(RMSwitch switchView, boolean isChecked) {
                if (isChecked) {
                    getlistServicesFromApi();
                } else {
                    mRecyclerParty.setVisibility(View.GONE);
                }
            }
        });
    }

    private void getlistServicesFromApi() {
        final List<Servcy> list = new ArrayList<>();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ListServciesModel> servicesCall = apiInterface.getServicesToBooking();
        servicesCall.enqueue(new Callback<ListServciesModel>() {
            @Override
            public void onResponse(Call<ListServciesModel> call, Response<ListServciesModel> response) {
                if (response.isSuccessful()) {
                    servcyList = response.body().getServcies();
                    if (servcyList != null) {
                        mRecyclerParty.setVisibility(View.VISIBLE);
                        for (Servcy servcy : servcyList) {
                            if (servcy.getCountry().getName().equals(countryUser) || servcy.getCountry().getArName().equals(countryUser)) {
                                list.add(servcy);
                            }
                        }
                        listServicesInHallsAdapter.setListServices(list);
                    }
                } else {
                    Toast.makeText(ReservationResting.this, R.string.error, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ListServciesModel> call, Throwable t) {
                Log.v("url", "" + t.getMessage());
            }
        });
    }

    private void switchCar() {
        mSwitchCar.addSwitchObserver(new RMSwitch.RMSwitchObserver() {
            @Override
            public void onCheckStateChange(RMSwitch switchView, boolean isChecked) {
                if (isChecked) {
                    spCars.setVisibility(View.VISIBLE);
                    new FillSpinnerTaskCars().execute();
                } else {
                    spCars.setVisibility(View.GONE);
                }
            }
        });
    }

    private void SwitchCarsPreperation() {
        switchCarsPreperation.addSwitchObserver(new RMSwitch.RMSwitchObserver() {
            @Override
            public void onCheckStateChange(RMSwitch switchView, boolean isChecked) {
                if (isChecked) {
                    spCarsPrepration.setVisibility(View.VISIBLE);
                    new FillSpinnerTaskCarsPreprations().execute();
                } else {
                    spCarsPrepration.setVisibility(View.GONE);
                }
            }
        });
    }

    private void getIntentImage() {
        final List<ImageModel> imageModels = intent.getParcelableArrayListExtra(IMAGE);
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

    private void getIntentRest() {
        String name = intent.getStringExtra(REST_KEY_NAME);
        String address = intent.getStringExtra(REST_KEY_ADDRESS);
        String details = intent.getStringExtra(REST_KEY_DETAILS);
        int number = intent.getIntExtra(REST_KEY_NUMBER, 0);
        String phone = intent.getStringExtra(REST_KEY_PHONE);
        int price = intent.getIntExtra(REST_KEY_PRICE, 0);

        getSupportActionBar().setTitle("\t \t " + name);
        mMax.setText(String.valueOf(number));
        mPrice.setText(String.valueOf(price));
        mPhone.setText(String.valueOf(phone));
        mAddress.setText(String.valueOf(address));
        mDetails.setText(String.valueOf(details));
    }

    private void getCelebrationFromApi() {

        mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Celebrations> list = mApiInterface.getCelebrations();
        list.enqueue(new Callback<Celebrations>() {
            @Override
            public void onResponse(Call<Celebrations> call, Response<Celebrations> response) {
                if (response.isSuccessful()) {
                    celebrationsModels = response.body().getCelebrations();
                    fillTheSpinnerCelebrations();
                } else {
                    Toast.makeText(ReservationResting.this, "Network Error", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Celebrations> call, Throwable t) {
                Log.v("url", "" + t.getMessage());

            }
        });

    }

    public void fillTheSpinnerCelebrations() {
        List<String> stringList = new ArrayList<>();

        for (int i = 0; i < celebrationsModels.size(); i++) {
            stringList.add(celebrationsModels.get(i).getTitle());
        }


        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(ReservationResting.this,
                        android.R.layout.simple_spinner_item, stringList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerKindCelebration.setAdapter(adapter);
    }

    private void getCarsPreparationsFromApi() {

        mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<CarPreparation> list = mApiInterface.getCarsPreparation();
        list.enqueue(new Callback<CarPreparation>() {
            @Override
            public void onResponse(Call<CarPreparation> call, Response<CarPreparation> response) {
                if (response.isSuccessful()) {
                    preparations = response.body().getPreparation();
                    fillTheSpinnerPreparations();
                } else {
                    Toasty.error(ReservationResting.this, "Network Error", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CarPreparation> call, Throwable t) {
                Log.v("url", "" + t.getMessage());

            }
        });

    }

    public void fillTheSpinnerPreparations() {
        List<String> stringList = new ArrayList<>();

        for (int i = 0; i < preparations.size(); i++) {
            stringList.add(preparations.get(i).getName());
        }


        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(ReservationResting.this,
                        android.R.layout.simple_spinner_item, stringList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCarsPrepration.setAdapter(adapter);
    }

    private void getCarsFromApi() {

        mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Cars> list = mApiInterface.getCars();
        list.enqueue(new Callback<Cars>() {
            @Override
            public void onResponse(Call<Cars> call, Response<Cars> response) {
                if (response.isSuccessful()) {
                    brandModels = response.body().getBrands();
                    fillTheSpinnerCars();
                } else {
                    Toast.makeText(ReservationResting.this, "Network Error", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Cars> call, Throwable t) {
                Log.v("url", "" + t.getMessage());

            }
        });

    }

    public void fillTheSpinnerCars() {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < brandModels.size(); i++) {
            stringList.add(brandModels.get(i).getName());
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(ReservationResting.this,
                        android.R.layout.simple_spinner_item, stringList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCars.setAdapter(adapter);
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
                new DatePickerDialog(ReservationResting.this, date, myCalendar
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
                mTimePicker = new TimePickerDialog(ReservationResting.this, new TimePickerDialog.OnTimeSetListener() {
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

    private void setmIncerament() {
        inc = 0;
        mIncerament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inc++;
                mGuest.setText(String.valueOf(inc));
            }
        });
    }

    private void setmDecerament() {
        mDecerament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dec = inc;
                if (dec > 0) {
                    inc--;
                    mGuest.setText(String.valueOf(inc));
                } else {
                    Toast.makeText(ReservationResting.this, "Can't Less 0", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void makeReservation() {
        getHotelsDays();
        getServices();
        getKitchen();
        String date = mStartDate.getText().toString();
        String time = mStartTime.getText().toString();
        Integer restingId = intent.getIntExtra(REST_KEY_ID, 0);
        Integer quantity = Integer.parseInt(mGuest.getText().toString());
        Integer celebration = mCelebrationsSelected;
        Integer car = mCarsSelected;
        Integer carPrepration = mPreparationsSelected;
        Integer cId = countryId;
        Integer guest = chooseTheGuest;
        Integer hId = hotelId;
        Integer hDays = Integer.parseInt(daysHotel);
        String serId = serviceId;
        String kitID = kitchenId;
        String auth = AUTH_BEARER + Shared_Preferences.getAuth(this);
        mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<UpdateProfileModel> message;

        message = mApiInterface.makeReservationResting(auth,
                date,
                time,
                restingId,
                quantity,
                celebration,
                guest,
                serId,
                kitID,
                car,
                carPrepration,
                hId,
                hDays,
                cId);
        final AlertDialog alertDialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage(R.string.booking)
                .setCancelable(false)
                .build();
        alertDialog.show();
        message.enqueue(new Callback<UpdateProfileModel>() {
            @Override
            public void onResponse(Call<UpdateProfileModel> call, Response<UpdateProfileModel> response) {
                alertDialog.dismiss();

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus().equals("1")) {
                            Toasty.success(ReservationResting.this, "" + response.body().getMessage(), Toast.LENGTH_LONG).show();
                        } else {
                            Toasty.error(ReservationResting.this, "" + response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toasty.error(ReservationResting.this, /*getString(R.string.error)*/"" + response, Toast.LENGTH_LONG).show();
                    Log.d("reservation", "" + response);

                }
            }

            @Override
            public void onFailure(Call<UpdateProfileModel> call, Throwable t) {
                Log.d("reservation", "" + t.getCause());
                alertDialog.dismiss();
            }
        });
    }

    private class myTaskTimer extends TimerTask {
        @Override
        public void run() {
            ReservationResting.this.runOnUiThread(new Runnable() {
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

    private class FillSpinnerTaskCelebrations extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            getCelebrationFromApi();
            return null;
        }
    }

    private class FillSpinnerTaskCars extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            getCarsFromApi();
            return null;
        }
    }

    private class FillSpinnerTaskCarsPreprations extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            getCarsPreparationsFromApi();
            return null;
        }
    }

}
