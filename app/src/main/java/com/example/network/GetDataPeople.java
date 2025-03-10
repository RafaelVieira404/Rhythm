package com.example.network;

import com.example.database.StudioGhPeople;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataPeople {
    @GET("/{people}")
    Call<List<StudioGhPeople>> GET_PEOPLE_DATA_CALL(@Path("people") String people);
}
