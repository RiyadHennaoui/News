package com.riyad.p5.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopStoriesResult {

    @SerializedName("section")
    @Expose
    private String section;

    @SerializedName("results")
    @Expose
    private List<TopStoriesArticle> topStoriesArticles = null;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public List<TopStoriesArticle> getTopStoriesArticles() {
        return topStoriesArticles;
    }
}
