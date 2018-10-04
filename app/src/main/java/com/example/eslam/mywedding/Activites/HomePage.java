package com.example.eslam.mywedding.Activites;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eslam.mywedding.API.ApiClient.ApiClient;
import com.example.eslam.mywedding.API.ApiInterFace.ApiInterface;
import com.example.eslam.mywedding.Fragments.ContactUsFragment;
import com.example.eslam.mywedding.Fragments.GetListHallFragment;
import com.example.eslam.mywedding.Fragments.MyReservationFragment;
import com.example.eslam.mywedding.HelperMethod.ConnectionDetector;
import com.example.eslam.mywedding.HelperMethod.Shared_Preferences;
import com.example.eslam.mywedding.Models.ContryModel.ContryModel;
import com.example.eslam.mywedding.Models.ContryModel.Country_;
import com.example.eslam.mywedding.Models.Logout;
import com.example.eslam.mywedding.Models.ProfileModle;
import com.example.eslam.mywedding.Models.UserModle;
import com.example.eslam.mywedding.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    ActionBarDrawerToggle toggle;
    ActionBar mActionBar;


    private TextView name;
    private CircularImageView profile;

    private static final String LOAD_IMAGE_URL = "http://wed.filerolesys.com/";
    private static final String AUTH_BEARER = "Bearer ";
    private UserModle userModle;
    private ApiInterface mApiInterface;
    private String auth;
    private Fragment mFragment;
    private String mEnglish;
    private String countryName;
    private int country_id;
    private int country;
    private String fname = "";
    private String lname = "";
    private String email = "";
    private String phone = "";
    private String address = "";
    private String image = "";
    private FragmentManager mfragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ButterKnife.bind(this);


        mActionBar = getSupportActionBar();

        setSupportActionBar(toolbar);


        View headerLayout = navigationView.getHeaderView(0);

        name = headerLayout.findViewById(R.id.tv_name_navheader);

        profile = headerLayout.findViewById(R.id.im_photo_navheader);

        mEnglish = Locale.getDefault().getLanguage();

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);


        if (mFragment != null) {
            removeFragment();
        }

        if (ConnectionDetector.isConnectingToInternet(this)) {
            setHomPageHalls();

            getUser();

            getContryFromApi();

        } else {
            Toasty.error(this, getString(R.string.error), Toast.LENGTH_LONG).show();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        setHomPageHalls();

    }

    private void saveUser() {
        if (!fname.isEmpty() &&
                !lname.isEmpty() &&
                !countryName.isEmpty() &&
                !email.isEmpty() &&
                !phone.isEmpty() &&
                !address.isEmpty() &&
                !image.isEmpty() &&
                country > 0) {
            userModle = new UserModle(fname,
                    lname,
                    countryName,
                    country,
                    email,
                    phone,
                    address,
                    image);
            Shared_Preferences.SaveUser(HomePage.this, userModle);
        } else {
            Toast.makeText(this, "" + R.string.error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
        }
    }

    private void getUser() {

        auth = AUTH_BEARER + Shared_Preferences.getAuth(this);

        mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<ProfileModle> profileModleCall = mApiInterface.getProfilea(auth);

        profileModleCall.enqueue(new Callback<ProfileModle>() {
            @Override
            public void onResponse(Call<ProfileModle> call, Response<ProfileModle> response) {
                if (response.isSuccessful()) {
                    name.setText(response.body().getProfile().getFname());

                    Picasso.get().load(LOAD_IMAGE_URL.concat(response.body().getProfile().getImage()))
                            .into(profile);
                    fname = response.body().getProfile().getFname();
                    lname = response.body().getProfile().getLname();
                    email = response.body().getProfile().getEmail();
                    address = response.body().getProfile().getAddress();
                    phone = response.body().getProfile().getPhone();
                    image = response.body().getProfile().getImage();
                    country = Integer.parseInt(response.body().getProfile().getCountry());

                } else {
                    try {
                        Thread.sleep(5000);
                        Toasty.info(HomePage.this, getResources().getString(R.string.login_again), Toast.LENGTH_LONG).show();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    logOut();
                }
            }

            @Override
            public void onFailure(Call<ProfileModle> call, Throwable t) {
                Log.v("url", "" + t.toString());

            }
        });
    }

    private void getContryFromApi() {
        mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ContryModel> list = mApiInterface.getCountryList();
        list.enqueue(new Callback<ContryModel>() {
            @Override
            public void onResponse(Call<ContryModel> call, Response<ContryModel> response) {

                for (Country_ country_ : response.body().getCountries()) {
                    country_id = country_.getId();
                    if (country_id == country) {
                        if (mEnglish.equals("en")) {
                            countryName = country_.getName();
                        } else {
                            countryName = country_.getArName();
                        }
                        saveUser();
                    }
                }
            }

            @Override
            public void onFailure(Call<ContryModel> call, Throwable t) {
                Log.v("url", "" + t.getMessage());

            }
        });

    }

    private void logOut() {
        final AlertDialog alertDialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage(R.string.log_out)
                .setCancelable(false)
                .build();
        alertDialog.show();
        mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Logout> logoutCall = mApiInterface.logOut();
        logoutCall.enqueue(new Callback<Logout>() {
            @Override
            public void onResponse(Call<Logout> call, Response<Logout> response) {
                alertDialog.dismiss();
                Toasty.info(HomePage.this, "" + response.body().getMessage(), Toast.LENGTH_LONG).show();
                Shared_Preferences.Logout(HomePage.this);
                startActivity(new Intent(HomePage.this, MainActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<Logout> call, Throwable t) {

            }
        });
    }

    private void setHomPageHalls() {
        mFragment = new GetListHallFragment();
        setFragment(mFragment);
    }

    private void setContactUsPage() {
        mFragment = new ContactUsFragment();
        setFragment(mFragment);
    }

    private void setReservtaionPage() {
        mFragment = new MyReservationFragment();
        setFragment(mFragment);
    }

    private void removeFragment() {
        getSupportFragmentManager().beginTransaction().remove(mFragment).commit();
    }

    private void setFragment(Fragment mFragment) {
        mfragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mfragmentManager.beginTransaction();
        transaction.replace(R.id.frame_content, mFragment);
        transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
    }

    private void shareIt() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Wedding App");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Now You Can a hall reservation and see the nearest hall, click here to visit https://wed.filerolesys.com ");
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            startActivity(new Intent(HomePage.this, MainActivity.class));
            finish();
        } else if (id == R.id.nav_account) {
            if (mFragment != null) {
                removeFragment();
            }
            startActivity(new Intent(HomePage.this, Profile.class));
        } else if (id == R.id.nav_reserveaton) {
            if (mFragment != null) {
                removeFragment();
            }
            setReservtaionPage();
        } else if (id == R.id.nav_share) {
            shareIt();
        } else if (id == R.id.nav_contact_us) {
            if (mFragment != null) {
                removeFragment();
            }
            setContactUsPage();

        } else if (id == R.id.nav_log_out) {
            if (mFragment != null) {
                removeFragment();
            }
            logOut();
        } else if (id == R.id.nav_service) {
            startActivity(new Intent(HomePage.this, ServicesActivity.class));
        } else if (id == R.id.nav_rests) {
            startActivity(new Intent(HomePage.this, RestsActivity.class));
        } else if (id == R.id.nav_halls) {
            if (mFragment != null) {
                removeFragment();
            }
            finish();
            startActivity(new Intent(HomePage.this, HomePage.class));
        } else if (id == R.id.nav_fav) {
            if (mFragment != null) {
                removeFragment();
            }
            startActivity(new Intent(HomePage.this, FavoriteHalls.class));
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
