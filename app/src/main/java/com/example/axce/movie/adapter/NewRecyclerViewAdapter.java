package com.example.axce.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.axce.movie.R;
import com.example.axce.movie.model.ModelNew;
import com.example.axce.movie.model.ModelPopular;

import java.util.ArrayList;

/**
 * Created by AXCE on 06/12/2017.
 */

public class NewRecyclerViewAdapter extends RecyclerView.Adapter<NewRecyclerViewAdapter.ViewHolder> {
    private ArrayList<ModelNew.ResultsBean> dataset;
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
    public void onBindViewHolder(NewRecyclerViewAdapter.ViewHolder holder, int position) {
        Log.d("CEKLIST", dataset.get(position).getTitle());
        holder.judul.setText(dataset.get(position).getTitle());
        holder.ratingBar.setRating(Float.valueOf(dataset.get(position).getOverview()));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void setList(ArrayList<ModelNew.ResultsBean> dataset) {
        this.dataset = dataset;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView judul;
        RatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_card);
            judul = itemView.findViewById(R.id.judul_card);
            ratingBar = itemView.findViewById(R.id.rating_bar);
        }
    }

}
