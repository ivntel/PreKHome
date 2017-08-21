package com.ivntel.android.prekhome.POJO;

/**
 * Created by ivnte on 2017-08-18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("results")
    @Expose
    private Results results;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("is_authenticated")
    @Expose
    private Boolean isAuthenticated;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getIsAuthenticated() {
        return isAuthenticated;
    }

    public void setIsAuthenticated(Boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }
}