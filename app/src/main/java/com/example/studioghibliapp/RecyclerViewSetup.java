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
import com.squareup.picasso.Transformation;

import java.util.List;


public class RecyclerViewSetup extends RecyclerView.Adapter<RecyclerViewSetup.RecyclerAdapterViewHolder> {

    private List<StudioGhMovies> localDataset;

    public RecyclerViewSetup(List<StudioGhMovies> apiData) {
        localDataset = apiData;
    }

    public String setInfo(String releaseDate, int runningTime, String rtScore) {
        String string = String.format("Release: %s    Time: %d  \nScoreRT: ", releaseDate, runningTime) + rtScore + "%";
        return string;
    }
    public String setDescription(String description) {
        int end = description.length();
        String string;
        if (end > 185) {
            string = String.format("Description: %s...", description.substring(0, 185));
        } else {
            string = String.format("Description: %s", description.substring(0, end));
        }
        return string;

    }

    public void setImageView(StudioGhMovies data,  ImageView imageView) {
        Picasso.get().load(data.getImage()).transform(new RoundCornerPicasso(30,0)).into(imageView);
    }

    @Override
    public RecyclerAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vertical_recycler_view, parent, false);
        return new RecyclerAdapterViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterViewHolder holder, final int position) {
        StudioGhMovies data = localDataset.get(position);
        holder.bind(data);

    }

    @Override
    public int getItemCount() {
        return localDataset.size();
    }

    class RecyclerAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView movie_title_original;
        TextView movie_title_romanised;
        TextView movie_info;
        TextView movie_description;
        ImageView movie_image;

        public RecyclerAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_title_original = itemView.findViewById(R.id.original_title);
            movie_title_romanised = itemView.findViewById(R.id.title);
            movie_info = itemView.findViewById(R.id.movie_info);
            movie_image = itemView.findViewById(R.id.image_movie);
            movie_description = itemView.findViewById(R.id.movie_description);

        }

        public void bind(StudioGhMovies data) {
            movie_title_romanised.setText(data.getOriginal_title_romanised());
            movie_title_original.setText(data.getOriginal_title());
            movie_info.setText(setInfo(data.getRelease_date(), data.getRunning_time(), data.getRt_score()));
            movie_description.setText(setDescription(data.getDescription()));
            setImageView(data, movie_image);

        }
    }
}

