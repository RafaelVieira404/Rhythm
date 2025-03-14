package com.example.network;

import com.example.database.StudioGhMovies;
import com.example.database.StudioGhPeople;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface GetDataPeople {
    @GET("/people/{id}")
    Call<StudioGhPeople> GET_DATA_PEOPLE(@Path("id") String id);
}
