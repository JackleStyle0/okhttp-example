package com.rungreangchai.spaky.onebasic.network;

import android.util.Log;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RequestBuilder {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static RequestBody requestMultipart(String name, String number, String price, File file, MediaType mediaType) {
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", name)
                .addFormDataPart("number", number)
                .addFormDataPart("price", price)
                .addFormDataPart("file", name, RequestBody.create(mediaType, file))
                .build();

        return body;
    }

    public static RequestBody requestPost(String json) {
        RequestBody body = RequestBody.create(JSON, json);
        return body;
    }
}
