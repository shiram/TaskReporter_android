package com.tr.hunter.taskreporter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

public class AccountsAdapter extends BaseAdapter {
    static List<UserData> items;
    static UserData userData;
    Context context;
    private ArrayList<UserData> arrayList;

    public AccountsAdapter(Context context, List<UserData> items) {
        this.context = context;
        AccountsAdapter.items = items;
        this.arrayList = new ArrayList<UserData>();
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
            v = li.inflate(R.layout.accounts, null);
        }

        userData = items.get(position);

        if(userData != null){
            ImageView user_image = v.findViewById(R.id.user_image);
            TextView user_firstname = v.findViewById(R.id.user_firstname);
            TextView user_lastname = v.findViewById(R.id.user_lastname);
            TextView employee_id = v.findViewById(R.id.employee_id);
            CheckBox is_team_leader = v.findViewById(R.id.is_team_leader);
            CheckBox is_employee = v.findViewById(R.id.is_employee);
            TextView assign_task = v.findViewById(R.id.assign_task);
            TextView assign_role = v.findViewById(R.id.assign_role);



            Picasso.with(context).load(Config.url+"/"+userData.getUser_image()).into(user_image);
            user_firstname.setText(userData.getFirstname());
            user_lastname.setText(userData.getLastname());
            employee_id.setText(userData.getEmployee_id());

            if(items.get(position).getIs_team_leader() == 1){
                is_team_leader.setChecked(true);
                is_employee.setChecked(false);
            }else if(items.get(position).getIs_employee() == 1){
                is_employee.setChecked(true);
                is_team_leader.setChecked(false);
            }

            user_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.manage_accounts_menu);
                    dialog.setTitle("Apply Action");


                    AppCompatTextView make_team_leader = dialog.findViewById(R.id.make_team_leader);
                    AppCompatTextView make_employee = dialog.findViewById(R.id.make_employee);
                    AppCompatTextView revert_team_leader = dialog.findViewById(R.id.revert_team_leader);
                    AppCompatTextView revert_employee = dialog.findViewById(R.id.revert_employee);
                    AppCompatTextView assign_task = dialog.findViewById(R.id.assign_task);
                    AppCompatTextView assign_role = dialog.findViewById(R.id.assign_role);

                    if(new CustomToast(context).getAccessLevel() == 1){

                        make_team_leader.setVisibility(View.VISIBLE);
                        make_employee.setVisibility(View.VISIBLE);
                        revert_team_leader.setVisibility(View.VISIBLE);
                        revert_employee.setVisibility(View.VISIBLE);

                    }
                    if(new CustomToast(context).getIsTeamLeader() == 1 ){
                        assign_task.setVisibility(View.VISIBLE);
                        assign_role.setVisibility(View.VISIBLE);
                    }

                    final int id = items.get(position).getUser_id();
                    final String fname = items.get(position).getFirstname();
                    final String lname = items.get(position).getLastname();

                    make_team_leader.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //make team leader


                            Receiver r = new Receiver(context);
                            r.giveRights(id,Config.MAKE_TEAM_LEADER);
                            dialog.dismiss();
                        }
                    });

                    make_employee.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //make employee


                            Receiver r = new Receiver(context);
                            r.giveRights(id,Config.MAKE_EMPLOYEE);
                            dialog.dismiss();
                        }
                    });

                    revert_team_leader.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //revert team leader
                            Receiver r = new Receiver(context);
                            r.giveRights(id,Config.REVERT_TEAM_LEADER);
                            dialog.dismiss();
                        }
                    });

                    revert_employee.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //revert employee
                            Receiver r = new Receiver(context);
                            r.giveRights(id,Config.REVERT_EMPLOYEE);
                            dialog.dismiss();
                        }
                    });

                    assign_task.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //Assign Task
                            dialog.dismiss();
                            Intent intent = new Intent(context, AddRT.class);
                            intent.putExtra("fname",fname);
                            intent.putExtra("lname",lname);
                            intent.putExtra("role", 0);
                            intent.putExtra("id",id);
                            context.startActivity(intent);

                        }
                    });

                    assign_role.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //Assign Role
                            dialog.dismiss();
                            Intent intent = new Intent(context, AddRT.class);
                            intent.putExtra("fname",fname);
                            intent.putExtra("lname",lname);
                            intent.putExtra("role", 1);
                            intent.putExtra("id",id);
                            context.startActivity(intent);
                        }
                    });

                    dialog.show();
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
            for (UserData wp : arrayList) {
                if (wp.getFirstname().toLowerCase(Locale.getDefault() )
                        .contains(charText) || wp.getLastname().toLowerCase(Locale.getDefault() )
                        .contains(charText) || wp.getEmployee_id().toLowerCase(Locale.getDefault() )
                        .contains(charText) ) {
                    items.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    public  void filter(String type ){
        items.clear();
        if(type.isEmpty()){
            items.addAll(arrayList);
        }

        if(type == "Team"){
            for(UserData wp: arrayList){
                if(wp.getIs_team_leader() == 1){
                    items.add(wp);
                }
            }
        }else if(type == "Employee"){
            for(UserData wp: arrayList){
                if(wp.getIs_employee() == 1){
                    items.add(wp);
                }
            }
        }

        notifyDataSetChanged();
    }
}
