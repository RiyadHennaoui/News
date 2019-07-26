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

import com.riyad.p5.data.model.MostPopularArticle;
import com.riyad.p5.data.model.MostPopularResult;
import com.riyad.p5.data.model.TopStoriesArticle;
import com.riyad.p5.data.model.TopStoriesResult;
import com.riyad.p5.ui.Article;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MostPopularFragment extends AbsFragment {


    @Override
    protected void reload() {


        Call<MostPopularResult> call = service.getMostPopular( "vWAeWal4GLoISnnu5K7KvoMQ26nBhVW5");

        call.enqueue(new Callback<MostPopularResult>() {
            @Override
            public void onResponse(Call<MostPopularResult> call, Response<MostPopularResult> response) {

                if (response.body() != null) {

                    setNewArticleList(mapResult(response.body()));
                }

            }

            @Override
            public void onFailure(Call<MostPopularResult> call, Throwable t) {

                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private List<Article> mapResult(MostPopularResult mostPopularResult) {

        List<Article> articles = new ArrayList<>();

        if (!CollectionUtils.isEmpty(mostPopularResult.getResults())) {
            for (MostPopularArticle mostPopularArticle : mostPopularResult.getResults()) {
                String imageUrl = mostPopularArticle.getMedia().get(0).getMediaMetadata().get(0).getUrl();
                articles.add(new Article(mostPopularArticle.getTitle(),
                        mostPopularArticle.getPublishedDate(),
                        mostPopularArticle.getColumn(),
                        imageUrl, mostPopularArticle.getAbstract()));
            }
        }
        return articles;
    }

}
