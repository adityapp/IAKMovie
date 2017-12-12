package com.example.axce.movie.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.axce.movie.R;
import com.example.axce.movie.model.ModelMovie;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import java.io.Serializable;

public class DetailActivity extends AppCompatActivity {
    private ImageView image;
    private TextView judul, tanggal;
    private JustifiedTextView deskripsi;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        image = findViewById(R.id.detail_image);
        judul = findViewById(R.id.detail_judul);
        deskripsi = findViewById(R.id.detail_deskripsi);
        ratingBar = findViewById(R.id.detail_rating_bar);
        tanggal = findViewById(R.id.detail_tanggal);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ModelMovie.ResultsBean extra = (ModelMovie.ResultsBean) getIntent().getSerializableExtra("object");
        Glide.with(this)
                .load("http://image.tmdb.org/t/p/w500" + extra.getBackdrop_path())
                .into(image);
        judul.setText(extra.getTitle());
        deskripsi.setText(extra.getOverview());
        tanggal.setText(extra.getRelease_date());
        ratingBar.setRating(Float.valueOf(extra.getVote_average()) / 2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
