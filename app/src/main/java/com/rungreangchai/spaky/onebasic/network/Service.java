package com.rungreangchai.spaky.onebasic.network;

import android.util.Log;

import com.rungreangchai.spaky.onebasic.network.model.UserProfile;

import org.json.JSONArray;
import org.json.JSONObject;


public class Service {
    private static final String URL_LOGIN_USER = "http://192.168.1.18/test/user_profile.php";
    private static final String DO_POST = "post";
    private static final String DO_GET = "get";

    private static RequestTask task;

    public static void getUserLogin(UserResultLoginCallback callback) {
        if (callback != null) {
            UserProfile userProfiles = null;
            task = new RequestTask();
            try {
                String strJson = task.execute(URL_LOGIN_USER, DO_GET).get();
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


    public interface UserResultLoginCallback {
        void onUserLoginSuccess(UserProfile userProfileList);
    }

}
