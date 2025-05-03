package com.example.tvbinding.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Channel_List_model {

    @SerializedName("record")
    private List<Channel_model> channelModels;
    @SerializedName("metadata")
    private Metadata metadata;


    public List<Channel_model> getChannelModels() {
        return channelModels;
    }

    public void setChannelModels(List<Channel_model> channelModels) {
        this.channelModels = channelModels;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
}
