package com.ivntel.android.prekhome.Retrofit;

import com.ivntel.android.prekhome.POJO.Example;
import com.ivntel.android.prekhome.POJO.Results;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by ivnte on 2017-08-18.
 */

public interface GeniusApi {
    @GET("/apiprek?")
    Call<Example> getPreKContents();

    @GET("/apiprek?")
    Call<Example> getPreKContentsAgeAndLanguage(@Query("ageGroup") String ageGroup, @Query("language") String language);
}
