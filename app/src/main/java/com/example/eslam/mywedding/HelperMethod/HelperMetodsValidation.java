package com.example.eslam.mywedding.HelperMethod;


import android.content.Context;
import android.widget.TextView;

import com.example.eslam.mywedding.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HelperMetodsValidation {

    public static boolean ValidEmail(TextView mEmail, Context context) {
        String valid_email = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{3,3})$";
        Matcher matcher_email = Pattern.compile(valid_email).matcher(mEmail.getText().toString().toLowerCase());
        if (matcher_email.matches()) {
            return true;
        } else {
            mEmail.setError(context.getResources().getString(R.string.invalidEmail));
            mEmail.requestFocus();
            return false;
        }
    }

    public static boolean ValidUserName(TextView mUserName, Context context) {
        String vaildName = "^[a-zA-Z\\s]+";
        String mName = mUserName.getText().toString().toLowerCase();
        Matcher matcher_phone = Pattern.compile(vaildName).matcher(mName);
        if (matcher_phone.matches()) {
            return true;
        } else {
            mUserName.setError(context.getResources().getString(R.string.invalidName));
            mUserName.requestFocus();
            return false;
        }
    }

    public static boolean ValidFamilyName(TextView mFamilyName, Context context) {
        String vaildName = "^[a-zA-Z\\s]+";
        String mName = mFamilyName.getText().toString().toLowerCase();
        Matcher matcher_phone = Pattern.compile(vaildName).matcher(mName);
        if (matcher_phone.matches()) {
            return true;
        } else {
            mFamilyName.setError(context.getResources().getString(R.string.invalidFamliyName));
            mFamilyName.requestFocus();
            return false;
        }
    }

    public static boolean ValidPassword(TextView mPassword, Context context) {
        String mPass = mPassword.getText().toString();

        if (mPass.length() > 6) {
            return true;
        } else {
            mPassword.setError(context.getResources().getString(R.string.invalidPassword));
            mPassword.requestFocus();
            return false;
        }

    }

    public static boolean ValidConfirmPassword(TextView mPassword, TextView mConfirmPassword, Context context) {

        if (mPassword.getText().toString().equals(mConfirmPassword.getText().toString())) {
            return true;
        } else {
            mConfirmPassword.setError(context.getResources().getString(R.string.invalidConfirmPassword));
            mConfirmPassword.requestFocus();
            return false;
        }
    }

    public static boolean ValidPhone(TextView mPhone, Context context) {
        String valid_Phone = "(010|011|012|015)(\\s*-?\\d){8}";
        String phone = mPhone.getText().toString();
        Matcher matcher_phone = Pattern.compile(valid_Phone).matcher(phone);
        if (matcher_phone.matches()) {
            return true;
        } else {
            mPhone.setError(context.getResources().getString(R.string.invalidPhone));
            mPhone.requestFocus();
            return false;
        }

    }

    public static boolean ValidAddress(TextView mAddress, Context context) {
        String mName = mAddress.getText().toString().toLowerCase();
        if (mName.length() > 0) {
            return true;
        } else {
            mAddress.setError(context.getResources().getString(R.string.invalidAddress));
            mAddress.requestFocus();
            return false;
        }
    }

    public static boolean ValidBooking(TextView mBooking, Context context) {
        String mName = mBooking.getText().toString().toLowerCase();
        if (mName.length() > 0) {
            return true;
        } else {
            mBooking.setError(context.getResources().getString(R.string.invalidBooking));
            mBooking.requestFocus();
            return false;
        }
    }

}
