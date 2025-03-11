package com.example.studioghibliapp;


import static com.example.studioghibliapp.RecyclerViewMovie.EXTRA_MOVIE_DATA;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends AppCompatActivity {
    private static final MovieActivity movieActivity = new MovieActivity();
    private static List<StudioGhMovies> apiDataMovie = new ArrayList<>();
    private static final int INDEX = 0;

    public static Intent createIntentToEndGame(Context context, StudioGhMovies ghMovies) {
        return new Intent(context, MovieActivity.class)
                .putExtra(RecyclerViewMain.EXTRA_MOVIE_DATA, ghMovies);

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
    }

    public class CropSquareTransformation implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
            if (result != source) {
                source.recycle();
            }
            return result;
        }

        @Override
        public String key() {
            return "square()";
        }
    }

    private void setupView(List<StudioGhMovies> data, int INDEX) {
        CropSquareTransformation cropSquareTransformation = new CropSquareTransformation();

        ImageView movieBanner = findViewById(R.id.image_banner);
        ImageView movieCover = findViewById(R.id.movie_cover);
        TextView movieTitle = findViewById(R.id.title_movie_info);
        TextView movieOriginalTitle = findViewById(R.id.original_movie_title);
        TextView movieInfo = findViewById(R.id.movie_description_info);
        TextView movieDirector = findViewById(R.id.toolbar_text_one);
        TextView movieProducer = findViewById(R.id.toolbar_text_two);

        movieTitle.setText(data.get(INDEX).getTitle());
        movieOriginalTitle.setText(data.get(INDEX).getOriginal_title());
        movieInfo.setText(data.get(INDEX).setInfoText(data.get(INDEX).getRelease_date(), data.get(INDEX).getRunning_time(), data.get(INDEX).getRt_score()));
        movieDirector.setText(data.get(INDEX).getDirector());
        movieProducer.setText(data.get(INDEX).getProducer());

        Picasso.get().load(data.get(INDEX).getImage()).into(movieCover);

        movieBanner.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                int width = movieBanner.getWidth();
                int height = movieBanner.getHeight();

                Picasso.get().load(data.get(INDEX).getMovie_banner())
                        .resize(width, height)
                        .into(movieBanner);
                movieBanner.removeOnLayoutChangeListener(this);


            }
        });


    }

}
