package com.rungreangchai.spaky.onebasic.network;

import android.util.Log;

import com.rungreangchai.spaky.onebasic.network.model.UserProfile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;


public class Service {
    private static final String URL_LOGIN_USER = "http://192.168.1.18/test/user_profile.php";
    private static final String URL_PRODUCR = "http://192.168.1.18/test/user_profile.php";
    private static final MediaType MEDIA_TYPE_JPG = MediaType.parse("image/jpg");
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private static final String TODO_SELECT = "SELECT";
    private static final String TODO_INSERT = "INSERT";
    private static final String TODO_UPDATE = "UPDATE";
    private static final String TODO_DELETE = "DELECT";

    private static RequestTask task = new RequestTask();

    public static void getUserLogin(UserResultLoginCallback callback) {
        if (callback != null) {
            UserProfile userProfiles = null;
            String strJson = null;
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

    public interface UserResultLoginCallback {
        void onUserLoginSuccess(UserProfile userProfileList);
    }

    public static void regisUser(String user, String pass, String fName, String lName, UserRegisterCallback callback) {
        JSONObject json = new JSONObject();
        String response = null;
        if (callback != null) {
            try {
                json.put("todo", TODO_INSERT);
                json.put("user", user);
                json.put("pass", pass);
                json.put("fname", fName);
                json.put("lname", lName);
            } catch (JSONException e) {
                Log.d("regisUser", "JSONException" + e.toString());
            }

            try {
                RequestBody body = RequestBuilder.requestPost(json.toString());
//                response = task.execute(URL_LOGIN_USER, body.toString(), "false").get();
//                Log.d("regisUser ", "Exception " + body);
                JSONObject obj = new JSONObject(response);
                callback.onUserRegisSuccess(obj.getBoolean("success"));
            } catch (Exception e) {
                Log.d("regisUser ", "Exception " + e.toString());
            }
        }
    }

    public interface UserRegisterCallback {
        void onUserRegisSuccess(Boolean success);
    }

    public static void productUpload(String proNumber, String proNname, String proPirce, String imgStr) {

    }

}
