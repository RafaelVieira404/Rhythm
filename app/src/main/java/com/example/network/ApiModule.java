package com.example.network;

import android.util.Log;

import com.example.yoursong.GetAilmentData;
import com.example.yoursong.SetScreenArguments;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class ApiModule {

    public static class retrofit {

        public interface GetAilment  {
            @GET("ailments/{id}")
            Call<GetAilmentData> GET_AILMENT_DATA_CALL(@Path("id") String test);
        }

        public void getApiClient() {
            Retrofit retrofit = ApiClient.getApiRetrofit();
            GetAilment getAilment = retrofit.create(GetAilment.class);
            getAilment.GET_AILMENT_DATA_CALL("5").enqueue(new Callback<GetAilmentData>() {
                @Override
                public void onResponse(Call<GetAilmentData> call, Response<GetAilmentData> response) {
                    if (response.body() != null) {
                        GetAilmentData getAilmentData = response.body();
                        String name = getAilmentData.getName();
                        SetScreenArguments.setScreen(name);

                    }
                }

                @Override
                public void onFailure(Call<GetAilmentData> call, Throwable throwable) {

                }

            } );
        }
    }
}
