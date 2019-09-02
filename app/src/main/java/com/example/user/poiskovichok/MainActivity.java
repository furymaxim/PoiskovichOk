package com.example.user.poiskovichok;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity implements SortFragment.FragmentCallback {

    private Toolbar toolbar;
    private FrameLayout redCircle;
    private TextView countTextView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter adapter;

    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private List<Integer> groupImages;
    private HashMap<Integer, List<Integer>> childImages;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Поисковичок");
        }

        mDrawerLayout = findViewById(R.id.drawer_layout);
        expandableListView = findViewById(R.id.navList);

        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        expandableListView.addHeaderView(listHeaderView);

        gendata();
        addDrawersItem();
        setupDrawer();



        if (savedInstanceState == null)
            selectFirstItemAsDefault();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }
    private void gendata() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();
        groupImages = new ArrayList<>();
        childImages = new HashMap<>();

        listDataHeader.add("Главная страница");
        listDataHeader.add("Кафе & Рестораны");
        listDataHeader.add("Развлечения");
        listDataHeader.add("Фитнес-клубы");
        listDataHeader.add("Культурные учреждения");
        listDataHeader.add("Текущие мероприятия");
        listDataHeader.add("Предстоящие мероприятия");
        listDataHeader.add("Связаться с разработчиком");
        listDataHeader.add("О приложении");


        List<String> cafeAndRestaurants= new ArrayList<>();
        cafeAndRestaurants.add("Кафе");
        cafeAndRestaurants.add("Рестораны");

        listDataChild.put(listDataHeader.get(1), cafeAndRestaurants);

        List<String> entertainment= new ArrayList<>();
        entertainment.add("Кинотеатры");
        entertainment.add("Развлекательные центры");
        entertainment.add("Парки аттракционов");

        listDataChild.put(listDataHeader.get(2), entertainment);

        List<String> cultureBuildings = new ArrayList<>();
        cultureBuildings.add("Музеи");
        cultureBuildings.add("Театры");
        cultureBuildings.add("Достопримечательности");

        listDataChild.put(listDataHeader.get(4), cultureBuildings);

        groupImages.add(R.drawable.ic_home_black_24dp);
        groupImages.add(R.drawable.ic_local_cafe_black_24dp);
        groupImages.add(R.drawable.ic_music_note_black_24dp);
        groupImages.add(R.drawable.ic_fitness_center_black_24dp);
        groupImages.add(R.drawable.ic_nature_people_black_24dp);
        groupImages.add(R.drawable.ic_pin_drop_black_24dp);
        groupImages.add(R.drawable.ic_access_time_black_24dp);
        groupImages.add(R.drawable.ic_message_black_24dp);
        groupImages.add(R.drawable.ic_info_outline_black_24dp);

        List<Integer> cafeAndRestaurantsImg = new ArrayList<>();
        cafeAndRestaurantsImg.add(R.drawable.ic_local_pizza_black_24dp);
        cafeAndRestaurantsImg.add(R.drawable.ic_restaurant_black_24dp);

        childImages.put(groupImages.get(1),cafeAndRestaurantsImg);

        List<Integer> entertainmentImg = new ArrayList<>();
        entertainmentImg.add(R.drawable.ic_local_movies_black_24dp);
        entertainmentImg.add(R.drawable.ic_casino_black_24dp);
        entertainmentImg.add(R.drawable.ic_airline_seat_recline_extra_black_24dp);

        childImages.put(groupImages.get(2), entertainmentImg);

        List<Integer> cultureBuildingsImg = new ArrayList<>();
        cultureBuildingsImg.add(R.drawable.ic_account_balance_black_24dp);
        cultureBuildingsImg.add(R.drawable.ic_local_play_black_24dp);
        cultureBuildingsImg.add(R.drawable.ic_nature_black_24dp);

        childImages.put(groupImages.get(4), cultureBuildingsImg);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void selectFirstItemAsDefault() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.home_content, new HomeFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open, R.string.close)
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getSupportActionBar().setTitle("Главная страница");
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
               // getSupportActionBar().setTitle("hi");
                invalidateOptionsMenu();
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void addDrawersItem() {
        adapter = new ExpandableListAdapter1(getApplicationContext(),listDataHeader,listDataChild,groupImages,childImages);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                getSupportActionBar().setTitle(listDataHeader.get(groupPosition).toString()); //Set title for toolbar when open menu
            }

        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                //getSupportActionBar().setTitle("Cashhhhlesssa");
            }
        });


        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                FrameLayout fragmentContainer = findViewById(R.id.fragment_container);
                FrameLayout fragmentSortContainer = findViewById(R.id.fragment_sort_container);
                FrameLayout fragmentHome = findViewById(R.id.home_content);

                boolean home = true;
                int childrenCount = adapter.getChildrenCount(groupPosition);

                Fragment selectedFragment = null;


                if(childrenCount == 0){
                   switch ((String)adapter.getGroup(groupPosition)){
                       case "Главная страница":
                           home = true;
                           selectedFragment = new HomeFragment();
                           break;
                       case "Текущие мероприятия":
                           home = false;
                           selectedFragment = new OngoingActivitiesFragment();
                           break;

                   }




                    if(!home) {
                        fragmentContainer.setVisibility(View.VISIBLE);
                        fragmentSortContainer.setVisibility(View.VISIBLE);
                        fragmentHome.setVisibility(View.GONE);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, selectedFragment)
                                .replace(R.id.fragment_sort_container, new SortFragment())
                                .addToBackStack(null)
                                .commit();
                    }else{
                        fragmentContainer.setVisibility(View.GONE);
                        fragmentSortContainer.setVisibility(View.GONE);
                        fragmentHome.setVisibility(View.VISIBLE);

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.home_content,
                                selectedFragment)
                                .addToBackStack(null)
                                .commit();
                    }

                    getSupportActionBar().setTitle((String)adapter.getGroup(groupPosition));

                    mDrawerLayout.closeDrawer(GravityCompat.START);
                }



                return false;


            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                FrameLayout fragmentContainer = findViewById(R.id.fragment_container);
                FrameLayout fragmentSortContainer = findViewById(R.id.fragment_sort_container);
                FrameLayout fragmentHome = findViewById(R.id.home_content);

                boolean home = false;

                Fragment selectedFragment = null;

                switch ((String)adapter.getChild(groupPosition,childPosition)){
                    case "Кафе":
                        selectedFragment = new CafeFragment();
                        break;
                }


                if(!home) {
                    fragmentContainer.setVisibility(View.VISIBLE);
                    fragmentSortContainer.setVisibility(View.VISIBLE);
                    fragmentHome.setVisibility(View.GONE);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .replace(R.id.fragment_sort_container, new SortFragment())
                            .addToBackStack(null)
                            .commit();


                }


                getSupportActionBar().setTitle((String)adapter.getChild(groupPosition,childPosition));

                mDrawerLayout.closeDrawer(GravityCompat.START);

                return false;

            }
        });
    }

    @Override
    public void onBackPressed()
    {
        if(getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item))
            return true;

        switch (item.getItemId()){
            case R.id.app_bar_notification:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        final MenuItem alertMenuItem = menu.findItem(R.id.app_bar_notification);
        FrameLayout rootView = (FrameLayout) alertMenuItem.getActionView();

        redCircle =  rootView.findViewById(R.id.view_alert_red_circle);
        countTextView = rootView.findViewById(R.id.view_alert_count_textview);

        return super.onPrepareOptionsMenu(menu);


    }

    private void updateAlertIcon() {
        // if alert count extends into two digits, just show the red circle

    }


    @Override
    public void messageFromSortFragment(CharSequence text) {
       CafeFragment cafeFragment = (CafeFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
       if(cafeFragment !=null){
           cafeFragment.setText(text);
       }else{
           CafeFragment newFragment = new CafeFragment();
           Bundle args = new Bundle();
           args.putCharSequence("message", text);
           newFragment.setArguments(args);

           FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
           transaction.replace(R.id.fragment_container, newFragment);
           transaction.addToBackStack(null);

           transaction.commit();
       }

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if(fragment instanceof SortFragment){
            SortFragment sortFragment = (SortFragment)fragment;
            sortFragment.setFragmentCallback(this);
        }

    }
}