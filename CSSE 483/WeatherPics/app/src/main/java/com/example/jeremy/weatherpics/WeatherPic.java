package com.example.jeremy.weatherpics;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Jeremy on 1/21/2016.
 */
public class WeatherPic {
    private String name;
    private String url;
    @JsonIgnore
    private String key;

    public WeatherPic() {
    }

    public WeatherPic(String name, String url) {
        this.name = name;
        this.url = url;
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
}
