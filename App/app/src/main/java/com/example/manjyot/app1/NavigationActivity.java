package com.example.manjyot.app1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;

import org.w3c.dom.Text;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static TextView navDrawName;
    public static TextView navDrawEmail;

    public static AlertDialog featureDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton newTaskBtn = (FloatingActionButton) findViewById(R.id.new_task_btn);
        newTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavigationActivity.this, NewTaskActivity.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        navigationView.setCheckedItem(R.id.nav_alltasks);
        navigationView.getMenu().performIdentifierAction(R.id.nav_alltasks, 0);

        TasksApplication app = (TasksApplication) this.getApplication();
        View headerView = navigationView.getHeaderView(0);
        navDrawName = headerView.findViewById(R.id.nav_draw_name);
        navDrawEmail = headerView.findViewById(R.id.nav_draw_email);
        navDrawName.setText(app.getSignedInName());
        navDrawEmail.setText(app.getSignedInEmail());

        AlertDialog.Builder featureAlertDialog = new AlertDialog.Builder(this);
        featureAlertDialog.setTitle("Unimplemented feature!");
        featureAlertDialog.setMessage("In an actual full implementation of this application where back end coding is the focus, a complex feature would be implemented. For the purpose of this HCI project, this is here as a placeholder to showcase the design features.");
        featureAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        featureDialog = featureAlertDialog.create();
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            featureDialog.show();
        } else if (id == R.id.action_filterBy) {
            featureDialog.show();
        } else if (id == R.id.action_sortBy) {
            featureDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_alltasks) {
            fragment = new FragmentAll();
        } else if (id == R.id.nav_overduetasks) {
            fragment = new FragmentOverdue();
        } else if (id == R.id.nav_todaytasks) {
            fragment = new FragmentToday();
        } else if (id == R.id.nav_tomorrowtasks) {
            fragment = new FragmentTomorrow();
        } else if (id == R.id.nav_completetasks) {
            fragment = new FragmentComplete();
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        } else if (id == R.id.nav_rateus_title) {

        }

        fragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
