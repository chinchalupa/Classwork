package com.example.jeremy.comicviewer;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jeremy on 1/14/2016.
 */
public class GetComicTask extends AsyncTask<String, Void, Comic> {

    private ComicConsumer comicConsumer;

    public GetComicTask(ComicConsumer comicConsumer) {
        this.comicConsumer = comicConsumer;
    }

    @Override
    protected void onPostExecute(Comic comic) {
        super.onPostExecute(comic);
        comicConsumer.onComicLoaded(comic);
    }

    @Override
    protected Comic doInBackground(String... urlStrings) {
        String urlString = urlStrings[0];
        Comic comic = null;
        try {
            comic = new ObjectMapper().readValue(new URL(urlString), Comic.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return comic;
    }

    public interface  ComicConsumer {
        public void onComicLoaded(Comic comic);
    }
}
