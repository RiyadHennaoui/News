
package com.riyad.p5.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MostPopularResult {

//    @SerializedName("status")
//    @Expose
//    private String status;
//    @SerializedName("copyright")
//    @Expose
//    private String copyright;
//    @SerializedName("num_results")
//    @Expose
//    private Integer numResults;
    @SerializedName("results")
    @Expose
    private List<MostPopularArticle> results = null;

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
//
//    public Integer getNumResults() {
//        return numResults;
//    }
//
//    public void setNumResults(Integer numResults) {
//        this.numResults = numResults;
//    }

    public List<MostPopularArticle> getResults() {
        return results;
    }

//    public void setResults(List<MostPopularArticle> results) {
//        this.results = results;
//    }

}
