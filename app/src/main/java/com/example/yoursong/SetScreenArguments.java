package com.example.yoursong;

import android.widget.TextView;

public class SetScreenArguments extends MainActivity{
    public static class getScreenElements {
        TextView test;


    }

    public static void setScreen(String s) {
        getScreenElements getScreenElements = new getScreenElements();
        getScreenElements.test.setText(s);

    }

}
