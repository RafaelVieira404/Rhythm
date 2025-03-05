package com.example.studioghibliapp;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.database.StudioGhMovies;
import com.example.yoursong.R;

import java.util.List;

public class SetScreenArguments {

    TextView textView;
    public void setTextView(TextView textView) {
        this.textView = textView.findViewById(R.id.teste_1);
    }
    public void setView(List<StudioGhMovies> ghMovies) {
        textView.setText(ghMovies.get(0).getOriginal_title());

    }


}
