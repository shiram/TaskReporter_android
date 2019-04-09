package com.tr.hunter.taskreporter;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Locale;

public class ManageTeamLeaders extends AppCompatActivity {

    EditText search_accounts;
    AppCompatSpinner filter_accounts;
    ListView accounts_list;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_team_leaders);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Manage Accounts");
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        search_accounts = findViewById(R.id.search_accounts);
        filter_accounts = findViewById(R.id.accounts_filter);
        accounts_list = findViewById(R.id.accounts_list);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(ManageTeamLeaders.this, R.array.accounts_filter, android.R.layout.simple_spinner_dropdown_item);
        filter_accounts.setAdapter(arrayAdapter);
        filter_accounts.setOnItemSelectedListener(mFilter);

        progressDialog = new ProgressDialog(ManageTeamLeaders.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading...");

        progressDialog.show();
        Receiver receiver = new Receiver(ManageTeamLeaders.this, progressDialog,accounts_list);
        receiver.getAccounts(Config.GET_ACCOUNTS);

        search_accounts.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = search_accounts.getText().toString().toLowerCase(Locale.getDefault());
                AdapterConnector.accountsAdapter.search(text);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private AdapterView.OnItemSelectedListener mFilter = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String type = adapterView.getItemAtPosition(i).toString();
            if(type == "Team Leaders"){
                AdapterConnector.accountsAdapter.filter("Team");
            }else if(type == "Employees"){
                AdapterConnector.accountsAdapter.filter("Employee");
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
}
