package com.rungreangchai.spaky.onebasic.network;

import java.io.File;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class RequestBodyBuilder {

    private OkHttpClient client;

    public static RequestBody postInsertUser(String todo, String username, String pass, String fName, String lName) {
        return new FormBody.Builder()
                .add("todo", todo)
                .add("username", username)
                .add("password", pass)
                .add("fname", fName)
                .add("lname", lName)
                .build();
    }

    public static MultipartBody postMutipartProduct(String todo, String number, String name, String price, File file, MediaType type) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("todo", todo)
                .addFormDataPart("number", number)
                .addFormDataPart("name", name)
                .addFormDataPart("price", price)
                .addFormDataPart("file", name, RequestBody.create(type, file))
                .build();
    }
}
