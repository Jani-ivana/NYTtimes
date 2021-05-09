package com.nimisha.nytimes.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nimisha.nytimes.repository.TopStoryRepository;
import com.nimisha.nytimes.response.TopStoryResponse;

import static com.nimisha.nytimes.constants.AppConstant.TOPSTORY_API_KEY;
import static com.nimisha.nytimes.constants.AppConstant.TOPSTORY_QUERY;

public class TopStoryViewModel extends AndroidViewModel {

    private TopStoryRepository topStoryRepository;
    private LiveData<TopStoryResponse> topstoryResponseLiveData;

    public TopStoryViewModel(@NonNull Application application) {
        super(application);

        topStoryRepository = new TopStoryRepository();
        this.topstoryResponseLiveData = topStoryRepository.getTopStories(TOPSTORY_API_KEY);
    }

    public LiveData<TopStoryResponse> getTopstoryResponseLiveData() {
        return topstoryResponseLiveData;
    }

}
