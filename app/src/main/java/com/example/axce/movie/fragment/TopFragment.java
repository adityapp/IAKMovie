package com.example.axce.movie.fragment;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.axce.movie.adapter.NewRecyclerViewAdapter;
import com.example.axce.movie.R;
import com.example.axce.movie.adapter.TopRecyclerViewAdapter;
import com.example.axce.movie.model.ModelTop;

import java.util.ArrayList;

import retrofit2.Retrofit;

public class TopFragment extends Fragment {
    private RecyclerView recyclerView;
    private TopRecyclerViewAdapter recyclerViewAdapter;
    private Retrofit retrofit;
    ArrayList<ModelTop.ResultsBean> dataSet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, container, false);
        Toast.makeText(getContext(),"TopFragment",Toast.LENGTH_SHORT).show();
        return view;
    }
}
