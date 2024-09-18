package com.heliwr.appbanvexemphim.Model;

import android.content.Context;
import android.content.SharedPreferences;

public class UserManager {
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_IDUSER = "iduser";

    private static final String KEY_USERLOGGEDIN = "userLoggedIn";
    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
    }

    public static void saveUserData(Context context, String username, String email, String iduser, boolean userLoggedIn) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_IDUSER, iduser);
        editor.putBoolean(KEY_USERLOGGEDIN,userLoggedIn);
        editor.apply();
    }

    public static String getUsername(Context context) {
        return getSharedPreferences(context).getString(KEY_USERNAME, null);
    }

    public static String getEmail(Context context) {
        return getSharedPreferences(context).getString(KEY_EMAIL, null);
    }
    public static String getIduser(Context context) {
        return getSharedPreferences(context).getString(KEY_IDUSER, null);
    }
    public static boolean getuserloggedin(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_USERLOGGEDIN, false);
    }
    public static void clearUserData(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
