package com.tr.hunter.taskreporter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdapterConnector {

    private List<UserData> userData;
    private List<Tasks> tasks;
    public static AccountsAdapter accountsAdapter;
    public static TasksAdapter tasksAdapter;
    ListView listView;
    private String data;

    private Context context;
    CustomToast customToast;

    public AdapterConnector(Context context, String data, ListView listView) {
        this.context = context;
        this.data = data;
        this.listView = listView;

    }

    public void populateUserList(){
        try{
            userData = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(data);
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int user_id = jsonObject.getInt("id");
                String firstname = jsonObject.getString("firstname");
                String lastname = jsonObject.getString("lastname");
                String employee_id = jsonObject.getString("employee_id");
                String email = jsonObject.getString("email");
                String user_image = jsonObject.getString("user_image");
                int is_team_leader = jsonObject.getInt("is_team_leader");
                int is_employee = jsonObject.getInt("is_employee");

                userData.add(new UserData(user_id,firstname,lastname,employee_id,email,user_image,is_team_leader,is_employee));
            }

            accountsAdapter = new AccountsAdapter(context, userData);
            listView.setAdapter(accountsAdapter);
            listView.deferNotifyDataSetChanged();

        }catch (JSONException e){
            customToast.setToast(Config.NO_DATA_ERROR);
            Log.e("MSG", e.getMessage().toString());
        }
    }


    public void populateTaskList(){
        try{
            tasks = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(data);
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int task_id = jsonObject.getInt("task_id");
                int employee_id = jsonObject.getInt("employee_id");
                String team_leader_names = jsonObject.getString("team_leader_names");
                String team_leader_email = jsonObject.getString("team_leader_email");
                String team_leader_emp_id = jsonObject.getString("team_leader_emp_id");
                String task_title = jsonObject.getString("task_title");
                String title_desc = jsonObject.getString("task_description");
                String start_date = jsonObject.getString("start_date");
                String end_date = jsonObject.getString("end_date");
                String is_role_task = jsonObject.getString("is_role_or_task");

                tasks.add(new Tasks(task_id,employee_id,team_leader_names,team_leader_email,team_leader_emp_id,task_title,title_desc,start_date,end_date,is_role_task));

            }

            tasksAdapter = new TasksAdapter(context, tasks);
            listView.setAdapter(tasksAdapter);
            listView.deferNotifyDataSetChanged();

        }catch (JSONException e){
           // customToast.setToast(Config.NO_DATA_ERROR);
            Log.e("MSG", e.getMessage().toString());
        }
    }
}
