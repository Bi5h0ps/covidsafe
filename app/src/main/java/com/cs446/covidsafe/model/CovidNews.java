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
                        .q("COVID")
                        .language("en")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        List<Article> articleLst = response.getArticles();
                        for(Article article : articleLst){
                            if(article.getTitle().contains("COVID") || article.getTitle().contains("vaccine")) {
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
        /*
        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q("COVID")
                        .language("en")
                        .sortBy("publishedAt")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        System.out.println("456");

                    }
                    @Override
                    public void onFailure(Throwable throwable) {
                        // add some data when failed
                        covidNewsList.add(new News("NSW COVID-19 cases set to surge today as infections grow in south-west Sydney",
                                "https://www.abc.net.au/news/2021-07-08/nsw-braces-for-rise-in-covid19-cases-in-south-west-sydney/100275184"));
                        covidNewsList.add(new News("Global Covid-19 death toll surpasses 4 million",
                                "https://www.reuters.com/world/asia-pacific/japan-set-declare-state-emergency-tokyo-area-through-aug-22-minister-2021-07-08/"));
                        covidNewsList.add(new News("COVID-19: Double-jabbed Britons will be allowed to travel to 140 amber list countries with no quarantine on return",
                                "https://news.sky.com/story/covid-19-summer-holidays-set-for-take-off-govt-to-allow-fully-vaccinated-travellers-to-avoid-quarantine-rules-12351279"));
                        covidNewsList.add(new News("Spectators to face Olympic ban as Tokyo declares coronavirus emergency-report",
                                "https://www.reuters.com/world/asia-pacific/japan-set-declare-state-emergency-tokyo-area-through-aug-22-minister-2021-07-08/"));
                        System.out.println(throwable.getMessage());
                    }
                }
        );
         */
    }
}