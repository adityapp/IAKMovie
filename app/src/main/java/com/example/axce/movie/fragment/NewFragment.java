package com.example.axce.movie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.axce.movie.adapter.NewRecyclerViewAdapter;
import com.example.axce.movie.R;
import com.example.axce.movie.model.ModelNew;
import com.example.axce.movie.model.ModelPopular;
import com.example.axce.movie.rest.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewFragment extends Fragment {
    private RecyclerView recyclerView;
    private NewRecyclerViewAdapter recyclerViewAdapter;
    private Retrofit retrofit;
    private ArrayList<ModelNew.ResultsBean> resultsBeans;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_new);

        //retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getList();

        //recyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        Toast.makeText(getContext(), "NewFragment", Toast.LENGTH_SHORT).show();
        return view;
    }

    private void getList() {

        final ApiClient apiClient = retrofit.create(ApiClient.class);

        Call<ModelNew> call = apiClient.getNew("now_playing?api_key=abdf48fd44cb74007720836ffe506983&language=en-US&page=1");
        call.enqueue(new Callback<ModelNew>() {
            @Override
            public void onResponse(Call<ModelNew> call, Response<ModelNew> response) {
                ModelNew modelNew = response.body();
                resultsBeans = new ArrayList<ModelNew.ResultsBean>();
                for (int i = 0; i < modelNew.getResults().size(); i++) {
                    resultsBeans.add(new ModelNew.ResultsBean(
                            modelNew.getResults().get(i).getVote_count(),
                            modelNew.getResults().get(i).getId(),
                            modelNew.getResults().get(i).getVideo(),
                            modelNew.getResults().get(i).getVote_average(),
                            modelNew.getResults().get(i).getTitle(),
                            modelNew.getResults().get(i).getPopularity(),
                            modelNew.getResults().get(i).getPoster_path(),
                            modelNew.getResults().get(i).getOriginal_language(),
                            modelNew.getResults().get(i).getOriginal_title(),
                            modelNew.getResults().get(i).getBackdrop_path(),
                            modelNew.getResults().get(i).getAdult(),
                            modelNew.getResults().get(i).getOverview(),
                            modelNew.getResults().get(i).getRelease_date(),
                            modelNew.getResults().get(i).getGenre_ids()
                    ));
                    Log.d("cekisi", resultsBeans.get(i).getTitle());
                }
                recyclerViewAdapter = new NewRecyclerViewAdapter(getContext());
                recyclerViewAdapter.setList(resultsBeans);
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<ModelNew> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });

    }
}
