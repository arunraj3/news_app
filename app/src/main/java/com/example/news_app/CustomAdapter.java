package com.example.news_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news_app.models.NewsHeadLines;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder>{




    private Context context;
    private List<NewsHeadLines> headLines;

    public CustomAdapter(Context context, List<NewsHeadLines> headLines) {
        this.context = context;
        this.headLines = headLines;
    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.headlines_main,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.news_headlines.setText(headLines.get(position).getTitle());
        holder.news_source.setText(headLines.get(position).getSource().getName());

        if(headLines.get(position).getUrlToImage() != null){
            Picasso.get().load(headLines.get(position).getUrlToImage()).into(holder.news_img_headline);
        }
    }

    @Override
    public int getItemCount() {
        return headLines.size();
    }
}
