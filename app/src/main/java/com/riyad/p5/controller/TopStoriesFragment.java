package com.riyad.p5.controller;

import android.os.Bundle;
import android.widget.Toast;

import com.riyad.p5.R;
import com.riyad.p5.data.model.TopStoriesArticle;
import com.riyad.p5.data.model.TopStoriesResult;
import com.riyad.p5.data.model.ui.Article;
import com.riyad.p5.utils.RetrofitConstant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopStoriesFragment extends AbsFragment {
    public static final String KEY_SECTION = "KEY_SECTION";
    private Call<TopStoriesResult> myCurrentCall;

    public static TopStoriesFragment newInstance(String section) {

        Bundle args = new Bundle();
        args.putString(KEY_SECTION, section);
        TopStoriesFragment fragment = new TopStoriesFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void reload() {
        if (myCurrentCall != null) {

            myCurrentCall.cancel();

        }

        //Call New York Times API
        myCurrentCall = service.getTopStories(getArguments().getString(KEY_SECTION), RetrofitConstant.API_KEY);

        myCurrentCall.enqueue(new Callback<TopStoriesResult>() {
            @Override
            public void onResponse(Call<TopStoriesResult> call, Response<TopStoriesResult> response) {

                if (response.body() != null) {

                    setNewArticleList(mapResult(response.body()));
                    swipeRefresh.setRefreshing(false);
                }

            }

            @Override
            public void onFailure(Call<TopStoriesResult> call, Throwable t) {

                Toast.makeText(getContext(), "please swipe to refresh", Toast.LENGTH_LONG).show();

                swipeRefresh.setRefreshing(false);

            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        myCurrentCall.cancel();
    }

    @Override
    public String getTitle() {
        return getArguments().getString(KEY_SECTION);
    }

    // Mapping response of articles
    private List<Article> mapResult(TopStoriesResult topStoriesResult) {
        int minPixelSize = getResources().getDimensionPixelSize(R.dimen.thumbnail_size);
        List<Article> articles = new ArrayList<>();

        if (!CollectionUtils.isEmpty(topStoriesResult.getTopStoriesArticles())) {
            for (TopStoriesArticle topStoriesArticle : topStoriesResult.getTopStoriesArticles()) {
                if (topStoriesArticle.getMultimedia() != null && !topStoriesArticle.getMultimedia().isEmpty()) {
                    String imageUrl = null;
                    for (int i = 0; i < topStoriesArticle.getMultimedia().size(); i++) {

                        if (topStoriesArticle.getMultimedia().get(i).getHeight() >= minPixelSize
                                && topStoriesArticle.getMultimedia().get(i).getWidth() >= minPixelSize) {

                            imageUrl = topStoriesArticle.getMultimedia().get(i).getUrl();
                        }

                    }

                    articles.add(new Article(topStoriesArticle.getTitle(),
                            topStoriesArticle.getPublishedDate(),
                            topStoriesArticle.getSection(),
                            imageUrl, topStoriesArticle.getAbstract(),
                            topStoriesArticle.getUrl()));
                }
            }
        }
        return articles;
    }


}
