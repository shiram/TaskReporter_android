package com.tr.hunter.taskreporter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by HUNTER on 5/25/2017.
 */

public class TasksAdapter extends BaseAdapter {
    static List<Tasks> items;
    static Tasks tasks;
    Context context;
    private ArrayList<Tasks> arrayList;

    public TasksAdapter(Context context, List<Tasks> items) {
        this.context = context;
        TasksAdapter.items = items;
        this.arrayList = new ArrayList<Tasks>();
        this.arrayList.addAll(items);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.tasks, null);
        }

        tasks = items.get(position);

        if(tasks != null){

            TextView task_title = v.findViewById(R.id.task_title);
            TextView task_start_date = v.findViewById(R.id.task_start_date);
            TextView task_end_date = v.findViewById(R.id.task_end_date);
            TextView team_leader = v.findViewById(R.id.team_leader);
            TextView is_task_role = v.findViewById(R.id.is_task_role);
            Button actions = v.findViewById(R.id.task_actions);

            task_title.setText(tasks.getTask_title());
            task_start_date.setText(tasks.getStart_date());
            task_end_date.setText(tasks.getEnd_date());
            team_leader.setText(tasks.getTeam_leader_emp_id());
            is_task_role.setText(tasks.getIs_role_task());

            actions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //to actions dialog
                }
            });


        }
        return v;
    }

    public void search(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        items.clear();
        if (charText.length() == 0) {
            items.addAll(arrayList);
        } else {
            for (Tasks wp : arrayList) {
                if (wp.getTask_title().toLowerCase(Locale.getDefault() )
                        .contains(charText) || wp.getTeam_leader_emp_id().toLowerCase(Locale.getDefault() )
                        .contains(charText) || wp.getStart_date().toLowerCase(Locale.getDefault() )
                        .contains(charText) ) {
                    items.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
