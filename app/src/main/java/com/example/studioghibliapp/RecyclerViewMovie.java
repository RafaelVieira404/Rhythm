package com.example.studioghibliapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.database.StudioGhMovies;
import com.example.yoursong.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewMovie extends RecyclerView.Adapter<RecyclerViewMovie.RecyclerAdapterViewHolder> {

    private List<StudioGhMovies> localDataset;

    public RecyclerViewMovie(List<StudioGhMovies> apiData) {
        localDataset = apiData;
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
        StudioGhMovies data = localDataset.get(position);
        holder.bind(data);

    }

    @Override
    public int getItemCount() {
        return localDataset.size();
    }

    class RecyclerAdapterViewHolder extends RecyclerView.ViewHolder {


        public RecyclerAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        public void bind(StudioGhMovies data) {




        }
    }
}