package com.example.news_app;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class CustomViewHolder extends RecyclerView.ViewHolder{

    TextView news_headlines,news_source;
    ImageView news_img_headline;

    CardView cardView;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);

        news_headlines = (TextView) itemView.findViewById(R.id.news_headlines);
        news_source = (TextView) itemView.findViewById(R.id.news_source);
        news_img_headline = (ImageView) itemView.findViewById(R.id.news_img_headline);
        cardView = (CardView) itemView.findViewById(R.id.cardView);

    }
}
