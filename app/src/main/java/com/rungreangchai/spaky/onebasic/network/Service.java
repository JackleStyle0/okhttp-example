package com.rungreangchai.spaky.onebasic.network;

import android.util.Log;

import com.rungreangchai.spaky.onebasic.network.model.UserProfile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.concurrent.ExecutionException;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;


public class Service {
    private static final String URL_LOGIN_USER = "http://192.168.1.16/test/user_profile.php";
    private static final String URL_PRODUCR = "http://192.168.1.16/test/product.php";
    private static final MediaType MEDIA_TYPE_JPG = MediaType.parse("image/jpg");
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private static final String TODO_SELECT = "SELECT";
    private static final String TODO_INSERT = "INSERT";
    private static final String TODO_UPDATE = "UPDATE";
    private static final String TODO_DELETE = "DELECT";

    private static RequestTask task = new RequestTask();

//    public static void getUserLogin(UserResultLoginCallback callback) {
//        if (callback != null) {
//            UserProfile userProfiles = null;
//            String strJson = null;
//            JSONObject json = new JSONObject();
//            try {
//                json.put("todo", TODO_SELECT);
//                strJson = task.execute(URL_LOGIN_USER, json.toString()).get();
//                JSONArray jsonArray = new JSONArray(strJson);
//                JSONObject obj = jsonArray.getJSONObject(0);
//                String name = obj.getString("username");
//                String password = obj.getString("password");
//                String firstName = obj.getString("firstname");
//                String lastName = obj.getString("lastname");
//                userProfiles = new UserProfile(name, password, firstName, lastName);
//            } catch (Exception e) {
//                Log.d("getUserLogin", "ExecutionException e" + e.toString());
//            }
//            callback.onUserLoginSuccess(userProfiles);
//        }
//    }

    public interface UserResultLoginCallback {
        void onUserLoginSuccess(UserProfile userProfileList);
    }

    public static void regisUser(String user, String pass, String fName, String lName, UserRegisterCallback callback) {
        String response = null;
        RequestBody body = RequestBodyBuilder.postInsertUser(TODO_INSERT, user, pass, fName, lName);
        if (callback != null) {
            try {
                Request request = RequestBuilder.Post(URL_LOGIN_USER, body);
                response = task.execute(request).get();
                Log.d("regisUser ", "response " + response.toString());
//                JSONObject obj = new JSONObject(response);
//                callback.onUserRegisSuccess(obj.getBoolean("success"));
            } catch (Exception e) {
                Log.d("regisUser ", "Exception " + e.toString());
            }
        }
    }

    public interface UserRegisterCallback {
        void onUserRegisSuccess(Boolean success);
    }

    public static void productUpload(String proNumber, String proNname, String proPirce, File imgStr, InsertProductCallback callback) {
        String response = null;
        RequestBody body = RequestBodyBuilder.postMutipartProduct(TODO_INSERT, proNumber, proNname, proPirce, imgStr, MEDIA_TYPE_JPG);
        if (callback != null) {
            Request request = RequestBuilder.Post(URL_PRODUCR, body);
            try {
                response = task.execute(request).get();

//                JSONObject obj = new JSONObject(response);
                Log.d("productUpload", "response" + response.toString());
//                callback.onInsertProductSuccess(obj.getBoolean("success"));
            } catch (Exception e) {
                Log.d("productUpload", "Exception e" + e.toString());
            }
        }
    }

    public interface InsertProductCallback {
        void onInsertProductSuccess(Boolean success);
    }

}
