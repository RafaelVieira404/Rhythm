package com.example.network;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient implements Serializable {

    public static Retrofit retrofit;

    public static Retrofit getApiRetrofit(final String BASE_URL) {
        Gson gson = new GsonBuilder().create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client())
                .build();
    }

    private static OkHttpClient client() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @NonNull
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request request = chain.request();  // Get the request
                        // Proceed with the request
                        Response response = chain.proceed(request);
                        // Check if the response is successful
                        if (!response.isSuccessful()) {
                            // Log error details if response is not successful
                            logError(response);
                        }
                        return response;
                    }
                    // Log error details when response is not successful
                    private void logError(Response response) {
                        try {
                            // Log HTTP status code and response body (if available)
                            String errorMessage = "Request failed with code: " + response.code()
                                    + ", message: " + response.message();
                            if (response.body() != null) {
                                errorMessage += ", response body: " + response.body().string();
                            }
                            // You can use Log.e() for error logging
                            Log.e("API_ERROR", errorMessage); // You can replace with your logging method
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        return okHttpClient;
    }
}