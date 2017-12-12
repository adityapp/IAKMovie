package com.example.axce.movie.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.axce.movie.R;
import com.example.axce.movie.adapter.PopularRecyclerViewAdapter;
import com.example.axce.movie.adapter.UpcomingRecyclerViewAdapter;
import com.example.axce.movie.model.ModelMovie;
import com.example.axce.movie.rest.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PopularFragment extends Fragment {
    private RecyclerView recyclerView;
    private PopularRecyclerViewAdapter recyclerViewAdapter;
    private Retrofit retrofit;
    private ArrayList<ModelMovie.ResultsBean> resultsBeans;
    private ProgressBar loading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular,container,false);
        recyclerView = view.findViewById(R.id.recyclerview_popular);
        loading = view.findViewById(R.id.loading_bar_popular);

        //retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getList();

        //recyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        return view;
    }

    private void getList(){
        final ApiClient apiClient = retrofit.create(ApiClient.class);
        Call<ModelMovie> call = apiClient.getMovie("popular?api_key=abdf48fd44cb74007720836ffe506983&language=en-US&page=1");
        call.enqueue(new Callback<ModelMovie>() {
            @Override
            public void onResponse(Call<ModelMovie> call, Response<ModelMovie> response) {
                ModelMovie modelMovie = response.body();
                resultsBeans = new ArrayList<ModelMovie.ResultsBean>();
                for (int i = 0; i < modelMovie.getResults().size(); i++){
                    resultsBeans.add(new ModelMovie.ResultsBean(
                            modelMovie.getResults().get(i).getVote_count(),
                            modelMovie.getResults().get(i).getId(),
                            modelMovie.getResults().get(i).getVideo(),
                            modelMovie.getResults().get(i).getVote_average(),
                            modelMovie.getResults().get(i).getTitle(),
                            modelMovie.getResults().get(i).getPopularity(),
                            modelMovie.getResults().get(i).getPoster_path(),
                            modelMovie.getResults().get(i).getOriginal_language(),
                            modelMovie.getResults().get(i).getOriginal_title(),
                            modelMovie.getResults().get(i).getBackdrop_path(),
                            modelMovie.getResults().get(i).getAdult(),
                            modelMovie.getResults().get(i).getOverview(),
                            modelMovie.getResults().get(i).getRelease_date(),
                            modelMovie.getResults().get(i).getGenre_ids()
                    ));
                }
                loading.setVisibility(View.GONE);
                recyclerViewAdapter = new PopularRecyclerViewAdapter(getContext());
                recyclerViewAdapter.setList(resultsBeans);
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<ModelMovie> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
}
