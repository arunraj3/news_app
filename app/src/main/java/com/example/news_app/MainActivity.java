package com.example.news_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;


import com.example.news_app.models.NewsApiResponse;
import com.example.news_app.models.NewsHeadLines;
import com.example.news_app.models.RequestManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler_view_main;
    CustomAdapter customAdapter;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        progressDialog = new ProgressDialog(this) ;
        progressDialog.setTitle("Fetching news Articles");
        progressDialog.show();
        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener,"general",null);


    }

    private final onFetchDataListener<NewsApiResponse> listener = new onFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadLines> list, String message) {
            showNews(list);
            progressDialog.dismiss();
        }

        private void showNews(List<NewsHeadLines> list) {
            recycler_view_main = (RecyclerView) findViewById(R.id.recycler_view_main);
            recycler_view_main.setHasFixedSize(true);
            recycler_view_main.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));

            customAdapter = new CustomAdapter(getApplicationContext(),list);
            recycler_view_main.setAdapter(customAdapter);
        }
        @Override
        public void onError(String message) {

        }
    };
}