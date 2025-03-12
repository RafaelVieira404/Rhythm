package com.example.studioghibliapp;


import static android.content.ContentValues.TAG;
import static com.example.studioghibliapp.RecyclerViewMovie.EXTRA_MOVIE_DATA;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieActivity extends AppCompatActivity {
    private static List<StudioGhMovies> apiDataMovie = new ArrayList<>();
    private static List<StudioGhPeople> studioGhPeople = new ArrayList<>();
    private static String[] peopleDataURL = new String[1];
    private static final int INDEX = 0;

    public static Intent createIntentToMovieInfo(Context context, StudioGhMovies ghMovies) {
        return new Intent(context, MovieActivity.class)
                .putExtra(RecyclerViewMain.EXTRA_MOVIE_DATA, ghMovies);
    }

    public static String[] getPeopleDataURL() {
        return peopleDataURL;
    }

    private static void setPeopleDataURL(String[] peopleDataURL) {
        for (int i = 0; i < apiDataMovie.get(INDEX).getPeople().size(); i+=1) {
            if (apiDataMovie.get(INDEX).getPeople().get(i) != "https://ghibliapi.dev/people/") {
                peopleDataURL[i] = String.valueOf(apiDataMovie.get(INDEX).getPeople());
                Log.println(Log.INFO, TAG, String.valueOf(apiDataMovie.get(INDEX).getPeople().get(i)));
            } else return;
        }
        MovieActivity.peopleDataURL = peopleDataURL;
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
        apiDataMovie.add(INDEX, intent.getParcelableExtra(EXTRA_MOVIE_DATA));
        setupView(apiDataMovie, INDEX);
        setPeopleDataURL(peopleDataURL);
        getPeopleData(getPeopleDataURL());
        recycleView();
    }

    private void setupView(List<StudioGhMovies> data, int index) {

        ImageView movieBanner = findViewById(R.id.image_banner);
        ImageView movieCover = findViewById(R.id.movie_cover);
        TextView movieTitle = findViewById(R.id.title_movie_info);
        TextView movieOriginalTitle = findViewById(R.id.original_movie_title);
        TextView movieInfo = findViewById(R.id.movie_description_info);
        TextView movieDirector = findViewById(R.id.toolbar_text_one);
        TextView movieProducer = findViewById(R.id.toolbar_text_two);
        TextView movieDescription = findViewById(R.id.movie_description);

        movieDescription.setText(data.get(index).getDescription());
        movieTitle.setText(data.get(index).getTitle());
        movieOriginalTitle.setText(data.get(index).getOriginal_title());
        movieInfo.setText(data.get(index).setInfoText(data.get(index).getRelease_date(), data.get(index).getRunning_time(), data.get(index).getRt_score()));

        String director = "<b>" + "Director: " + data.get(index).getDirector();
        String producer = "<b>" + "Producer: " + data.get(index).getProducer();
        movieDirector.setText(Html.fromHtml(director, 0));
        movieProducer.setText(Html.fromHtml(producer, 1));

        PicassoSettings picassoSettings = new PicassoSettings(20, 0);
        picassoSettings.loadImageIntoContainer(movieBanner, data.get(index).getMovie_banner(), picassoSettings);
        picassoSettings.loadImageIntoContainer(movieCover, data.get(index).getImage(), picassoSettings);

    }

    private void recycleView() {
        RecyclerViewMovie recyclerViewSetup = new RecyclerViewMovie(studioGhPeople);
        RecyclerView recyclerView1 = this.findViewById(R.id.recycler_title_one);
        recyclerView1.setAdapter(recyclerViewSetup);

    }

    private void getPeopleData(String[] URL) {
        if (getPeopleDataURL() != null) {
            for (int i = 0; i < URL.length; i += 1) {
                final int index = i;
                Retrofit retrofit = ApiClient.getApiRetrofit(URL[i]);
                GetDataPeople getDataPeople = retrofit.create(GetDataPeople.class);
                Call<List<StudioGhPeople>> studioGhPeopleCall = getDataPeople.GET_DATA_PEOPLE();
                studioGhPeopleCall.enqueue(new Callback<List<StudioGhPeople>>() {
                    @Override
                    public void onResponse(Call<List<StudioGhPeople>> call, Response<List<StudioGhPeople>> response) {
                        if (response.isSuccessful()) {
                            studioGhPeople.set(index, (StudioGhPeople) response.body());
                            Log.i(TAG, "Successful: " + response.message());

                        } else Log.e(TAG, "Error: " + response.errorBody());
                    }

                    @Override
                    public void onFailure(Call<List<StudioGhPeople>> call, Throwable throwable) {
                        Log.e(TAG, "Error: " + throwable.getMessage());
                    }
                });
            }
        }

    }

}
