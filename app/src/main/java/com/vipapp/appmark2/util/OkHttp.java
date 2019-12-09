package com.vipapp.appmark2.util;

import com.vipapp.appmark2.callback.PushCallback;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttp {
    private static OkHttpClient client = new Builder()
            .connectTimeout(7, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build();

    public static void get(final String url, final PushCallback<String> result) {
        client.newCall(new Request.Builder().url(url).build()).enqueue(new Callback() {
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (!(e instanceof UnknownHostException)) {
                    Thread.ui(() -> result.onComplete(null));
                } else {
                    OkHttp.get(url, result);
                }
            }
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                Thread.ui(() -> {
                    try {
                        result.onComplete((Objects.requireNonNull(response.body())).string());
                    } catch (Exception e) {
                        result.onComplete(null);
                    }
                });
            }
        });
    }
}