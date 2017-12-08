package com.example.axce.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.axce.movie.R;
import com.example.axce.movie.model.ModelNew;
import com.example.axce.movie.model.ModelTop;

import java.util.ArrayList;

/**
 * Created by AXCE on 08/12/2017.
 */

public class TopRecyclerViewAdapter extends RecyclerView.Adapter<TopRecyclerViewAdapter.ViewHolder> {
    private ArrayList<ModelTop.ResultsBean> dataset;
    private Context context;

    public TopRecyclerViewAdapter(Context context){
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        TopRecyclerViewAdapter.ViewHolder viewHolder = new TopRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TopRecyclerViewAdapter.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void setList(ArrayList<ModelTop.ResultsBean> dataset){
        this.dataset = dataset;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
