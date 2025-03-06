package com.example.studioghibliapp;

import android.view.LayoutInflater;
import android.view.RoundedCorner;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.database.StudioGhMovies;
import com.example.yoursong.R;
import com.google.android.material.shape.RoundedCornerTreatment;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

public class RecyclerViewSetup extends RecyclerView.Adapter<RecyclerViewSetup.RecyclerAdapterViewHolder> {

    private List<StudioGhMovies> localDataset;

    public RecyclerViewSetup(List<StudioGhMovies> apiData) {
        localDataset = apiData;
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
        ImageView movie_Banner;

        public RecyclerAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_title_original = itemView.findViewById(R.id.original_title);
            movie_title_romanised = itemView.findViewById(R.id.title_1);
            movie_Banner = itemView.findViewById(R.id.image_movie);
        }

        public void bind(StudioGhMovies data) {
            movie_title_romanised.setText(data.getOriginal_title_romanised());
            movie_title_original.setText(data.getOriginal_title());
        }
    }


}

