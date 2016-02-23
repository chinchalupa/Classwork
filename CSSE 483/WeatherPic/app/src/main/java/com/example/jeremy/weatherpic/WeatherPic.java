package com.example.jeremy.weatherpic;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Jeremy on 1/21/2016.
 */
public class WeatherPic {
    private String name;
    private String url;
    private String uid;
    @JsonIgnore
    private String key;

    public WeatherPic() {
    }

//    public WeatherPic(String name, String url) {
//        this.name = name;
//        this.url = url;
//    }

    public WeatherPic(String name, String url, String uid) {
        this.name = name;
        this.url = url;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setValues(WeatherPic pic) {
        this.name = pic.getName();
        this.url = pic.getUrl();
        this.uid = pic.getUrl();
    }
}
