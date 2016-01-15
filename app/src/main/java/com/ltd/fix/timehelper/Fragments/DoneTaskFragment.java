package com.ltd.fix.timehelper.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ltd.fix.timehelper.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DoneTaskFragment extends Fragment {

    private RecyclerView rvDone_task;
    private RecyclerView.LayoutManager layoutManager;


    public DoneTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_done_task, container, false);

        rvDone_task = (RecyclerView) rootView.findViewById(R.id.rv_done_task);

        layoutManager = new LinearLayoutManager(getActivity());

        rvDone_task.setLayoutManager(layoutManager);


        return  rootView;
    }

}
