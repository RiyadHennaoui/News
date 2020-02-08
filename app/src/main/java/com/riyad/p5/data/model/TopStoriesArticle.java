
package com.riyad.p5.data.model;

import android.os.Parcel;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopStoriesArticle implements android.os.Parcelable {

    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("abstract")
    @Expose
    private String _abstract;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("published_date")
    @Expose
    private String publishedDate;
    @SerializedName("multimedia")
    @Expose
    private List<Multimedium> multimedia = null;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getTitle() {
        return title;
    }

    public String getAbstract() {
        return _abstract;
    }

    public String getUrl() {
        return url;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public List<Multimedium> getMultimedia() {
        return multimedia;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.section);
        dest.writeString(this.title);
        dest.writeString(this._abstract);
        dest.writeString(this.url);
        dest.writeString(this.publishedDate);
        dest.writeList(this.multimedia);
    }


    protected TopStoriesArticle(Parcel in) {
        this.section = in.readString();
        this.title = in.readString();
        this._abstract = in.readString();
        this.url = in.readString();
        this.publishedDate = in.readString();
        this.multimedia = new ArrayList<Multimedium>();
        in.readList(this.multimedia, Multimedium.class.getClassLoader());
    }

    public static final Creator<TopStoriesArticle> CREATOR = new Creator<TopStoriesArticle>() {
        @Override
        public TopStoriesArticle createFromParcel(Parcel source) {
            return new TopStoriesArticle(source);
        }

        @Override
        public TopStoriesArticle[] newArray(int size) {
            return new TopStoriesArticle[size];
        }
    };
}
