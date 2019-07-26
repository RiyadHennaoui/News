package com.riyad.p5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.riyad.p5.model.MostPopularArticle;
import com.riyad.p5.model.MostPopularResult;
import com.riyad.p5.model.TopStoriesArticle;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MostPopularFragment extends Fragment {

    private MainAdapter mAdapter = new MainAdapter();
    private ArrayList<MostPopularArticle> mData;

    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.article_layout, container,false);

        RecyclerView myRecyclerView = myView.findViewById(R.id.rv_article);

        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myRecyclerView.setAdapter(mAdapter);

        if (savedInstanceState == null) {
            Toast.makeText(getContext(), "This is the first time I'm launched", Toast.LENGTH_SHORT)
                    .show();

            reload();
        } else {
            // TODO RIYAD RESTORE STATE
            mAdapter.setDataTopStories(savedInstanceState.<TopStoriesArticle>getParcelableArrayList("toto"));

            Toast.makeText(getContext(), "This is not my first rodeo", Toast.LENGTH_SHORT).show();
        }


        return myView;
    }


    private void reload() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewYorkTimesAPI service =
                retrofit.create(NewYorkTimesAPI.class);

        Call<MostPopularResult> call = service.getMostPopular("vWAeWal4GLoISnnu5K7KvoMQ26nBhVW5");

        call.enqueue(new Callback<MostPopularResult>() {
            @Override
            public void onResponse(Call<MostPopularResult> call, Response<MostPopularResult> response) {

                onNewData(response.body());

            }

            @Override
            public void onFailure(Call<MostPopularResult> call, Throwable t) {

                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void onNewData(@Nullable MostPopularResult body) {
        if (body != null && !CollectionUtils.isEmpty(body.getResults()))  {
            mData = new ArrayList<>(body.getNumResults());

            mAdapter.setDataMostPopular(body.getResults());
        }
    }

}
