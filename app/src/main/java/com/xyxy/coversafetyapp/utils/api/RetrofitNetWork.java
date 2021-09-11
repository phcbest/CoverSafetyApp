package com.xyxy.coversafetyapp.utils.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitNetWork {

    public static ApiCoverInfo getApiCoverInfo() {
        return initRetrofit().create(ApiCoverInfo.class);
    }

    public static ApiCoverLog getApiCoverLog() {
        return initRetrofit().create(ApiCoverLog.class);
    }

    private static Retrofit initRetrofit() {

        return new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:23333/")//119.3.40.236
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }


}
