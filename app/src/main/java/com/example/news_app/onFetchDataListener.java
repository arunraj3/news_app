package com.example.news_app;

import com.example.news_app.models.NewsHeadLines;

import java.util.List;

public interface onFetchDataListener {
   void onFetchData(List<NewsHeadLines> list,String message);
   void onError(String message);
}
