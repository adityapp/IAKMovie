package com.example.axce.movie.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.axce.movie.fragment.NewFragment;
import com.example.axce.movie.fragment.TopFragment;

/**
 * Created by AXCE on 05/12/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                NewFragment tab1 = new NewFragment();
                return tab1;
            case 1:
                TopFragment tab2 = new TopFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
