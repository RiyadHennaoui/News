package com.riyad.p5.ui;

import android.os.Parcel;
import android.os.Parcelable;

public class Article implements Parcelable {

    private String title;
    private String date;
    private String section;
    private String imageUrl;
    private String shortDesc;

    public Article(String title, String date, String section, String imageUrl, String shortDesc) {
        this.title = title;
        this.date = date;
        this.section = section;
        this.imageUrl = imageUrl;
        this.shortDesc = shortDesc;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getSection() {
        return section;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.date);
        dest.writeString(this.section);
        dest.writeString(this.imageUrl);
        dest.writeString(this.shortDesc);
    }

    protected Article(Parcel in) {
        this.title = in.readString();
        this.date = in.readString();
        this.section = in.readString();
        this.imageUrl = in.readString();
        this.shortDesc = in.readString();
    }

    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel source) {
            return new Article(source);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
}
