package com.example.eslam.mywedding.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.eslam.mywedding.API.ApiClient.ApiClient;
import com.example.eslam.mywedding.API.ApiInterFace.ApiInterface;
import com.example.eslam.mywedding.HelperMethod.HelperMetodsValidation;
import com.example.eslam.mywedding.Models.ContactUs;
import com.example.eslam.mywedding.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dmax.dialog.SpotsDialog;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsFragment extends Fragment {
    View rootView;
    @BindView(R.id.ed_fname_contact_us)
    EditText ed_fname;
    @BindView(R.id.ed_lname_contact_us)
    EditText ed_lname;
    @BindView(R.id.ed_email_contact_us)
    EditText ed_email;
    @BindView(R.id.ed_phone_contact_us)
    EditText ed_phone;
    @BindView(R.id.ed_message_contact_us)
    EditText ed_Message;
    @BindView(R.id.btn_send_contact_us)
    Button btn_send;
    private String mLname;
    private String mFname;
    private String mEmail;
    private String mPhone;
    private String mMessage;
    private ApiInterface mApiInterface;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.contact_us, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInformation();
            }
        });

        return rootView;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    private void checkInformation() {
        if (HelperMetodsValidation.ValidUserName(ed_fname, getContext())
                && HelperMetodsValidation.ValidFamilyName(ed_lname, getContext())
                && HelperMetodsValidation.ValidEmail(ed_email, getContext())
                && HelperMetodsValidation.ValidPhone(ed_phone, getContext())) {
            getInformation();
        }
    }

    private void getInformation() {
        mFname = ed_fname.getText().toString().trim();
        mLname = ed_lname.getText().toString().trim();
        mEmail = ed_email.getText().toString().trim();
        mPhone = String.valueOf(ed_phone.getText().toString().trim());
        mMessage = ed_Message.getText().toString().trim();

        RequestBody fname = RequestBody.create(MultipartBody.FORM, mFname);
        RequestBody lname = RequestBody.create(MultipartBody.FORM, mLname);
        RequestBody email = RequestBody.create(MultipartBody.FORM, mEmail);
        RequestBody phone = RequestBody.create(MultipartBody.FORM, mPhone);
        RequestBody message = RequestBody.create(MultipartBody.FORM, mMessage);

        mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        final AlertDialog progressDialog = new SpotsDialog.Builder()
                .setContext(getContext())
                .setMessage(R.string.sendto)
                .setCancelable(false)
                .build();
        progressDialog.show();
        Call<ContactUs> contactUsCall = mApiInterface.contactUs(fname, lname, email, phone, message);

        contactUsCall.enqueue(new Callback<ContactUs>() {
            @Override
            public void onResponse(Call<ContactUs> call, Response<ContactUs> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getMessage() != null && !response.body().getMessage().isEmpty()) {
                        Snackbar.make(rootView, "" + response.body().getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                } else {
                    Snackbar.make(rootView, R.string.error, Snackbar.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ContactUs> call, Throwable t) {
                Log.d("urlContactUs", "" + t.getMessage());
                progressDialog.dismiss();
            }
        });

    }

}
