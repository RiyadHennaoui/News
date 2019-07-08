package com.riyad.p5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textview = findViewById(R.id.main_tv);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewYorkTimesAPI service =
                retrofit.create(NewYorkTimesAPI.class);

        Call<TopStoriesResult> call = service.getTopStories("business", "vWAeWal4GLoISnnu5K7KvoMQ26nBhVW5");

        call.enqueue(new Callback<TopStoriesResult>() {
            @Override
            public void onResponse(Call<TopStoriesResult> call, Response<TopStoriesResult> response) {

                textview.setText(response.body().getResults().get(0).getTitle());

            }

            @Override
            public void onFailure(Call<TopStoriesResult> call, Throwable t) {

                textview.setText(t.getLocalizedMessage());

            }
        });
    }
}
