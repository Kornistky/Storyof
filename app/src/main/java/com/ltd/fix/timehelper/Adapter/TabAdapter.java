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

    public static final int CURRENT_TASK_POSITION = 0;
    public static final int DONE_TASK_POSITION = 1;

    private currentTaskFragment currentTaskFragment;
    private DoneTaskFragment doneTaskFragment;

    public TabAdapter(FragmentManager fm, int numberTabs) {
        super(fm);
        this.numberTabs = numberTabs;
        currentTaskFragment = new currentTaskFragment();
        doneTaskFragment = new DoneTaskFragment();
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                return currentTaskFragment;
            case 1:
                return doneTaskFragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numberTabs;
    }
}
