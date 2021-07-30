package com.cs446.covidsafe.model;

import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

import com.cs446.covidsafe.R;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CovidNews {
    private List<HashMap<String, String>> covidNewsList= new ArrayList<HashMap<String, String>>();
    public class News {
        private String title;
        private String link;

        public News(String title, String link) {
            this.title = title;
            this.link = link;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return this.title;
        }
    }

    public List<HashMap<String, String>> getCovidNewsList(){
        return covidNewsList;
    }

    public void pullNews(SimpleAdapter adapter){
        NewsApiClient newsApiClient = new NewsApiClient("021949c322734fdf89063f4fba4a9d94");
        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q("COVID or vaccine")
                        .language("en")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        List<Article> articleLst = response.getArticles();
                        for(Article article : articleLst){
                            if(article.getTitle().contains("COVID")
                                    || article.getTitle().contains("vaccine")
                                    || article.getTitle().contains("lockdown")
                                    || article.getTitle().contains("pandemic")) {
                                HashMap<String, String> item = new HashMap<String, String>();
                                item.put("news_image", article.getUrlToImage());
                                item.put("news_title", article.getTitle());
                                item.put("weblink", article.getUrl());
                                covidNewsList.add(item);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
        );
    }
}