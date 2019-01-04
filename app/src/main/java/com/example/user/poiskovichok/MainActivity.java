package com.example.user.poiskovichok;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.KeyEvent;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        showHome();
    }
    Fragment fragment = null;

    private void showHome(){
        fragment = new Home();
        if (fragment != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main , fragment,fragment.getTag());
            fragmentTransaction.commit();
        }

    }


    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();

        } else {
            getFragmentManager().popBackStack();
        }
    }


    private void displaySelectedScreen(int id){


        switch (id){
            case R.id.nav_cafe_restaurants:
                fragment = new Cafe_restaurants();
                break;
            case R.id.nav_cinemas:
                fragment = new Cinemas();
                break;
            case R.id.nav_enjoys:
                fragment = new Enjoys();
                break;
            case R.id.nav_night_clubs:
                fragment = new Night_clubs();
                break;
            case R.id.nav_theaters:
                fragment = new Theaters();
                break;
            case R.id.nav_museums:
                fragment = new Museums();
                break;
            case R.id.nav_sports:
                fragment = new Sports();
                break;
            case R.id.nav_sights:
                fragment = new Sights();
                break;
            case R.id.nav_current_actions:
                fragment = new Current_events();
                break;
            case R.id.nav_future_actions:
                fragment = new Future_events();
                break;
            case R.id.nav_contacts:
                fragment = new Contacts();
                break;

        }


        if (fragment != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main , fragment,fragment.getTag());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        displaySelectedScreen(id);
        return true;
    }
}
