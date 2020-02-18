package com.riyad.p5.controller;

import com.riyad.p5.data.model.MostPopularResult;
import com.riyad.p5.data.model.TopStoriesResult;
import com.riyad.p5.data.model.search.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewYorkTimesAPI {

    @GET("svc/topstories/v2/{section}.json")
    Call<TopStoriesResult> getTopStories(@Path("section") String section, @Query("api-key") String apiKey);

    @GET("svc/mostpopular/v2/viewed/30.json")
    Call<MostPopularResult> getMostPopular(@Query("api-key") String apiKey);

    @GET("svc/search/v2/articlesearch.json")
    Call<SearchResponse> getSearchResponse(@Query("q") String query, @Query("fq") String filter,
                                           @Query("begin_date") String beginDate,
                                           @Query("end_date") String endDate,
                                           @Query("api-key") String apiKey);
}
