package com.ivntel.android.prekhome.POJO;

/**
 * Created by ivnte on 2017-08-18.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

    @SerializedName("videos")
    @Expose
    private List<Video> videos = null;
    @SerializedName("games")
    @Expose
    private List<Game> games = null;
    @SerializedName("ebooks")
    @Expose
    private List<Ebook> ebooks = null;
    @SerializedName("lessons")
    @Expose
    private List<Lesson> lessons = null;

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public List<Ebook> getEbooks() {
        return ebooks;
    }

    public void setEbooks(List<Ebook> ebooks) {
        this.ebooks = ebooks;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

}