package com.tr.hunter.taskreporter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class MainActivity extends AppCompatActivity {

    private EditText user_email, user_password;
    private Button register, login;
    private TextView forgot_password;

    String email, password;
    AwesomeValidation awesomeValidation;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        user_email = findViewById(R.id.user_email);
        user_password = findViewById(R.id.user_password);
        forgot_password = findViewById(R.id.request_password);

        register.setOnClickListener(mRegister);
        forgot_password.setOnClickListener(mRequestPassword);
        login.setOnClickListener(mLogin);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(Config.LOGIN);
    }

    private View.OnClickListener mRegister = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, Register.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener mRequestPassword = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    private View.OnClickListener mLogin = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            initGUI();
            if(awesomeValidation.validate()){
                //To server
                progressDialog.show();
                Receiver receiver = new Receiver(MainActivity.this, progressDialog);
                receiver.userLogin(email,password);
            }
        }
    };

    private void initGUI(){
        email = user_email.getText().toString().trim();
        password = user_password.getText().toString().trim();
        AddValidation();
    }

    private void AddValidation(){
        awesomeValidation.addValidation(user_email, Patterns.EMAIL_ADDRESS, getString(R.string.email_err));
        awesomeValidation.addValidation(user_password, RegexTemplate.NOT_EMPTY, getString(R.string.password_err));
    }


}
