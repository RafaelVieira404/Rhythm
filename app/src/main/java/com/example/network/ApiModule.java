package com.example.network;

import com.example.database.StudioGhMovies;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class ApiModule {

    public static class retrofit {

        public interface GetAilment  {
            @GET("/{id}/")
            Call<StudioGhMovies> GET_AILMENT_DATA_CALL(@Path("id") String test);
        }

        public void getApiClient() {
            Retrofit retrofit = ApiClient.getApiRetrofit();
            GetAilment getAilment = retrofit.create(GetAilment.class);
            getAilment.GET_AILMENT_DATA_CALL("films").enqueue(new Callback<StudioGhMovies>() {
                @Override
                public void onResponse(Call<StudioGhMovies> call, Response<StudioGhMovies> response) {
                    if (response.body() != null) {
                        StudioGhMovies studioGhMovies = new StudioGhMovies(
                                response.body().getId(), response.body().getTitle(),
                                response.body().getOriginalTitle(), response.body().getOriginalTitleRomanticised(),
                                response.body().getDescription(), response.body().getDirector(),
                                response.body().getProducer(), response.body().getReleaseDate(),
                                response.body().getRunningTime(), response.body().getRtScore(),
                                response.body().getPeopleUrl()
                        );

                        System.out.println(studioGhMovies);

                    }

                }

                @Override
                public void onFailure(Call<StudioGhMovies> call, Throwable throwable) {

                }

            } );
        }
    }
}
