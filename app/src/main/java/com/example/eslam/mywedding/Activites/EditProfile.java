package com.example.eslam.mywedding.Activites;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.eslam.mywedding.API.ApiClient.ApiClient;
import com.example.eslam.mywedding.API.ApiInterFace.ApiInterface;
import com.example.eslam.mywedding.HelperMethod.HelperMetodsSelectedPhoto;
import com.example.eslam.mywedding.HelperMethod.HelperMetodsValidation;
import com.example.eslam.mywedding.HelperMethod.Shared_Preferences;
import com.example.eslam.mywedding.Models.ContryModel.ContryModel;
import com.example.eslam.mywedding.Models.ContryModel.Country_;
import com.example.eslam.mywedding.Models.UpdateProfileModel;
import com.example.eslam.mywedding.Models.UserModle;
import com.example.eslam.mywedding.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import es.dmoral.toasty.Toasty;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfile extends AppCompatActivity {
    private static final int REQUEST_READ_EXTERNAL_STORAGE = 2;
    private static final int GALLERY = 1;
    private static final String AUTH_BEARER = "Bearer ";
    private String mName, mEmail, mFamilyName, mPhone, mAdress;
    @BindView(R.id.im_photo_edite)
    CircularImageView mProfilePhoto;
    @BindView(R.id.spinner_Contry_edite)
    Spinner mSpinnerContry;
    @BindView(R.id.ed_user_name_edite)
    EditText mEdName;
    @BindView(R.id.ed_famtily_name_edite)
    EditText mEdFamilyName;
    @BindView(R.id.ed_phone_edite)
    EditText mEdPhone;
    @BindView(R.id.ed_address_edite)
    EditText mEdAdress;
    @BindView(R.id.btn_update_edite)
    Button mBtnRegister;
    private String mCitySelected;
    private int mCitySelectedNumber;
    private ApiInterface mApiInterface;
    List<Country_> contryModels;
    private Uri uri;
    private String path;
    private String mEnglish;
    private UserModle userModle;
    private String mAuth;
    AdapterView.OnItemSelectedListener onItemSelectedListener =
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    mCitySelected = contryModels.get(position).getName();
                    mCitySelectedNumber = contryModels.get(position).getId();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        mEnglish = Locale.getDefault().getLanguage();

        mSpinnerContry.setOnItemSelectedListener(onItemSelectedListener);

        setTheEditeText();

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInformation();

            }
        });
        mProfilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isReadStoragePermissionGranted();
                pickPhoto();
            }
        });
        new FillSpinnerTask().execute();
    }

    private void setTheEditeText() {
        userModle = Shared_Preferences.getUser(this);
        if (userModle != null) {
            mEdAdress.setText(userModle.getAddress());
            mEdName.setText(userModle.getFname());
            mEdFamilyName.setText(userModle.getLname());
            mEdPhone.setText(userModle.getPhone());
            mEmail = userModle.getEmail();
        }
    }

    private void checkInformation() {
        if (HelperMetodsValidation.ValidUserName(mEdName, this)
                && HelperMetodsValidation.ValidFamilyName(mEdFamilyName, this)
                && HelperMetodsValidation.ValidPhone(mEdPhone, this)
                && HelperMetodsValidation.ValidAddress(mEdAdress, this)) {
            setUserUpdateToApi();
        }
    }

    private void setUserUpdateToApi() {

        mAuth = AUTH_BEARER + Shared_Preferences.getAuth(this);

        final File file;
        if (path != null) {
            file = new File(path);

            mName = String.valueOf(mEdName.getText().toString());
            mFamilyName = String.valueOf(mEdFamilyName.getText().toString());
            mPhone = String.valueOf(mEdPhone.getText().toString());
            mAdress = String.valueOf(mEdAdress.getText().toString());


            RequestBody requestFile = RequestBody.create(MultipartBody.FORM, file);

            MultipartBody.Part imagefile = MultipartBody.Part.createFormData("image", file.getName(), requestFile);


            final AlertDialog progressDialog = new SpotsDialog.Builder()
                    .setContext(this)
                    .setMessage(R.string.editing)
                    .setCancelable(false)
                    .build();
            progressDialog.show();

            mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);


            Call<UpdateProfileModel> updateProfileModelCall = mApiInterface.update(
                    mAuth
                    , mName
                    , mFamilyName
                    , mAdress
                    , mPhone
                    , imagefile
                    , mCitySelectedNumber
            );
            updateProfileModelCall.enqueue(new Callback<UpdateProfileModel>() {
                @Override
                public void onResponse(Call<UpdateProfileModel> call, Response<UpdateProfileModel> response) {
                    progressDialog.dismiss();
                    if (response.isSuccessful()) {
                        try {
                            Toasty.success(EditProfile.this, "" + response.body().getMessage(), Toast.LENGTH_LONG).show();
                            userModle = new UserModle(mName, mFamilyName, mCitySelected, mCitySelectedNumber, mEmail, mPhone, mAdress, "");
                            Shared_Preferences.SaveUser(EditProfile.this, userModle);
                            finish();
                        } catch (Exception e) {
                            Log.v("url2", "" + e.getMessage());
                        }
                    } else {
                        Toasty.error(EditProfile.this, " Error Please Try Again Later", Toast.LENGTH_LONG).show();
                        Log.v("url", "" + response + "\n" + response.code());
                    }
                }

                @Override
                public void onFailure(Call<UpdateProfileModel> call, Throwable t) {
                    Log.v("inurl", "" + t.getMessage());
                    progressDialog.dismiss();
                }
            });
        } else {
            Toast.makeText(this, R.string.Plz_Choose_photo, Toast.LENGTH_LONG).show();
        }
    }

    public void pickPhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.choose)), GALLERY);
    }


    public boolean isReadStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_EXTERNAL_STORAGE);
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(EditProfile.this, R.string.AskforPermission, Toast.LENGTH_LONG).show();
                }
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (isReadStoragePermissionGranted()) {
            if (requestCode == GALLERY) {
                if (data != null) {

                    uri = data.getData();
                    {


                        if (Build.VERSION.SDK_INT <= 19)
                            path = HelperMetodsSelectedPhoto.getRealPathFromURI_API18(this, data.getData());
                        else
                            path = HelperMetodsSelectedPhoto.getRealPathFromURI_API19(this, data.getData());
                    }
                    try {
                        if (uri != null) {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                            mProfilePhoto.setImageBitmap(bitmap);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    private void getContryFromApi() {


        mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ContryModel> list = mApiInterface.getCountryList();
        list.enqueue(new Callback<ContryModel>() {
            @Override
            public void onResponse(Call<ContryModel> call, Response<ContryModel> response) {
                contryModels = response.body().getCountries();
                fillTheSpinner();
            }

            @Override
            public void onFailure(Call<ContryModel> call, Throwable t) {
                Log.v("url", "" + t.getMessage());

            }
        });

    }

    public void fillTheSpinner() {
        final AlertDialog alertDialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage(R.string.login)
                .setCancelable(false)
                .build();
        alertDialog.show();
        List<String> stringList = new ArrayList<>();
        if (mEnglish.equals("en")) {
            alertDialog.dismiss();
            for (int i = 0; i < contryModels.size(); i++) {
                stringList.add(contryModels.get(i).getName());
            }
        } else {
            alertDialog.dismiss();
            for (int i = 0; i < contryModels.size(); i++) {
                stringList.add(contryModels.get(i).getArName());
            }
        }
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(EditProfile.this,
                        android.R.layout.simple_spinner_item, stringList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerContry.setAdapter(adapter);
    }

    private class FillSpinnerTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            getContryFromApi();
            return null;
        }
    }

}