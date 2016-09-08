package com.rungreangchai.spaky.onebasic.network;


import android.os.AsyncTask;
import android.util.Log;


import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RequestTask extends AsyncTask<String, Void, String> {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private String doPostRequest(String url, String json, String multipart) {
        Response response = null;
        RequestBody body = RequestBody.create(JSON, json);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            Log.d("requestAndRespones", "IOExcep : " + e.toString());
        }
        return null;
    }

    @Override
    protected String doInBackground(String... values) {
        return doPostRequest(values[0], values[1], values[2]);
    }

    @Override
    protected void onPostExecute(final String result) {
        super.onPostExecute(result);
    }

//    public RequestBody mutipartBody() {
//
//    }
}
