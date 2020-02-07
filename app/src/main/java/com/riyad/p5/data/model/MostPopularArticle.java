
package com.riyad.p5.data.model;

import android.os.Parcel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MostPopularArticle implements android.os.Parcelable {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("abstract")
    @Expose
    private String _abstract;
    @SerializedName("published_date")
    @Expose
    private String publishedDate;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("media")
    @Expose
    private List<Medium> media = null;

    protected MostPopularArticle(Parcel in) {
        url = in.readString();
        section = in.readString();
        title = in.readString();
        _abstract = in.readString();
        publishedDate = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
    }

    public static final Creator<MostPopularArticle> CREATOR = new Creator<MostPopularArticle>() {
        @Override
        public MostPopularArticle createFromParcel(Parcel in) {
            return new MostPopularArticle(in);
        }

        @Override
        public MostPopularArticle[] newArray(int size) {
            return new MostPopularArticle[size];
        }
    };

    public String getUrl() {
        return url;
    }

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

    public String getPublishedDate() {
        return publishedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Medium> getMedia() {
        return media;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(url);
        parcel.writeString(section);
        parcel.writeString(title);
        parcel.writeString(_abstract);
        parcel.writeString(publishedDate);
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
    }


}

