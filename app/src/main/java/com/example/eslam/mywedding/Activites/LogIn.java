package com.example.eslam.mywedding.Activites;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eslam.mywedding.API.ApiClient.ApiClient;
import com.example.eslam.mywedding.API.ApiInterFace.ApiInterface;
import com.example.eslam.mywedding.HelperMethod.HelperMetodsValidation;
import com.example.eslam.mywedding.HelperMethod.Shared_Preferences;
import com.example.eslam.mywedding.Models.LogInModel;
import com.example.eslam.mywedding.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import es.dmoral.toasty.Toasty;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogIn extends AppCompatActivity {

    @BindView(R.id.ed_email_login)
    EditText ed_Login;
    @BindView(R.id.ed_password_login)
    EditText ed_Pass;
    @BindView(R.id.btn_log_in)
    Button fab_Login;
    @BindView(R.id.tv_sign_up)
    TextView tv_SignUp;
    @BindView(R.id.txt_forgotten_pass)
    TextView tv_ForgetPass;


    private String mEmail;
    private String mPassword;
    private String authes;
    private ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);

        boolean isLogin = Shared_Preferences.getLogin(LogIn.this);

        if (isLogin) {
            startActivity(new Intent(LogIn.this, HomePage.class));
            finish();
        }
        fab_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInformation();
            }
        });

        tv_ForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogIn.this, ForgottenPassword.class));
            }
        });

        tv_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogIn.this, SignUp.class));
            }
        });

    }


    private void checkInformation() {
        if (HelperMetodsValidation.ValidEmail(ed_Login, this)
                && HelperMetodsValidation.ValidPassword(ed_Pass, this)) {
            setLogin();
        }
    }

    private void setLogin() {
        mEmail = String.valueOf(ed_Login.getText().toString());
        mPassword = String.valueOf(ed_Pass.getText().toString());

        mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        RequestBody requestEmail = RequestBody.create(MultipartBody.FORM, mEmail);
        RequestBody requestPassword = RequestBody.create(MultipartBody.FORM, mPassword);
        final AlertDialog alertDialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage(R.string.login)
                .setCancelable(false)
                .build();
        alertDialog.show();


        Call<LogInModel> logIn = mApiInterface.logIn(requestEmail, requestPassword);


        logIn.enqueue(new Callback<LogInModel>() {
            @Override
            public void onResponse(Call<LogInModel> call, Response<LogInModel> response) {
                alertDialog.dismiss();
                String message = response.body().getStatus();
                authes = response.body().getToken();
                if (message.equals("0")) {
                    Toasty.error(LogIn.this, getString(R.string.wrong), Toast.LENGTH_LONG).show();
                } else {
                    Shared_Preferences.saveLogin(LogIn.this, mEmail, mPassword, authes);
                    Intent intent = new Intent(LogIn.this, HomePage.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<LogInModel> call, Throwable t) {
                // progressDialog.dismiss();
                alertDialog.dismiss();

                Log.v("url", "" + t.toString());
            }
        });
    }

}
