package com.rungreangchai.spaky.onebasic.network;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by spaky on 12/9/2559.
 */
public class RequestBuilder {
    public static Request Post(String urls, RequestBody body) {
        return new Request.Builder()
                .url(urls)
                .post(body)
                .build();

    }
}
