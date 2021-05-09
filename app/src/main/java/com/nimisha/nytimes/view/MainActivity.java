package com.nimisha.nytimes.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.nimisha.nytimes.R;
import com.nimisha.nytimes.adapter.MovieArticleAdapter;
import com.nimisha.nytimes.adapter.SliderAdapter;
import com.nimisha.nytimes.model.Article;
import com.nimisha.nytimes.model.SliderData;
import com.nimisha.nytimes.model.TopStories;
import com.nimisha.nytimes.view_model.ArticleViewModel;
import com.nimisha.nytimes.view_model.TopStoryViewModel;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView my_recycler_view;
    private SliderView sliderView;
    private ProgressBar progress_circular_movie_article;
    private LinearLayoutManager layoutManager;
    private MovieArticleAdapter adapter;
    private SliderAdapter slideadapter;
    private ArrayList<Article> articleArrayList = new ArrayList<>();
    private ArrayList<TopStories> storiesArrayList =new ArrayList<>();

    ArticleViewModel articleViewModel;
    TopStoryViewModel topStoryViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();
        // getTopStories();

        //getMovieArticles();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    /**
     * @param @null
     */
    private void initialization() {
        progress_circular_movie_article = (ProgressBar) findViewById(R.id.progress_circular_movie_article);
        my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);
        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        my_recycler_view.setLayoutManager(layoutManager);

        my_recycler_view.setHasFixedSize(true);

        adapter = new MovieArticleAdapter(MainActivity.this, articleArrayList);
        my_recycler_view.setAdapter(adapter);

        // View Model


        articleViewModel.getArticleResponseLiveData().observe(this, articleResponse -> {
            if (articleResponse != null) {
                progress_circular_movie_article.setVisibility(View.GONE);
                List<Article> articles = articleResponse.getArticleList().getArticles();
                articleArrayList.addAll(articles);
                adapter.notifyDataSetChanged();
            }
        });

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        my_recycler_view.addItemDecoration(dividerItemDecoration);

        sliderView = findViewById(R.id.slider);

        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        sliderView.setScrollTimeInSec(3);

        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

        // adapter
        slideadapter = new SliderAdapter(MainActivity.this, storiesArrayList);
        sliderView.setSliderAdapter(slideadapter);


        articleViewModel.getTopStoriesResponseLiveData().observe(this, topStoryResponse -> {
            if (topStoryResponse != null) {
                List<TopStories> topStories = topStoryResponse.getResults();
                storiesArrayList.addAll(topStories);
                slideadapter.notifyDataSetChanged();
            }
        });
    }


    private void getMovieArticles() {
        articleViewModel.getArticleResponseLiveData().observe(this, articleResponse -> {
            if (articleResponse != null) {

                progress_circular_movie_article.setVisibility(View.GONE);
                List<Article> articles = articleResponse.getArticleList().getArticles();
                articleArrayList.addAll(articles);
                adapter.notifyDataSetChanged();
            }
        });
    }


}