package com.example.studioghibliapp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.database.MovieDataFav;
import com.example.database.StudioGhMovies;
import com.example.network.ApiClient;
import com.example.network.GetDataFilms;
import com.example.yoursong.R;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE_FAV = "EXTRA_MOVIE_FAV";
    private static final String URL = "https://ghibliapi.dev";
    private static List<StudioGhMovies> ApiData = new ArrayList<>();
    private static ArrayList<MovieDataFav> movieFav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ParseInstallation.getCurrentInstallation().saveInBackground();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getApiClient();
    }


    private void getApiClient() {
        Retrofit retrofit = ApiClient.getApiRetrofit(URL);
        GetDataFilms DataFilms = retrofit.create(GetDataFilms.class);
        Call<List<StudioGhMovies>> studioGhMoviesCall = DataFilms.GET_AILMENT_DATA_CALL();
        studioGhMoviesCall.enqueue(new Callback<List<StudioGhMovies>>() {
            @Override
            public void onResponse(Call<List<StudioGhMovies>> call, Response<List<StudioGhMovies>> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "Successful: " + response.message());
                    ApiData = response.body();
                    getDataParse(ApiData);
                    recyclerViewMovies();



                } else {
                    Log.e(TAG, "Error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<StudioGhMovies>> call, Throwable throwable) {
                Log.e(TAG, "Error: " + throwable.getMessage());
            }
        });
    }

    private void recyclerViewMovies() {
        Drawable bannerDrawable = ContextCompat.getDrawable(this, R.drawable.studio_ghibli_logo);
        RecyclerViewMain recyclerViewSetup = new RecyclerViewMain(ApiData, bannerDrawable);
        RecyclerView recyclerView = this.findViewById(R.id.recycler_layout);
        recyclerView.setAdapter(recyclerViewSetup);

    }

    private void getDataParse(List<StudioGhMovies> data) {
        movieFav = new ArrayList<>(data.size());


        for (int i = 0; i < data.size(); i++) {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Movies");
            query.whereEqualTo("nameMovie", data.get(i).getOriginal_title_romanised());
            query.findInBackground((object, e) -> {
                if (e == null) {
                    for (ParseObject movieObject : object) {
                        MovieDataFav movieData = new MovieDataFav();
                        movieData.setMovieKey(movieObject.getObjectId());
                        movieData.setMovieName(movieObject.getString("nameMovie"));
                        movieFav.add(movieData);

                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            });
            intentMovie(movieFav);
        }
    }

    private void intentMovie(ArrayList<MovieDataFav> movieFav) {
        MovieActivity.createIntentForFavorites(this, movieFav);
    }

}