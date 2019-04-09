package com.tr.hunter.taskreporter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SharedPreferences sharedPreferences;
    private  int user_id, access_level;
    private String email, firstname, lastname, user_image;

    ImageView profile_image;
    TextView names, session_email;

    Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sharedPreferences = getSharedPreferences(Config.MYPREFERENCES, Context.MODE_PRIVATE);
        user_id = sharedPreferences.getInt("session_id", 0);
        email = sharedPreferences.getString("session_email", null);
        firstname = sharedPreferences.getString("session_firstname", null);
        lastname  = sharedPreferences.getString("session_lastname", null);
        access_level = sharedPreferences.getInt("access_level", 0);
        user_image = sharedPreferences.getString("user_image", null);

        View headerView = navigationView.getHeaderView(0);
        profile_image = headerView.findViewById(R.id.profile_image);
        names = headerView.findViewById(R.id.names);
        session_email = headerView.findViewById(R.id.email);

        Picasso.with(Home.this).load(Config.url+user_image).into(profile_image);
        names.setText(firstname+" "+lastname);
        session_email.setText(email);

        if(new CustomToast(Home.this).getIsTeamLeader() == 1){

            fragment = new EmployeesFragment();
            loadFragment(fragment);
            navigationView.getMenu().findItem(R.id.team).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_send_reports).setVisible(false);

        }
        if(new CustomToast(Home.this).getIsEmployee() == 1 || new CustomToast(Home.this).getIsEmployee() == 0){

            navigationView.getMenu().findItem(R.id.team).setVisible(false);
            navigationView.getMenu().findItem(R.id.employees).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_view_reports).setVisible(false);
            fragment = new UserTasksFragment();
            loadFragment(fragment);

        }
        if(new CustomToast(Home.this).getAccessLevel() > 0){
            fragment = new HomeFragment();
            loadFragment(fragment);
            navigationView.getMenu().findItem(R.id.employees).setVisible(false);
            navigationView.getMenu().findItem(R.id.reports).setVisible(false);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manage_team_leader) {
            Intent intent = new Intent(Home.this, ManageTeamLeaders.class);
            startActivity(intent);
            // Handle the camera action
        }else if (id == R.id.nav_manage_employees) {

            fragment = new EmployeesFragment();
            loadFragment(fragment);

        } else if (id == R.id.nav_view_reports) {

        } else if (id == R.id.nav_send_reports) {

        }else if (id == R.id.nav_profile) {

        }else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
