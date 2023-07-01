package com.example.news_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.ServiceConnectionLeakedViolation;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.news_app.models.NewsApiResponse;
import com.example.news_app.models.NewsHeadLines;
import com.example.news_app.models.RequestManager;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener,View.OnClickListener {

    RecyclerView recycler_view_main;
    SearchView search_bar;
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


        search_bar = (SearchView) findViewById(R.id.search_bar);
        search_bar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("Fetching news articles of "+query);
                progressDialog.show();
                RequestManager manager = new RequestManager(MainActivity.this);
                manager.getNewsHeadlines(listener,"general",query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        business_tab = (Button) findViewById(R.id.business_tab);
        business_tab.setOnClickListener(this);
        entertainment_tab = (Button) findViewById(R.id.entertainment_tab);
        entertainment_tab.setOnClickListener(this);
        general_tab = (Button) findViewById(R.id.general_tab);
        general_tab.setOnClickListener(this);
        health_tab = (Button) findViewById(R.id.health_tab);
        health_tab.setOnClickListener(this);
        science_tab = (Button) findViewById(R.id.science_tab);
        science_tab.setOnClickListener(this);
        sports_tab = (Button) findViewById(R.id.sports_tab);
        sports_tab.setOnClickListener(this);
        technology_tab = (Button) findViewById(R.id.technology_tab);
        technology_tab.setOnClickListener(this);

        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener,"business",null);
    }

    private final onFetchDataListener<NewsApiResponse> listener = new onFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadLines> list, String message) {
            if(list.isEmpty()){
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this,"No Data Found!",Toast.LENGTH_LONG).show();
            }else{
                showNews(list);
                progressDialog.dismiss();
            }
        }
        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this,"Error!",Toast.LENGTH_SHORT).show();
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
        String category = (button.getText().toString()).toLowerCase();
        progressDialog.setTitle("Fetching news articles of "+category);
        progressDialog.show();
        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener,category,null);
    }
}