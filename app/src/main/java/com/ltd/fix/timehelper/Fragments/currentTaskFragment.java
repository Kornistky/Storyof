package com.ltd.fix.timehelper.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ltd.fix.timehelper.Adapter.CurrentTaskAdapter;
import com.ltd.fix.timehelper.Models.ModelTask;
import com.ltd.fix.timehelper.R;


public class currentTaskFragment extends Fragment {

    private RecyclerView rvCurrent_task;
    private RecyclerView.LayoutManager layoutManager;

    private CurrentTaskAdapter adapter;


    public currentTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_current_task, container, false);

        rvCurrent_task = (RecyclerView) rootView.findViewById(R.id.rv_current_task);

        layoutManager = new LinearLayoutManager(getActivity());

        rvCurrent_task.setLayoutManager(layoutManager);

        adapter = new CurrentTaskAdapter();

        rvCurrent_task.setAdapter(adapter);

        return rootView;
    }

    public void addTask(ModelTask newTask) {
        int position = -1;

        for (int i = 0; i < adapter.getItemCount(); i++) {
            if (adapter.getItem(i).isTask()){
                ModelTask task = (ModelTask) adapter.getItem(i);
                if (newTask.getDate()< task.getDate()){
                    position = i;
                    break;
                }
            }
        }

        if(position != -1){
            adapter.addItem(position, newTask);
        }else {
            adapter.addItem(newTask);
        }
    }
}
