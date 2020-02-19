package com.riyad.p5.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MostPopularResult {

    @SerializedName("results")
    @Expose
    private List<MostPopularArticle> results = null;

    public List<MostPopularArticle> getResults() {
        return results;
    }
}
