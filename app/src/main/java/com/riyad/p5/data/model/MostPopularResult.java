
package com.riyad.p5.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class  MostPopularResult {

    @SerializedName("results")
    @Expose
    private List<MostPopularArticle> results = null;

    public List<MostPopularArticle> getResults() {
        return results;
    }
}
