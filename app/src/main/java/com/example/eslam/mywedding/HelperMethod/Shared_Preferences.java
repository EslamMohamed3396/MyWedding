package com.example.eslam.mywedding.HelperMethod;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.eslam.mywedding.Models.UserModle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public final class Shared_Preferences {
    private static final String PREFERENCES_ACCOUNT = "Prefs_Account";
    private static final String PREF_NAME = "pref_Name";
    private static final String PREF_PASS = "pref_Pass";
    private static final String PREF_AUTH = "pref_Auth";
    private static final String PREF_FNAME = "pref_Fname";
    private static final String PREF_LNAME = "pref_Lname";
    private static final String PREF_EMAIL = "pref_Email";
    private static final String PREF_IMAGE = "pref_Image";
    private static final String PREF_PHONE = "pref_Phone";
    private static final String PREF_COUNTRY = "pref_Contry";
    private static final String PREF_COUNTRY_ID = "pref_Contry_id";
    private static final String PREF_ADDRESS = "pref_Address";

    private static final String PREFERENCES_WIDGET = "Prefs_Account";
    private static final String PREF_NAME_WIDGET = "pref_name_widget";


    private static SharedPreferences mPref;
    private static SharedPreferences.Editor mEditor;

    public static void saveLogin(Context context, String email, String pass, String auth) {
        mPref = context.getSharedPreferences(PREFERENCES_ACCOUNT, Context.MODE_PRIVATE);
        mEditor = mPref.edit();
        mEditor.putString(PREF_NAME, email);
        mEditor.putString(PREF_PASS, pass);
        mEditor.putString(PREF_AUTH, auth);
        mEditor.apply();
    }



    public static void saveArrayList(List<String> list, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCES_WIDGET, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(PREF_NAME_WIDGET, json);
        editor.apply();
    }


    public static List<String> getList(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCES_WIDGET, context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(PREF_NAME_WIDGET, null);
        Type type = new TypeToken<List<String>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public static boolean getLogin(Context context) {
        mPref = context.getSharedPreferences(PREFERENCES_ACCOUNT, Context.MODE_PRIVATE);
        String email = mPref.getString(PREF_NAME, "");
        String pass = mPref.getString(PREF_PASS, "");
        String auth = mPref.getString(PREF_AUTH, "");
        if ((!email.isEmpty() && email != null) && (!pass.isEmpty() && pass != null) && (!auth.isEmpty() && auth != null)) {
            return true;
        }
        return false;
    }

    public static String getAuth(Context context) {
        mPref = context.getSharedPreferences(PREFERENCES_ACCOUNT, Context.MODE_PRIVATE);

        String auth = mPref.getString(PREF_AUTH, "");
        if ((!auth.isEmpty() && auth != null)) {
            return auth;
        }
        return "";
    }

    public static void Logout(Context context) {
        mPref = context.getSharedPreferences(PREFERENCES_ACCOUNT, context.MODE_PRIVATE);
        mEditor = mPref.edit();
        mEditor.clear();
        mEditor.commit();
    }


    public static void SaveUser(Context context, UserModle userModle) {
        mPref = context.getSharedPreferences(PREFERENCES_ACCOUNT, Context.MODE_PRIVATE);
        mEditor = mPref.edit();
        mEditor.putString(PREF_FNAME, userModle.getFname());
        mEditor.putString(PREF_LNAME, userModle.getLname());
        mEditor.putString(PREF_EMAIL, userModle.getEmail());
        mEditor.putString(PREF_ADDRESS, userModle.getAddress());
        mEditor.putString(PREF_IMAGE, userModle.getImage());
        mEditor.putString(PREF_PHONE, userModle.getPhone());
        mEditor.putString(PREF_COUNTRY, userModle.getCountry());
        mEditor.putInt(PREF_COUNTRY_ID, userModle.getCountryId());
        mEditor.apply();
    }

    public static UserModle getUser(Context context) {
        UserModle userModle;
        mPref = context.getSharedPreferences(PREFERENCES_ACCOUNT, Context.MODE_PRIVATE);
        String fname = mPref.getString(PREF_FNAME, "");
        String lname = mPref.getString(PREF_LNAME, "");
        String email = mPref.getString(PREF_EMAIL, "");
        String Image = mPref.getString(PREF_IMAGE, "");
        String phone = mPref.getString(PREF_PHONE, "");
        String address = mPref.getString(PREF_ADDRESS, "");
        String country = mPref.getString(PREF_COUNTRY, "");
        int country_id = mPref.getInt(PREF_COUNTRY_ID, 0);
        if ((!fname.isEmpty() && fname != null)
                && (!lname.isEmpty() && lname != null)
                && (!phone.isEmpty() && phone != null)
                && (!Image.isEmpty() && Image != null)
                && (!address.isEmpty() && address != null)
                && (!email.isEmpty() && email != null)
                && (!country.isEmpty() && country != null)
                && country_id > 0) {
            userModle = new UserModle(fname, lname, country, country_id, email, phone, address, Image);
            return userModle;
        }
        return null;
    }
}
