
package com.riyad.p5.data.model.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Keyword {

    @SerializedName("name")
    @Expose
    private String name;
//    @SerializedName("value")
//    @Expose
//    private String value;
//    @SerializedName("rank")
//    @Expose
//    private Integer rank;
//    @SerializedName("major")
//    @Expose
//    private String major;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getValue() {
//        return value;
//    }
//
//    public void setValue(String value) {
//        this.value = value;
//    }
//
//    public Integer getRank() {
//        return rank;
//    }
//
//    public void setRank(Integer rank) {
//        this.rank = rank;
//    }
//
//    public String getMajor() {
//        return major;
//    }
//
//    public void setMajor(String major) {
//        this.major = major;
//    }

}
