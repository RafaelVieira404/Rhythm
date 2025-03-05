package com.example.network;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.database.StudioGhMovies;
import com.example.studioghibliapp.SetScreenArguments;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class ApiModule {

    public static class retrofit {

        public interface GetAilment  {
            @GET("/films")
            Call<List<StudioGhMovies>> GET_AILMENT_DATA_CALL();
        }

        public void getApiClient() {
            Retrofit retrofit = ApiClient.getApiRetrofit();
            GetAilment getAilment = retrofit.create(GetAilment.class);
            Call<List<StudioGhMovies>> studioGhMoviesCall = getAilment.GET_AILMENT_DATA_CALL();
            studioGhMoviesCall.enqueue(new Callback<List<StudioGhMovies>>() {
                @Override
                public void onResponse(Call<List<StudioGhMovies>> call, Response<List<StudioGhMovies>> response) {
                    if(response.isSuccessful()) {
                        List<StudioGhMovies> studioGhMovies = response.body();

                        for (StudioGhMovies studioGh : studioGhMovies) {
                            Log.i(TAG, String.format("%s " + "  %s", studioGh.getId(), studioGh.getOriginal_title()));
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<StudioGhMovies>> call, Throwable throwable) {
                    Log.e(TAG,"Error: " + throwable.getMessage());
                }
            });
        }
    }
}
