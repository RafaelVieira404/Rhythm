package com.example.studioghibliapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.database.StudioGhMovies;
import com.example.database.StudioGhPeople;
import com.example.yoursong.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewMovie extends RecyclerView.Adapter<RecyclerViewMovie.RecyclerAdapterViewHolder> {

    public static final  String EXTRA_MOVIE_DATA = "EXTRA_MOVIE_DATA";

    private List<StudioGhPeople> apiDataMovie;

    public RecyclerViewMovie(List<StudioGhPeople> apiData) {
        apiDataMovie = apiData;
    }

    @Override
    public RecyclerAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_movie_info, parent, false);
        return new RecyclerAdapterViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterViewHolder holder, final int position) {
        StudioGhPeople data = apiDataMovie.get(position);
        holder.bind(data);

    }

    @Override
    public int getItemCount() {
        return apiDataMovie.size();
    }

    class RecyclerAdapterViewHolder extends RecyclerView.ViewHolder {

        ImageView peopleCover;
        TextView peopleName;
        TextView peopleInfo;

        public RecyclerAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            peopleCover = itemView.findViewById(R.id.image_people);
            peopleName = itemView.findViewById(R.id.people_name);
            peopleInfo = itemView.findViewById(R.id.people_info);

        }

        public void bind(StudioGhPeople data) {
            peopleName.setText(data.getName());
            peopleInfo.setText(data.setInfoText(data.getGender(), data.getAge(), data.getHairColor(), data.getEyeColor()));
        }
    }
}