package com.riyad.p5.controller;

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

import com.riyad.p5.R;
import com.riyad.p5.data.model.ui.Article;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class AbsFragment extends Fragment {

    protected void setNewArticleList (List<Article> articles){

        mData = articles;
        mAdapter.setData(articles);

    }

    private MainAdapter mAdapter ;
    private List<Article> mData = new ArrayList<>();
    protected NewYorkTimesAPI service;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.article_layout, container,false);
        init();
        RecyclerView myRecyclerView = myView.findViewById(R.id.rv_article);
        mAdapter =  new MainAdapter(getContext());

        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myRecyclerView.setAdapter(mAdapter);

        if (savedInstanceState != null) {
            // TODO RIYAD RESTORE STATE and change toto
            mAdapter.setData(savedInstanceState.getParcelableArrayList("toto"));


        }


        return myView;
    }

    @Override
    public void onResume() {
        super.onResume();
        reload();
    }

    protected abstract void reload();


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // TODO RIYAD SAVE COLLECTION OF ITEMS and change toto
        savedInstanceState.putParcelableArrayList("toto", new ArrayList<>(mData));
    }

    private void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         service = retrofit.create(NewYorkTimesAPI.class);

    }


    public abstract String getTitle();
}
