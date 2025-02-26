package com.example.network;

import android.util.Log;

import com.example.yoursong.GetAilmentData;
import com.example.yoursong.SetScreenArguments;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class ApiModule {


    public static OkHttpClient client() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        return okHttpClient;
    }

    public static Gson gson() {
        Gson gson = new GsonBuilder().create();
        return gson;
    }

    public static class retrofit {

        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://mhw-db.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        public void create() {
            getAilment getAil = retrofit.create(getAilment.class);
            getAil.GET_AILMENT_DATA_CALL("3").enqueue(new Callback<GetAilmentData>() {
                @Override
                public void onResponse(Call<GetAilmentData> call, Response<GetAilmentData> response) {
                    // Check if response body is null
                    if (response.body() != null) {
                        GetAilmentData ailmentData = response.body();


                        // Check if ailment is null
                        if (ailmentData.ailments != null) {
                            String description = ailmentData.ailments.description;

                            // Check if description is null
                            if (description != null) {
                                // Set the arguments with the description
                                SetScreenArguments.getScreenElements getScreenElements = new SetScreenArguments.getScreenElements();
                                getScreenElements.setArguments(description);
                            } else {
                                Log.e("ApiModule", "Description is null");
                            }
                        } else {
                            Log.e("ApiModule", "Ailment is null");
                        }
                    } else {
                        Log.e("ApiModule", "Response body is null");
                    }
                }

                @Override
                public void onFailure(Call<GetAilmentData> call, Throwable t) {
                    Log.e("ApiModule", "Request failed: " + t.getMessage());
                }
            });
        }


        public interface getAilment {
            @GET("https://mhw-db.com/ailments/{id}")
            Call<GetAilmentData> GET_AILMENT_DATA_CALL(@Path("id") String id);
        }

    }
}
