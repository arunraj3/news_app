package com.example.news_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news_app.models.NewsHeadLines;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    ImageView img_detail_news;
    TextView text_detail_title,text_detail_author,text_detail_time,text_detail_detail,text_detail_content;
    NewsHeadLines newsHeadLines;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        text_detail_title = (TextView)findViewById(R.id.text_detail_title);
        text_detail_author = (TextView)findViewById(R.id.text_detail_author);
        text_detail_time = (TextView)findViewById(R.id.text_detail_time);
        text_detail_detail = (TextView)findViewById(R.id.text_detail_detail);
        text_detail_content = (TextView)findViewById(R.id.text_detail_content);
        img_detail_news = (ImageView) findViewById(R.id.img_detail_news);
        newsHeadLines = (NewsHeadLines) getIntent().getSerializableExtra("data");


        text_detail_title.setText(newsHeadLines.getTitle());
        text_detail_author.setText(newsHeadLines.getAuthor());
        text_detail_time.setText(newsHeadLines.getPublishedAt());
        text_detail_detail.setText(newsHeadLines.getDescription());
        text_detail_content.setText(newsHeadLines.getContent());


        Picasso.get().load(newsHeadLines.getUrlToImage()).into(img_detail_news);
    }



}