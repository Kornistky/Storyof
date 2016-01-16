package com.ltd.fix.timehelper.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ltd.fix.timehelper.Fragments.TaskFragment;
import com.ltd.fix.timehelper.Models.Item;
import com.ltd.fix.timehelper.Models.ModelTask;
import com.ltd.fix.timehelper.R;
import com.ltd.fix.timehelper.Utils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fix on 15.01.16.
 */
public class CurrentTaskAdapter extends TaskAdapter {


    private static final int TYPE_TASK = 0;

    private static final int TYPE_SEPARATOR = 1;

    public CurrentTaskAdapter(TaskFragment taskFragment) {
        super(taskFragment);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        switch (viewType) {
            case TYPE_TASK:
                View v = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.model_task, viewGroup, false);
                TextView title = (TextView) v.findViewById(R.id.tvTaskTitle);
                TextView date = (TextView) v.findViewById(R.id.tvTaskDate);

                return new TaskViewHolder(v, title, date);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Item item = items.get(position);

        if (item.isTask()) {
            viewHolder.itemView.setEnabled(true);
            ModelTask task = (ModelTask) item;
            TaskViewHolder taskViewHolder = (TaskViewHolder) viewHolder;

            taskViewHolder.title.setText(task.getTitle());
            if (task.getDate() != 0) {
                taskViewHolder.date.setText(Utils.getAllDate(task.getDate()));
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position).isTask()) {

            return TYPE_TASK;
        } else {
            return TYPE_SEPARATOR;
        }
    }

    
}
