package com.example.studioghibliapp;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.database.StudioGhMovies;
import com.example.network.ApiClient;
import com.example.network.GetDataFilms;
import com.example.yoursong.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "https://ghibliapi.dev";
    private static List<StudioGhMovies> ApiData = new ArrayList<>();
    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getApiClient();

    }


    private void getApiClient() {
        Retrofit retrofit = ApiClient.getApiRetrofit(URL);
        GetDataFilms DataFilms = retrofit.create(GetDataFilms.class);
        Call<List<StudioGhMovies>> studioGhMoviesCall = DataFilms.GET_AILMENT_DATA_CALL();
        studioGhMoviesCall.enqueue(new Callback<List<StudioGhMovies>>() {
            @Override
            public void onResponse(Call<List<StudioGhMovies>> call, Response<List<StudioGhMovies>> response) {
                if (response.isSuccessful()) {
                    ApiData = response.body();
                    Log.i(TAG, "Successful: " + response.message());
                    recyclerView();
                } else {
                    Log.e(TAG, "Error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<StudioGhMovies>> call, Throwable throwable) {
                Log.e(TAG, "Error: " + throwable.getMessage());
            }
        });
    }

    private void recyclerView() {
        RecyclerViewMain recyclerViewSetup = new RecyclerViewMain(ApiData);
        RecyclerView recyclerView = this.findViewById(R.id.recycler_layout);
        recyclerView.setAdapter(recyclerViewSetup);
    }


}