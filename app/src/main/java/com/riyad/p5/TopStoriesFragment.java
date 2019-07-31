package com.riyad.p5;

import android.widget.Toast;

import com.riyad.p5.data.model.TopStoriesArticle;
import com.riyad.p5.data.model.TopStoriesResult;
import com.riyad.p5.ui.Article;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopStoriesFragment extends AbsFragment {

    @Override
    protected void reload() {


        Call<TopStoriesResult> call = service.getTopStories("business", "vWAeWal4GLoISnnu5K7KvoMQ26nBhVW5");

        call.enqueue(new Callback<TopStoriesResult>() {
            @Override
            public void onResponse(Call<TopStoriesResult> call, Response<TopStoriesResult> response) {

                if (response.body() != null) {

                    setNewArticleList(mapResult(response.body()));
                }

            }

            @Override
            public void onFailure(Call<TopStoriesResult> call, Throwable t) {

                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private List<Article> mapResult(TopStoriesResult topStoriesResult) {

        List<Article> articles = new ArrayList<>();

        if (!CollectionUtils.isEmpty(topStoriesResult.getTopStoriesArticles())) {
            for (TopStoriesArticle topStoriesArticle : topStoriesResult.getTopStoriesArticles()) {
                String imageUrl = topStoriesResult.getTopStoriesArticles().get(0).getMultimedia().get(0).getUrl();
                articles.add(new Article(topStoriesArticle.getTitle(),
                        topStoriesArticle.getPublishedDate(),
                        topStoriesArticle.getSection(),
                        imageUrl, topStoriesArticle.getAbstract()));
            }
        }
        return articles;
    }

    // todo override gettitle.


}
