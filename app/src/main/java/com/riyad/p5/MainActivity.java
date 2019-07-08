package com.riyad.p5;

import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private MainAdapter mAdapter;
    private ArrayList<Result> mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.main_rv_articles);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MainAdapter();

        recyclerView.setAdapter(mAdapter);

        if (savedInstanceState == null) {
            Toast.makeText(this, "This is the first time I'm launched", Toast.LENGTH_SHORT).show();

            reload();
        } else {
            // TODO RIYAD RESTORE STATE
            mAdapter.setData(savedInstanceState.<Result>getParcelableArrayList("toto"));

            Toast.makeText(this, "This is not my first rodeo", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // TODO RIYAD SAVE COLLECTION OF ITEMS
        savedInstanceState.putParcelableArrayList("toto", mData);
    }

    private void reload() {
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

                onNewData(response.body());

            }

            @Override
            public void onFailure(Call<TopStoriesResult> call, Throwable t) {

                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void onNewData(@Nullable TopStoriesResult body) {
        if (body != null && !CollectionUtils.isEmpty(body.getResults()))  {
            mData = new ArrayList<>(body.getResults());

            mAdapter.setData(body.getResults());
        }
    }
}
