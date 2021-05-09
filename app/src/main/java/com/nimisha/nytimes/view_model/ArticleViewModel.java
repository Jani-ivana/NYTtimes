package com.nimisha.nytimes.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nimisha.nytimes.repository.ArticleRepository;
import com.nimisha.nytimes.repository.TopStoryRepository;
import com.nimisha.nytimes.response.ArticleResponse;
import com.nimisha.nytimes.response.TopStoryResponse;

import static com.nimisha.nytimes.constants.AppConstant.API_KEY;
import static com.nimisha.nytimes.constants.AppConstant.ARTICLE_QUERY;

public class ArticleViewModel extends AndroidViewModel {

    private ArticleRepository articleRepository;
    private TopStoryRepository topStoryRepository;
    private LiveData<ArticleResponse> articleResponseLiveData;
    private LiveData<TopStoryResponse> topStoriesResponseLiveData;

    public ArticleViewModel(@NonNull Application application) {
        super(application);

        articleRepository = new ArticleRepository();
        this.articleResponseLiveData = articleRepository.getMovieArticles(ARTICLE_QUERY,API_KEY);

        topStoryRepository = new TopStoryRepository();
        this.topStoriesResponseLiveData = topStoryRepository.getTopStories(API_KEY);
    }

    public LiveData<ArticleResponse> getArticleResponseLiveData() {
        return articleResponseLiveData;
    }

    public LiveData<TopStoryResponse> getTopStoriesResponseLiveData() {
        return topStoriesResponseLiveData;
    }

}
