package com.example.axce.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.axce.movie.R;
import com.example.axce.movie.activity.DetailActivity;
import com.example.axce.movie.model.ModelMovie;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Created by AXCE on 10/12/2017.
 */

public class PopularRecyclerViewAdapter extends RecyclerView.Adapter<PopularRecyclerViewAdapter.ViewHolder> {
    private ArrayList<ModelMovie.ResultsBean> dataSet;
    private Context context;

    public PopularRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public PopularRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        PopularRecyclerViewAdapter.ViewHolder viewHolder = new PopularRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PopularRecyclerViewAdapter.ViewHolder holder, final int position) {
        holder.judul.setText(dataSet.get(position).getTitle());
        holder.deskripsi.setText(dataSet.get(position).getOverview());
        holder.tanggal.setText(dataSet.get(position).getRelease_date());
        holder.ratingBar.setRating(Float.valueOf(dataSet.get(position).getVote_average())/2);
        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w500"+dataSet.get(position).getPoster_path())
                .into(holder.image);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("object",(Serializable) dataSet.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView judul, deskripsi, tanggal;
        RatingBar ratingBar;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_card);
            judul = itemView.findViewById(R.id.judul_card);
            deskripsi = itemView.findViewById(R.id.deskripsi);
            tanggal = itemView.findViewById(R.id.tanggal);
            ratingBar = itemView.findViewById(R.id.rating_bar);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }

    public void setList(ArrayList<ModelMovie.ResultsBean> dataSet) {
        this.dataSet = dataSet;
    }
}
