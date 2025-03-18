package com.example.studioghibliapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.database.StudioGhMovies;
import com.example.yoursong.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RecyclerViewMain extends RecyclerView.Adapter<RecyclerViewMain.RecyclerAdapterViewHolder> {

    private static final int VIEW_TYPE_BANNER = 0;
    private static final int VIEW_TYPE_MOVIE = 1;
    public static final String EXTRA_MOVIE_DATA = "EXTRA_MOVIE_DATA";
    private List<StudioGhMovies> localDataset;
    private static final PicassoSettings picassoSettings = new PicassoSettings(0, 0);

    private final Drawable drawableBanner;

    public RecyclerViewMain(List<StudioGhMovies> apiData, Drawable bannerImage) {
        localDataset = apiData;
        drawableBanner = bannerImage;
    }

    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_BANNER;
        } else {
            return VIEW_TYPE_MOVIE;
        }
    }

    @Override
    public RecyclerAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_BANNER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_recycler_image, parent, false);
            return new RecyclerAdapterViewHolder(view, true);

        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_recycler_view, parent, false);
            return new RecyclerAdapterViewHolder(view, false);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterViewHolder holder, final int position) {

        if (getItemViewType(position) == VIEW_TYPE_BANNER) {
            holder.bindBanner(drawableBanner);
        } else {
            StudioGhMovies data = localDataset.get(position - 1);
            holder.bindMovie(data);
        }
    }

    @Override
    public int getItemCount() {
        return localDataset.size() + 1;
    }

    class RecyclerAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView movie_title_original;
        TextView movie_title_romanised;
        TextView movie_info;
        ImageView movie_image;
        LinearLayout cardView;
        ImageView bannerRecyclerView;

        private Boolean isBanner;

        public RecyclerAdapterViewHolder(@NonNull View itemView, boolean isBanner) {
            super(itemView);
            this.isBanner = isBanner;

            if (isBanner) {
                bannerRecyclerView = itemView.findViewById(R.id.banner_image);
            } else {
                movie_title_original = itemView.findViewById(R.id.original_title);
                movie_title_romanised = itemView.findViewById(R.id.title);
                movie_info = itemView.findViewById(R.id.movie_info);
                movie_image = itemView.findViewById(R.id.image_movie);
                cardView = itemView.findViewById(R.id.card_view);
            }
        }

        public void bindBanner(Drawable bannerImage) {
            if (bannerRecyclerView != null) {
                bannerRecyclerView.setImageResource(R.drawable.logo_studio_com_filmes);
            }
        }

        public void bindMovie(StudioGhMovies data) {


            movie_title_romanised.setText(data.getOriginal_title_romanised());
            movie_title_original.setText(data.getOriginal_title());
            movie_info.setText(data.setInfoText(data.getRelease_date(), data.getRunning_time(), data.getRt_score()));

            if (movie_image.getTag() != null) {
                picassoSettings.loadImageIntoContainer(movie_image, data.getImage(), new PicassoSettings(15, 0));
            } else {
                movie_image.setImageDrawable(null);
                picassoSettings.loadImageIntoContainer(movie_image, data.getImage(), new PicassoSettings(15, 0));
            }

            cardView.setOnClickListener(v -> {
                Intent intent = MovieActivity.createIntentToMovieInfo(itemView.getContext(), data);
                itemView.getContext().startActivity(intent);
            });
        }
    }
}

