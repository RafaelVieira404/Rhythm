package com.example.studioghibliapp;


import static android.content.ContentValues.TAG;
import static com.example.studioghibliapp.RecyclerViewMovie.EXTRA_MOVIE_DATA;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.database.StudioGhMovies;
import com.example.database.StudioGhPeople;
import com.example.network.ApiClient;
import com.example.network.GetDataPeople;
import com.example.yoursong.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieActivity extends AppCompatActivity {
    private static PicassoSettings picassoSettings = new PicassoSettings(0,0);
    private static final String URL = "https://ghibliapi.dev";
    private static List<StudioGhMovies> apiDataMovie = new ArrayList<>();
    private static String[] favoriteMovieSelected = new String[5];
    StudioGhPeople[] studioGhPeople;
    private static int arraySize = 0;

    public static Intent createIntentToMovieInfo(Context context, StudioGhMovies ghMovies) {
        return new Intent(context, MovieActivity.class)
                .putExtra(RecyclerViewMain.EXTRA_MOVIE_DATA, ghMovies);
    }

    private static String[] setPeopleDataURL() {
        arraySize = apiDataMovie.get(0).getPeople().size();
        String[] peopleDataURL = new String[arraySize];
        for (int i = 0; i < apiDataMovie.get(0).getPeople().size(); i += 1) {
            if (apiDataMovie.get(0).getPeople().get(i).equals("https://ghibliapi.dev/people/")) {
                break;

            } else {
                peopleDataURL[i] = String.valueOf((removeStringUrl(apiDataMovie.get(0).getPeople().get(i))));
                Log.println(Log.INFO, TAG, String.valueOf(apiDataMovie.get(0).getPeople().get(i)));
            }
        }
        return peopleDataURL;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movie_info);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.movie_info_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        apiDataMovie.add(0, intent.getParcelableExtra(EXTRA_MOVIE_DATA));
        getPeopleData(setPeopleDataURL());
        setupView(apiDataMovie, 0);

    }

    private boolean apiVerify(StudioGhPeople[] studioGhPeople) {
        for (int i = 0; i < studioGhPeople.length; i +=1) {
            if (studioGhPeople[i] == null) {
                return false;
            }
        }
        return true;
    }

    private void setupView(List<StudioGhMovies> data, int index) {

        ImageView movieBanner = findViewById(R.id.image_banner);
        ImageView movieCover = findViewById(R.id.movie_cover);
        TextView movieTitle = findViewById(R.id.title_movie_info);
        TextView movieOriginalTitle = findViewById(R.id.original_movie_title);
        TextView movieInfo = findViewById(R.id.movie_description_info);
        TextView movieDescription = findViewById(R.id.movie_description);
        TextView title_recycler_one = findViewById(R.id.title_recycler_view_one);
        FloatingActionButton favoriteMovie = findViewById(R.id.floating_favorite);

        movieDescription.setText(data.get(index).getDescription());
        movieTitle.setText(data.get(index).getTitle());
        movieOriginalTitle.setText(data.get(index).getOriginal_title());
        movieInfo.setText(data.get(index).setInfoText(data.get(index).getRelease_date(), data.get(index).getRunning_time(), data.get(index).getRt_score()));
        title_recycler_one.setText("Teste");

        String director = "<b>" + "Director: " + data.get(index).getDirector() + "<b>" + " Producer: " + data.get(index).getProducer();
        movieInfo.setText(Html.fromHtml(director, 0));


        picassoSettings.loadImageIntoContainer(movieCover, data.get(index).getImage(), new PicassoSettings(15, 1));
        picassoSettings.loadImageIntoContainer(movieBanner, data.get(index).getMovie_banner(), new PicassoSettings(0,0));

        favoriteMovie.setOnClickListener(v -> {
//                if (validateFavorite(data, index)) {
//                    favoriteMovie.getDrawable().setTint(Color.parseColor("#D02626"));
//
//                    for (int i = 0; i < favoriteMovieSelected.length; i++) {
//                        if (favoriteMovieSelected[i].isEmpty()) {
//                            favoriteMovieSelected[i] = data.get(index).getOriginal_title_romanised();
//                        }
//                    }
//
//
//                } else  {
//                    favoriteMovie.getDrawable().setTint(Color.parseColor("#000000"));
//                }

        });

    }

    private void recycleView() {
        RecyclerViewMovie recyclerViewSetup = new RecyclerViewMovie(studioGhPeople);
        RecyclerView recyclerView1 = this.findViewById(R.id.recycler_title_one);
        recyclerView1.setAdapter(recyclerViewSetup);

    }

    private static StringBuilder removeStringUrl(String url) {
        String target = "https://ghibliapi.dev/people/";
        int startIndex = url.indexOf(target);
        int stopIndex = startIndex + target.length();

        StringBuilder builder = new StringBuilder(url);
        if (builder.toString().contains(target)) {
            builder.delete(startIndex, stopIndex);
        }
        return builder;
    }

    private void getPeopleData(String[] link) {

        studioGhPeople = new StudioGhPeople[link.length];
        for (int i = 0; i < link.length; i += 1) {
            Retrofit retrofit = ApiClient.getApiRetrofit(URL);
            GetDataPeople getDataPeople = retrofit.create(GetDataPeople.class);
            Call<StudioGhPeople> studioGhPeopleCall = getDataPeople.GET_DATA_PEOPLE(link[i]);
            final int index = i;
            studioGhPeopleCall.enqueue(new Callback<StudioGhPeople>() {
                @Override
                public void onResponse(Call<StudioGhPeople> call, Response<StudioGhPeople> response) {
                    if (response.isSuccessful()) {
                        Log.i(TAG, "Successful: " + response.message());
                        studioGhPeople[index] = response.body();
                        if (apiVerify(studioGhPeople)) {
                            recycleView();
                        }
                    } else {
                        Log.e(TAG, "Error: " + response.code());
                        Log.e(TAG, "Error Body: " + (response.errorBody() != null ? response.errorBody().toString() : "No error body"));
                    }
                }

                @Override
                public void onFailure(Call<StudioGhPeople> call, Throwable throwable) {
                    Log.e(TAG, "Request failed: " + throwable.getMessage());
                }
            });
        }
    }

    private boolean postDataBase(List<StudioGhMovies> data, int index) {
        AtomicBoolean validation = new AtomicBoolean();

        try {
            ParseObject gameScore = new ParseObject("Movies");
            gameScore.put("nameMovie", data.get(index).getOriginal_title_romanised());
            gameScore.put("rottenTomatoesScore", data.get(index).getRt_score());
            gameScore.put("releaseDate", data.get(index).getRelease_date());
            gameScore.put("time", data.get(index).getRunning_time());
            gameScore.saveInBackground();
            validation.set(true);

        } catch (Exception e) {
            e.printStackTrace();
            validation.set(false);
        }

        return validation.get();
    }


}
