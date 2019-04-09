package com.tr.hunter.taskreporter;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    EditText search_accounts;
    AppCompatSpinner filter_accounts;
    ListView accounts_list;
    ProgressDialog progressDialog;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home, container, false);


        search_accounts = v.findViewById(R.id.search_accounts);
        filter_accounts = v.findViewById(R.id.accounts_filter);
        accounts_list = v.findViewById(R.id.accounts_list);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.accounts_filter, android.R.layout.simple_spinner_dropdown_item);
        filter_accounts.setAdapter(arrayAdapter);
        filter_accounts.setOnItemSelectedListener(mFilter);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading...");

        progressDialog.show();
        Receiver receiver = new Receiver(getContext(), progressDialog,accounts_list);
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

        return v;
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
