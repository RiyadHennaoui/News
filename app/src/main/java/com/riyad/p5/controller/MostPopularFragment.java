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
    private String API_KEY = "vWAeWal4GLoISnnu5K7KvoMQ26nBhVW5";
    private String mostPopularTitle = "Most Popular";

    @Override
    protected void reload() {

        if (call != null) {
            call.isCanceled();
        }

        call = service.getMostPopular(API_KEY);

        //Call New York Times API
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
        return mostPopularTitle;
    }

    // Map response of articles
    private List<Article> mapResult(MostPopularResult mostPopularResult) {
        List<Article> articles = new ArrayList<>();
        String extentionDateFormat = "T05:00:12-05:00";

        if (!CollectionUtils.isEmpty(mostPopularResult.getResults())) {
            for (MostPopularArticle mostPopularArticle : mostPopularResult.getResults()) {
                if (mostPopularArticle.getMedia() != null && !mostPopularArticle.getMedia().isEmpty()) {

                    String imageUrl = mostPopularArticle.getMedia().get(0).getMediaMetadata().get(
                            mostPopularArticle.getMedia().get(0).getMediaMetadata().size() - 1
                    ).getUrl();

                    articles.add(new Article(mostPopularArticle.getTitle(),
                            //Add extention date format for the API
                            mostPopularArticle.getPublishedDate() + extentionDateFormat,
                            mostPopularArticle.getSection(),
                            imageUrl, mostPopularArticle.getAbstract(),
                            mostPopularArticle.getUrl()));
                }
            }
        }
        return articles;
    }
}
