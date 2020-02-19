package com.riyad.p5.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Medium {

    @SerializedName("media-metadata")
    @Expose
    private List<MediaMetadatum> mediaMetadata = null;

    public List<MediaMetadatum> getMediaMetadata() {
        return mediaMetadata;
    }
}
