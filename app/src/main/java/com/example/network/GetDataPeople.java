package com.example.network;

import com.example.database.StudioGhMovies;
import com.example.database.StudioGhPeople;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataPeople {
    Call<List<StudioGhPeople>> GET_DATA_PEOPLE();
}
