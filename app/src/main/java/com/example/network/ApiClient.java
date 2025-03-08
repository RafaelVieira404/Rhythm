package com.example.network;

import com.example.database.StudioGhMovies;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class ApiClient implements Serializable {

    public static final String BASE_URL = "https://ghibliapi.dev";
    public static Retrofit retrofit;

    public static Retrofit getApiRetrofit() {
        if (retrofit == null) {
            synchronized (ApiClient.class) {
                Gson gson = new GsonBuilder()
                        .create();

                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(client())
                        .build();
            }
        }

        return retrofit;
    }

    public interface GetAilment  {
        @GET("/films")
        Call<List<StudioGhMovies>> GET_AILMENT_DATA_CALL();
    }

    public static OkHttpClient client() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        return okHttpClient;
    }

}
