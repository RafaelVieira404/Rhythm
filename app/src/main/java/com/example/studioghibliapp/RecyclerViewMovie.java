package com.example.studioghibliapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.database.StudioGhPeople;
import com.example.yoursong.R;
import com.squareup.picasso.Picasso;

public class RecyclerViewMovie extends RecyclerView.Adapter<RecyclerViewMovie.RecyclerAdapterViewHolder> {

    public static final String EXTRA_MOVIE_DATA = "EXTRA_MOVIE_DATA";
    private StudioGhPeople[] apiDataMovie;

    public RecyclerViewMovie(StudioGhPeople[] apiData) {
        apiDataMovie = apiData;
    }

    @Override
    public RecyclerAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_horizontal, parent, false);
        return new RecyclerAdapterViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterViewHolder holder, final int position) {
        StudioGhPeople data = apiDataMovie[position];
        holder.bind(data);

    }

    @Override
    public int getItemCount() {
        return apiDataMovie.length;
    }

    class RecyclerAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView peopleName;
        TextView peopleInfo;
        TextView tittle_actors;
        ImageView actor_image;

        public RecyclerAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            peopleName = itemView.findViewById(R.id.actor_name);
            peopleInfo = itemView.findViewById(R.id.actor_info);
            tittle_actors = itemView.findViewById(R.id.title_recycler_view_one);
            actor_image = itemView.findViewById(R.id.image_actor);
        }

        public void bind(StudioGhPeople data) {
            peopleName.setText(data.getName());
            peopleInfo.setText(data.setInfoText(data.getGender(), data.getAge(), data.getHair_color(), data.getEye_color()));
            actor_image.setImageResource(R.drawable.totoro);

        }
    }
}