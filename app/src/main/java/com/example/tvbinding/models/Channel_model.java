package com.example.tvbinding.models;

import android.app.Application;
import android.media.Image;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.tvbinding.app.application;

public class Channel_model {

    String name;
    String logo;
    String type;
    String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }




    @BindingAdapter("android:load")
    public static void loadIMG(ImageView imageView, String url){
        Glide.with(application.getContext()).load(url).into(imageView);
    }
}
