package com.rungreangchai.spaky.onebasic.network;


import android.os.AsyncTask;
import android.util.Log;


import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestTask extends AsyncTask<String, Void, String> {


    private String doPostOrGetRequest(String url, String doPostAndGet) {
        String result = null;
        if (doPostAndGet.trim().equals("post")) {
            result = doPostRequest(url);
            Log.d("request", "do post");
        } else {
            result = doGetRequest(url);
            Log.d("request", "do get");
        }
        return result;
    }

    private String doPostRequest(String url) {
        return null;
    }

    private String doGetRequest(String url) {
        Response response = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
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
    protected String doInBackground(String... urls) {
        return doPostOrGetRequest(urls[0], urls[1]);
    }

    @Override
    protected void onPostExecute(final String result) {
        super.onPostExecute(result);
    }
}
