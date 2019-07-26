package com.riyad.p5;

import com.riyad.p5.data.model.MostPopularResult;
import com.riyad.p5.data.model.TopStoriesResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewYorkTimesAPI {

    @GET("svc/topstories/v2/{section}.json")
    Call<TopStoriesResult> getTopStories(@Path("section") String section, @Query("api-key") String apiKey);
    @GET("svc/mostpopular/v2/viewed/7.json")
    Call<MostPopularResult> getMostPopular(@Query("api-key") String  apiKey);
}
