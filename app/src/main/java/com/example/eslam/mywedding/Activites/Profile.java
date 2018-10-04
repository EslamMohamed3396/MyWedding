package com.example.eslam.mywedding.Activites;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eslam.mywedding.API.ApiClient.ApiClient;
import com.example.eslam.mywedding.API.ApiInterFace.ApiInterface;
import com.example.eslam.mywedding.HelperMethod.Shared_Preferences;
import com.example.eslam.mywedding.Models.ContryModel.ContryModel;
import com.example.eslam.mywedding.Models.ContryModel.Country_;
import com.example.eslam.mywedding.Models.Logout;
import com.example.eslam.mywedding.Models.ProfileModle;
import com.example.eslam.mywedding.Models.UserModle;
import com.example.eslam.mywedding.R;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {
    @BindView(R.id.email_profile)
    TextView tv_email;
    @BindView(R.id.phone_profile)
    TextView tv_phone;
    @BindView(R.id.tv_address_profile)
    TextView tv_address;
    @BindView(R.id.tv_name_profile)
    TextView tv_name;
    @BindView(R.id.country_profile)
    TextView tv_country;
    @BindView(R.id.im_photo_profile)
    ImageView mProfilePhoto;
    @BindView(R.id.toolbar_profile)
    Toolbar toolbar;
    private static final String LOAD_IMAGE_URL = "http://wed.filerolesys.com/";
    private static final String AUTH_BEARER = "Bearer ";
    private String auth;
    private ApiInterface mApiInterface;
    private String mEnglish;
    private String countryName;
    private int country_id;
    private int country;
    private String fname;
    private String lname;
    private String email;
    private String phone;
    private String address;
    private String image;
    private UserModle userModle;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getUser();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        userModle = Shared_Preferences.getUser(Profile.this);

        String Fname = userModle.getFname();
        String Lname = userModle.getLname();
        getSupportActionBar().setTitle(Fname + " " + Lname);
        mEnglish = Locale.getDefault().getLanguage();
        getContryFromApi();
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
            Shared_Preferences.SaveUser(Profile.this, userModle);
            tv_email.setText(email);
            tv_phone.setText(phone);
            tv_address.setText(address);
            tv_country.setText(countryName);

        } else {
            Toasty.error(this, getString(R.string.error), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getContryFromApi();
        getUser();
    }


    private void getUser() {

        auth = AUTH_BEARER + Shared_Preferences.getAuth(this);

        mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<ProfileModle> profileModleCall = mApiInterface.getProfilea(auth);
        final AlertDialog alertDialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage(R.string.getting)
                .setCancelable(false)
                .build();
        alertDialog.show();

        profileModleCall.enqueue(new Callback<ProfileModle>() {
            @Override
            public void onResponse(Call<ProfileModle> call, Response<ProfileModle> response) {
                alertDialog.dismiss();
                if (response.isSuccessful()) {
                    Picasso.get().load(LOAD_IMAGE_URL.concat(response.body().getProfile().getImage()))
                            .into(mProfilePhoto);
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
                        Toasty.info(Profile.this, getResources().getString(R.string.login_again), Toast.LENGTH_LONG).show();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    logOut();
                }
            }

            @Override
            public void onFailure(Call<ProfileModle> call, Throwable t) {
                Log.v("url", "" + t.toString());
                alertDialog.dismiss();

            }
        });
    }

    private void logOut() {
        mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Logout> logoutCall = mApiInterface.logOut();
        logoutCall.enqueue(new Callback<Logout>() {
            @Override
            public void onResponse(Call<Logout> call, Response<Logout> response) {
                Toasty.info(Profile.this, "" + response.body().getMessage(), Toast.LENGTH_LONG).show();
                Shared_Preferences.Logout(Profile.this);
                startActivity(new Intent(Profile.this, MainActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<Logout> call, Throwable t) {

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_edite, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.edite_profile:
                Intent intent = new Intent(Profile.this, EditProfile.class);
                startActivity(intent);
                return true;

            default:
                break;
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
