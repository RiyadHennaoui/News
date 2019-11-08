package com.riyad.p5.controller;

import android.widget.Toast;

import com.riyad.p5.data.model.MostPopularArticle;
import com.riyad.p5.data.model.MostPopularResult;
import com.riyad.p5.data.model.ui.Article;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MostPopularFragment extends AbsFragment {
    private Call<MostPopularResult> call;

    @Override
    protected void reload() {


        call = service.getMostPopular( "vWAeWal4GLoISnnu5K7KvoMQ26nBhVW5");

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

    @Override
    public void onPause() {
        super.onPause();
        call.cancel();
    }

    @Override
    public String getTitle() {
        return "Most Popular";
    }

    private List<Article> mapResult(MostPopularResult mostPopularResult) {

        List<Article> articles = new ArrayList<>();

        if (!CollectionUtils.isEmpty(mostPopularResult.getResults())) {
            for (MostPopularArticle mostPopularArticle : mostPopularResult.getResults()) {
                String imageUrl = mostPopularArticle.getMedia().get(0).getMediaMetadata().get(0).getUrl();
                articles.add(new Article(mostPopularArticle.getTitle(),
                        mostPopularArticle.getPublishedDate(),
                        mostPopularArticle.getSection(),
                        imageUrl, mostPopularArticle.getAbstract(),
                        mostPopularArticle.getUrl()));
            }
        }
        return articles;
    }

}
