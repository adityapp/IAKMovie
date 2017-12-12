package com.example.axce.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
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
import java.util.ArrayList;

/**
 * Created by AXCE on 06/12/2017.
 */

public class NewRecyclerViewAdapter extends RecyclerView.Adapter<NewRecyclerViewAdapter.ViewHolder> {
    private ArrayList<ModelMovie.ResultsBean> dataset;
    private Context context;

    public NewRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public NewRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        NewRecyclerViewAdapter.ViewHolder viewHolder = new NewRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final NewRecyclerViewAdapter.ViewHolder holder, final int position) {
        Log.d("CEKLIST", dataset.get(position).getTitle());
        holder.judul.setText(dataset.get(position).getTitle());
        holder.deskripsi.setText(dataset.get(position).getOverview());
        holder.ratingBar.setRating(Float.valueOf(dataset.get(position).getVote_average())/2);
        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w500"+dataset.get(position).getPoster_path())
                .into(holder.image);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("object", (Serializable) dataset.get(position));
                context.startActivity(intent);
            }
        });
        holder.tanggal.setText(dataset.get(position).getRelease_date());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void setList(ArrayList<ModelMovie.ResultsBean> dataset) {
        this.dataset = dataset;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView judul, deskripsi, tanggal;
        RatingBar ratingBar;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_card);
            judul = itemView.findViewById(R.id.judul_card);
            deskripsi = itemView.findViewById(R.id.deskripsi);
            ratingBar = itemView.findViewById(R.id.rating_bar);
            cardView = itemView.findViewById(R.id.card_view);
            tanggal = itemView.findViewById(R.id.tanggal);
        }
    }

}
