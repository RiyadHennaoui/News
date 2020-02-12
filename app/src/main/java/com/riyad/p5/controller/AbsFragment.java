package com.riyad.p5.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.riyad.p5.R;
import com.riyad.p5.data.model.ui.Article;
import com.riyad.p5.utils.RetrofitConstant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class AbsFragment extends Fragment {

    protected void setNewArticleList (List<Article> articles){
        mData = articles;
        mAdapter.setData(articles);
    }

    private final String SAVED_INSTANCE_STATE_KEY = "SAVED_INSTANCE_STATE_KEY";
    private MainAdapter mAdapter ;
    private List<Article> mData = new ArrayList<>();
    protected NewYorkTimesAPI service;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.article_layout, container,false);
        initRetrofitService();
        RecyclerView myRecyclerView = myView.findViewById(R.id.rv_article);
        mAdapter =  new MainAdapter(getContext());

        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myRecyclerView.setAdapter(mAdapter);

        if (savedInstanceState != null) {
            mAdapter.setData(savedInstanceState.getParcelableArrayList(SAVED_INSTANCE_STATE_KEY));
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

        savedInstanceState.putParcelableArrayList(SAVED_INSTANCE_STATE_KEY, new ArrayList<>(mData));
    }

    private void initRetrofitService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         service = retrofit.create(NewYorkTimesAPI.class);
    }

    public abstract String getTitle();
}
