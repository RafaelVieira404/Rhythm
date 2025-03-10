package com.example.network;

import com.example.database.StudioGhMovies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataFilms {
    @GET("/films")
    Call<List<StudioGhMovies>> GET_AILMENT_DATA_CALL();
}
