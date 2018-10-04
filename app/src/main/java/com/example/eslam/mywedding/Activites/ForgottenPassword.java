package com.example.eslam.mywedding.Activites;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eslam.mywedding.API.ApiClient.ApiClient;
import com.example.eslam.mywedding.API.ApiInterFace.ApiInterface;
import com.example.eslam.mywedding.HelperMethod.ConnectionDetector;
import com.example.eslam.mywedding.HelperMethod.HelperMetodsValidation;
import com.example.eslam.mywedding.Models.ForgetPassModel;
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

public class ForgottenPassword extends AppCompatActivity {
    @BindView(R.id.ed_email_forrggetn)
    EditText ed_Forggeten;
    @BindView(R.id.btn_forgot)
    Button fab_Forgot;
    private String mEmail;
    private ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten_password);
        ButterKnife.bind(this);
        fab_Forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ConnectionDetector.isConnectingToInternet(ForgottenPassword.this)) {
                    checkInformation();
                } else {
                    Toasty.error(ForgottenPassword.this, getString(R.string.error), Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void checkInformation() {
        if (HelperMetodsValidation.ValidEmail(ed_Forggeten, this)) {
            gettingPass();
        }
    }

    private void gettingPass() {
        mEmail = String.valueOf(ed_Forggeten.getText().toString());

        RequestBody requestEmail = RequestBody.create(MultipartBody.FORM, mEmail);

        mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);


        final AlertDialog progressDialog  = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage(R.string.sending)
                .setCancelable(false)
                .build();
        progressDialog.show();

        Call<ForgetPassModel> forgottenPasswordCall = mApiInterface.forgetPass(requestEmail);
        forgottenPasswordCall.enqueue(new Callback<ForgetPassModel>() {
            @Override
            public void onResponse(Call<ForgetPassModel> call, Response<ForgetPassModel> response) {
                progressDialog.dismiss();
                String status = response.body().getStatus();
                if (status.equals("0")) {
                    Toast.makeText(ForgottenPassword.this, R.string.check_email, Toast.LENGTH_LONG).show();
                    try {
                        Thread.sleep(2000);
                        startActivity(new Intent(ForgottenPassword.this, SignUp.class));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toasty.info(ForgottenPassword.this, getString(R.string.emailCkecking), Toast.LENGTH_LONG).show();
                    try {
                        Thread.sleep(2000);
                        startActivity(new Intent(ForgottenPassword.this, LogIn.class));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ForgetPassModel> call, Throwable t) {
                progressDialog.dismiss();
                Log.v("url", "" + t.toString());
            }
        });
    }
}
