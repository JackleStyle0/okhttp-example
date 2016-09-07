package com.rungreangchai.spaky.onebasic.network;

import android.util.Log;

import com.rungreangchai.spaky.onebasic.network.model.UserProfile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Service {
    private static final String URL_LOGIN_USER = "http://192.168.1.18/test/user_profile.php";
    private static final String URL_PRODUCR = "http://192.168.1.18/test/user_profile.php";
    private static final String TODO_SELECT = "SELECT";
    private static final String TODO_INSERT = "INSERT";
    private static final String TODO_UPDATE = "UPDATE";
    private static final String TODO_DELETE = "DELECT";

    private static RequestTask task;

    public static void getUserLogin(UserResultLoginCallback callback) {
        if (callback != null) {
            UserProfile userProfiles = null;
            String strJson = null;
            task = new RequestTask();
            JSONObject json = new JSONObject();
            try {
                json.put("todo", TODO_SELECT);
                strJson = task.execute(URL_LOGIN_USER, json.toString()).get();
                JSONArray jsonArray = new JSONArray(strJson);
                JSONObject obj = jsonArray.getJSONObject(0);
                String name = obj.getString("username");
                String password = obj.getString("password");
                String firstName = obj.getString("firstname");
                String lastName = obj.getString("lastname");
                userProfiles = new UserProfile(name, password, firstName, lastName);
            } catch (Exception e) {
                Log.d("getUserLogin", "ExecutionException e" + e.toString());
            }
            callback.onUserLoginSuccess(userProfiles);
        }
    }

    public static void regisUser(String user, String pass, String fName, String lName, UserRegisterCallback callback) {
        JSONObject json = new JSONObject();
        String response = null;
        if (callback != null) {
            try {
                json.put("user", user);
                json.put("pass", pass);
                json.put("fname", fName);
                json.put("lname", lName);
            } catch (JSONException e) {
                Log.d("regisUser", "JSONException" + e.toString());
            }

            try {
                response = task.execute(URL_LOGIN_USER, json.toString()).get();
            } catch (Exception e) {
                Log.d("regisUser ", "Exception" + e.toString());
            }
        }

        Log.d("response", " data " + response);

    }

    public interface UserRegisterCallback {
        void onUserRegisSuccess(Boolean success);
    }

    public interface UserResultLoginCallback {
        void onUserLoginSuccess(UserProfile userProfileList);
    }

}
