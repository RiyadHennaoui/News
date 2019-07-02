package com.riyad.p5;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewYorkTimesAPI {

    @GET("svc/topstories/v2/{section}.json")
    Call<TopStoriesResult> getTopStories(@Path("section") String section, @Query("api-key") String apiKey);
}
