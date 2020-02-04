
package com.riyad.p5.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopStoriesResult {

//    @SerializedName("status")
//    @Expose
//    private String status;
//    @SerializedName("copyright")
//    @Expose
//    private String copyright;
    @SerializedName("section")
    @Expose
    private String section;
//    @SerializedName("last_updated")
//    @Expose
//    private String lastUpdated;
//    @SerializedName("num_results")
//    @Expose
//    private Integer numResults;
    @SerializedName("results")
    @Expose
    private List<TopStoriesArticle> topStoriesArticles = null;

//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getCopyright() {
//        return copyright;
//    }
//
//    public void setCopyright(String copyright) {
//        this.copyright = copyright;
//    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

//    public String getLastUpdated() {
//        return lastUpdated;
//    }
//
//    public void setLastUpdated(String lastUpdated) {
//        this.lastUpdated = lastUpdated;
//    }
//
//    public Integer getNumResults() {
//        return numResults;
//    }
//
//    public void setNumResults(Integer numResults) {
//        this.numResults = numResults;
//    }

    public List<TopStoriesArticle> getTopStoriesArticles() {
        return topStoriesArticles;
    }

//    public void setTopStoriesArticles(List<TopStoriesArticle> topStoriesArticles) {
//        this.topStoriesArticles = topStoriesArticles;
//    }

}
