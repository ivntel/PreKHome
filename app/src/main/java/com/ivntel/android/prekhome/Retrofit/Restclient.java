package com.ivntel.android.prekhome.Retrofit;

/**
 * Created by ivnte on 2017-08-18.
 */

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * NetworkService is used to create the network client to make network calls
 **/
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Restclient {
    private static Retrofit retrofit = null;
    public static final String BASE_URL = "https://www.geniusplaza.com";


    public static Retrofit getClient(String baseUrl) {

        if (retrofit==null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static GeniusApi getExampleApi() {
        return Restclient.getClient(BASE_URL).create(GeniusApi.class);
    }
}