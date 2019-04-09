package com.tr.hunter.taskreporter;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class AddRT extends AppCompatActivity {

    int user_id,role;
    String firstname,lastname,title,description,start_date,end_date, is_role_or_task;
    TextView user_names;
    EditText task_title, task_description, task_atart_date, task_end_date;
    Button assign;
    ProgressDialog progressDialog;
    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rt);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Assign Role/Task");
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        try{
            user_id = getIntent().getExtras().getInt("id");
            role = getIntent().getExtras().getInt("role");
            firstname = getIntent().getExtras().getString("fname");
            lastname = getIntent().getExtras().getString("lname");
        }catch (NullPointerException e){

        }

        user_names = findViewById(R.id.user_name);
        if(role > 0){
            user_names.setText("Assign Role To: "+firstname+" "+lastname);
            is_role_or_task = "Role.";
        }else{
            user_names.setText("Assign Task To: "+firstname+" "+lastname);
            is_role_or_task = "Task";
        }

        task_title = findViewById(R.id.title);
        task_description = findViewById(R.id.description);
        task_atart_date = findViewById(R.id.start_date);
        task_end_date = findViewById(R.id.end_date);
        assign = findViewById(R.id.assign);

        assign.setOnClickListener(mAssign);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        progressDialog = new ProgressDialog(AddRT.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Processing...");

    }

    private void initData(){
        title = task_title.getText().toString().trim();
        description = task_description.getText().toString().trim();
        start_date = task_atart_date.getText().toString().trim();
        end_date = task_end_date.getText().toString().trim();
        AddValidation();
    }

    private void AddValidation(){
        awesomeValidation.addValidation(task_title, RegexTemplate.NOT_EMPTY, getString(R.string.field_fill_err));
        awesomeValidation.addValidation(task_description, RegexTemplate.NOT_EMPTY, getString(R.string.field_fill_err));
        awesomeValidation.addValidation(task_atart_date, RegexTemplate.NOT_EMPTY, getString(R.string.field_fill_err));
        awesomeValidation.addValidation(task_end_date, RegexTemplate.NOT_EMPTY, getString(R.string.field_fill_err));
    }



    private View.OnClickListener mAssign = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            initData();
            if(awesomeValidation.validate()){
                //store task to server
                progressDialog.show();
                Uploader uploader = new Uploader(AddRT.this, progressDialog);
                uploader.assignTasks(title,description,start_date,end_date,user_id,is_role_or_task);
            }
        }
    };
}
