package com.example.yoursong;

import android.widget.TextView;

public class SetScreenArguments extends MainActivity {
    public static class getScreenElements {
        TextView teste1;

        public void setArguments (String string){
            getScreenElements getScreenElements = new getScreenElements();
            getScreenElements.teste1.findViewById(R.id.teste_1);
            teste1.setText(string);
        }
    }


}
