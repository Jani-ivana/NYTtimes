package com.nimisha.nytimes.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nimisha.nytimes.model.ArticleList;

public class ArticleResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private ArticleList articleList = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArticleList getArticleList() {
        return articleList;
    }

    public void setArticleList(ArticleList articleList) {
        this.articleList = articleList;
    }
}
