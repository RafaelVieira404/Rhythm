package com.example.studioghibliapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.database.StudioGhMovies;
import com.example.yoursong.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewMovie extends RecyclerView.Adapter<RecyclerViewMovie.RecyclerAdapterViewHolder> {

    public static final  String EXTRA_MOVIE_DATA = "EXTRA_MOVIE_DATA";

    private List<StudioGhMovies> apiDataMovie;

    public RecyclerViewMovie(List<StudioGhMovies> apiData) {
        apiDataMovie = apiData;
    }


    public void setImageView(StudioGhMovies data,  ImageView imageView) {
        Picasso.get().load(data.getImage()).transform(new RoundCornerPicasso(30,0)).into(imageView);
    }

    @Override
    public RecyclerAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_movie_info, parent, false);
        return new RecyclerAdapterViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterViewHolder holder, final int position) {
        StudioGhMovies data = apiDataMovie.get(position);
        holder.bind(data);

    }

    @Override
    public int getItemCount() {
        return apiDataMovie.size();
    }

    class RecyclerAdapterViewHolder extends RecyclerView.ViewHolder {


        public RecyclerAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ImageView movieBanner = itemView.findViewById(R.id.image_banner);
            ImageView movieCover = itemView.findViewById(R.id.movie_cover);
            TextView movieTitle = itemView.findViewById(R.id.title_movie_info);
            TextView movieOriginalTitle = itemView.findViewById(R.id.original_movie_title);
            TextView movieDescription = itemView.findViewById(R.id.movie_description_info);
            TextView movieDirector = itemView.findViewById(R.id.toolbar_text_one);
            TextView movieProducer = itemView.findViewById(R.id.toolbar_text_two);
            TextView movieRottenTomatoScore = itemView.findViewById(R.id.toolbar_text_three);

        }

        public void bind(StudioGhMovies data) {




        }
    }
}