package com.rungreangchai.spaky.onebasic.network;


import android.os.AsyncTask;
import android.util.Log;


import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RequestTask extends AsyncTask<Request, Void, String> {

    private String doPostRequest(Request request) {
        Response response = null;
        OkHttpClient client = new OkHttpClient();

        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            Log.d("requestAndRespones", "IOExcep : " + e.toString());
        }
        return null;
    }


    @Override
    protected String doInBackground(Request... values) {
        return doPostRequest(values[0]);
    }

    @Override
    protected void onPostExecute(final String result) {
        super.onPostExecute(result);
    }

}
