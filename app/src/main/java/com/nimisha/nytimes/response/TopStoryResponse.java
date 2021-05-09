package com.nimisha.nytimes.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nimisha.nytimes.model.TopStories;

import java.util.List;

public class TopStoryResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("results")
    @Expose
    private List<TopStories> results;

    public List<TopStories> getResults() {
        return results;
    }

    public void setResults(List<TopStories> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
