package edu.rosehulman.moviequotes;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Matt Boutell on 12/15/2015, based on earlier work by Dave Fisher.
 */
public class MovieQuote {
    private String quote;
    private String movie;
    @JsonIgnore
    private String key;



    public MovieQuote() {
    }

    public MovieQuote(String quote, String movie) {
        this.movie = movie;
        this.quote = quote;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public void setValues(MovieQuote newMovieQuote) {
        movie = newMovieQuote.getMovie();
        quote = newMovieQuote.getQuote();
    }
}
