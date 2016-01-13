package com.ltd.fix.timehelper.Adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.ltd.fix.timehelper.Fragments.DoneTaskFragment;
import com.ltd.fix.timehelper.Fragments.currentTaskFragment;

/**
 * Created by fix on 14.01.16.
 */
public class TabAdapter extends FragmentStatePagerAdapter {
    private int numberTabs;

    public TabAdapter(FragmentManager fm,int numberTabs) {
        super(fm);
        this.numberTabs = numberTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new currentTaskFragment();
            case 1:
                return new DoneTaskFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numberTabs;
    }
}
