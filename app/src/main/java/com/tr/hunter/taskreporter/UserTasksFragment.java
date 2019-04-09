package com.tr.hunter.taskreporter;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserTasksFragment extends Fragment {

    private ListView task_list;
    private EditText search_task;
    private ProgressDialog progressDialog;


    public UserTasksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_user_tasks, container, false);
        task_list = v.findViewById(R.id.task_list);
        search_task = v.findViewById(R.id.search_tasks);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading...");

        progressDialog.show();
        Receiver receiver = new Receiver(getContext(), progressDialog,task_list);
        receiver.getUserTasks(new CustomToast(getContext()).getUserId());

        return v;
    }

}
