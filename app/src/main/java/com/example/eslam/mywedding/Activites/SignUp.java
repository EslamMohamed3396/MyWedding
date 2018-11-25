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
import com.example.eslam.mywedding.Models.ContryModel.ContryModel;
import com.example.eslam.mywedding.Models.ContryModel.Country_;
import com.example.eslam.mywedding.Models.SignUpMessageModel;
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
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {
    private static final int REQUEST_READ_EXTERNAL_STORAGE = 2;
    private static final int GALLERY = 1;
    private String mName, mEmail, mPassword, mConfirmPass, mFamilyName, mPhone, mAdress;
    @BindView(R.id.im_photo)
    CircularImageView mProfilePhoto;
    @BindView(R.id.spinner_Contry)
    Spinner mSpinnerContry;
    @BindView(R.id.ed_user_name)
    EditText mEdName;
    @BindView(R.id.ed_email)
    EditText mEdEmail;
    @BindView(R.id.ed_password)
    EditText mEdPassword;
    @BindView(R.id.ed_confirm_password)
    EditText mEdConfirmPass;
    @BindView(R.id.ed_famtily_name)
    EditText mEdFamilyName;
    @BindView(R.id.ed_phone)
    EditText mEdPhone;
    @BindView(R.id.ed_address)
    EditText mEdAdress;
    @BindView(R.id.btn_register)
    Button mBtnRegister;
    private int mCitySelected = 1;
    private ApiInterface mApiInterface;
    List<Country_> contryModels;
    private Uri uri;
    private String path;
    private String mEnglish;
    AdapterView.OnItemSelectedListener onItemSelectedListener =
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    mCitySelected = contryModels.get(position).getId();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);

        mEnglish = Locale.getDefault().getLanguage();

        mSpinnerContry.setOnItemSelectedListener(onItemSelectedListener);

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

    private void checkInformation() {
        if (HelperMetodsValidation.ValidUserName(mEdName, this)
                && HelperMetodsValidation.ValidFamilyName(mEdFamilyName, this)
                && HelperMetodsValidation.ValidEmail(mEdEmail, this)
                && HelperMetodsValidation.ValidPassword(mEdPassword, this)
                && HelperMetodsValidation.ValidConfirmPassword(mEdPassword, mEdConfirmPass, this)
                && HelperMetodsValidation.ValidPhone(mEdPhone, this)
                && HelperMetodsValidation.ValidAddress(mEdAdress, this)) {
            setUserToApi();
        }
    }

    public void pickPhoto() {

        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, getString(R.string.choose));
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

        startActivityForResult(chooserIntent, GALLERY);
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
                    Toast.makeText(SignUp.this, R.string.AskforPermission, Toast.LENGTH_LONG).show();
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
                        if (Build.VERSION.SDK_INT > 19) {
                            path = HelperMetodsSelectedPhoto.getRealPathFromURI_API19(this, data.getData());
                        } else {
                            path = HelperMetodsSelectedPhoto.getRealPathFromURI_API18(this, data.getData());
                        }
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

    private void setUserToApi() {
        final File file;
        if (path != null) {
            file = new File(path);

            mName = String.valueOf(mEdName.getText().toString());
            mEmail = String.valueOf(mEdEmail.getText().toString());
            mPassword = String.valueOf(mEdPassword.getText().toString());
            mConfirmPass = String.valueOf(mEdConfirmPass.getText().toString());
            mFamilyName = String.valueOf(mEdFamilyName.getText().toString());
            mPhone = String.valueOf(mEdPhone.getText().toString());
            mAdress = String.valueOf(mEdAdress.getText().toString());

            RequestBody requestfName = RequestBody.create(MediaType.parse("multipart/form-data"), mName);
            RequestBody requestLname = RequestBody.create(MediaType.parse("multipart/form-data"), mFamilyName);
            RequestBody requestEmail = RequestBody.create(MediaType.parse("multipart/form-data"), mEmail);
            RequestBody requestPassword = RequestBody.create(MediaType.parse("multipart/form-data"), mPassword);
            RequestBody requestConfirmPassword = RequestBody.create(MediaType.parse("multipart/form-data"), mConfirmPass);
            RequestBody requestPhone = RequestBody.create(MediaType.parse("multipart/form-data"), mPhone);
            RequestBody requestAddress = RequestBody.create(MediaType.parse("multipart/form-data"), mAdress);

            RequestBody requestCountry = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(mCitySelected - 1));

            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

            MultipartBody.Part imagefile = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

            if (mCitySelected != 1) {
                final AlertDialog alertDialog = new SpotsDialog.Builder()
                        .setContext(this)
                        .setMessage(R.string.signing)
                        .setCancelable(false)
                        .build();
                alertDialog.show();
                mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                final Call<SignUpMessageModel> sign = mApiInterface.signUP(
                        requestfName
                        , requestLname
                        , requestEmail
                        , requestPassword
                        , requestConfirmPassword
                        , requestPhone
                        , requestAddress
                        , imagefile
                        , requestCountry
                );
                sign.enqueue(new Callback<SignUpMessageModel>() {
                    @Override
                    public void onResponse(Call<SignUpMessageModel> call, Response<SignUpMessageModel> response) {
                        alertDialog.dismiss();

                        if (response.isSuccessful()) {
                            Toasty.success(SignUp.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUp.this, LogIn.class));
                            finish();
                        } else {
                            Toasty.error(SignUp.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SignUpMessageModel> call, Throwable t) {
                        Log.v("urlSignUp", "" + t.getMessage());
                        alertDialog.dismiss();
                        Toasty.error(SignUp.this, "" + getString(R.string.errorOrAdded), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toasty.info(SignUp.this, getString(R.string.chooseCountry), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toasty.info(SignUp.this, getString(R.string.Plz_Choose_photo), Toast.LENGTH_SHORT).show();
        }

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
            stringList.add(getString(R.string.chooseCountry));
            alertDialog.dismiss();
            for (int i = 0; i < contryModels.size(); i++) {
                stringList.add(contryModels.get(i).getName());
            }
        } else {
            stringList.add(getString(R.string.chooseCountry));
            alertDialog.dismiss();
            for (int i = 0; i < contryModels.size(); i++) {
                stringList.add(contryModels.get(i).getArName());
            }
        }
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(SignUp.this,
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


