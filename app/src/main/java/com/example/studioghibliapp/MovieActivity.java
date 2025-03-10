package com.example.studioghibliapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.database.StudioGhMovies;
import com.example.yoursong.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends AppCompatActivity {

    public static final  String EXTRA_MOVIE_DATA = "EXTRA_MOVIE_DATA";
    public static List<StudioGhMovies> apiDataMovie = new ArrayList<>();
    View view;


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
        apiDataMovie = intent.getParcelableArrayListExtra(EXTRA_MOVIE_DATA);

    }

    private void setupView() {
        ImageView movieBanner = findViewById(R.id.image_banner);
        ImageView movieCover = findViewById(R.id.movie_cover);
        TextView movieTitle = findViewById(R.id.title_movie_info);
        TextView movieOriginalTitle = findViewById(R.id.original_movie_title);
        TextView movieDescription = findViewById(R.id.movie_description_info);
        TextView movieDirector = findViewById(R.id.toolbar_text_one);
        TextView movieProducer = findViewById(R.id.toolbar_text_two);
        TextView movieRottenTomatoScore = findViewById(R.id.toolbar_text_three);

        movieBanner.setImageResource(Picasso.get()
                .load(apiDataMovie.get().getMovie_banner())
                .into(movieBanner)
        );
    }




}
