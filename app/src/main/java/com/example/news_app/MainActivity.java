package com.example.news_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.ServiceConnectionLeakedViolation;
import android.view.View;
import android.widget.Button;


import com.example.news_app.models.NewsApiResponse;
import com.example.news_app.models.NewsHeadLines;
import com.example.news_app.models.RequestManager;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener,View.OnClickListener {

    RecyclerView recycler_view_main;

    Button business_tab,entertainment_tab,general_tab,health_tab,science_tab,sports_tab,technology_tab;
    CustomAdapter customAdapter;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this) ;
        progressDialog.setTitle("Fetching news Articles");
        progressDialog.show();


        business_tab = (Button) findViewById(R.id.business_tab);
        entertainment_tab = (Button) findViewById(R.id.entertainment_tab);
        general_tab = (Button) findViewById(R.id.general_tab);
        health_tab = (Button) findViewById(R.id.health_tab);
        science_tab = (Button) findViewById(R.id.science_tab);
        sports_tab = (Button) findViewById(R.id.sports_tab);
        technology_tab = (Button) findViewById(R.id.technology_tab);

        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener,"general",null);


    }

    private final onFetchDataListener<NewsApiResponse> listener = new onFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadLines> list, String message) {
            showNews(list);
            progressDialog.dismiss();
        }
        @Override
        public void onError(String message) {

        }
    };
    private void showNews(List<NewsHeadLines> list) {
        recycler_view_main = (RecyclerView) findViewById(R.id.recycler_view_main);
        recycler_view_main.setHasFixedSize(true);
        recycler_view_main.setLayoutManager(new GridLayoutManager(this,1));

        customAdapter = new CustomAdapter(getApplicationContext(),list,this );
        recycler_view_main.setAdapter(customAdapter);
    }
    @Override
    public void OnNewsClicked(NewsHeadLines headlines) {
        startActivity(new Intent(MainActivity.this,DetailsActivity.class)
                .putExtra("data",headlines));
    }

    @Override
    public void onClick(View view) {

        Button button = (Button) view;




    }
}