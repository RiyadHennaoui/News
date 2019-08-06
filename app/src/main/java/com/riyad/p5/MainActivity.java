package com.riyad.p5;

import android.app.DatePickerDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        ViewPager.OnPageChangeListener,
        DatePickerDialog.OnDateSetListener {
    private DrawerLayout drawer;
    private ViewPager myViewPager;
    private NavigationView myNavView;
    private Layout mySearchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myViewPager = findViewById(R.id.main_vp_articles);
        TabLayout myTabLayout = findViewById(R.id.main_tl);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        myNavView = findViewById(R.id.navigation_view);
        mySearchView = findViewById(R.layout.search_layout);

        setSupportActionBar(myToolbar);

        myTabLayout.setupWithViewPager(myViewPager);

        myViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        myViewPager.addOnPageChangeListener(this);

        // TODO

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, myToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        myNavView.setNavigationItemSelectedListener(this);
        myNavView.setCheckedItem(R.id.nav_topStories);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.nav_topStories:
                myViewPager.setCurrentItem(0);
                drawer.closeDrawer(GravityCompat.START);

                break;

            case R.id.nav_mostPopular:
                myViewPager.setCurrentItem(1);
                drawer.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_business:
                myViewPager.setCurrentItem(2);
                drawer.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_sport:
                myViewPager.setCurrentItem(3);
                drawer.closeDrawer(GravityCompat.START);
                break;

        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.articles_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search_btn).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.search_btn);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search last News");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (query.length() > 2) {


                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_min) {

            // TODO afficher la page de notifications faire un switch

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        Toast.makeText(this, "" + position, Toast.LENGTH_LONG).show();
        switch (position) {

            case 0:

                myNavView.setCheckedItem(R.id.nav_topStories);

                break;

            case 1:


                myNavView.setCheckedItem(R.id.nav_mostPopular);
                break;
            case 2:


                myNavView.setCheckedItem(R.id.nav_business);

                break;
            case 3:

                myNavView.setCheckedItem(R.id.nav_sport);
                break;

        }


    }

    @Override
    public void onPageScrollStateChanged(int state) {


    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        String currentDayString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
    }


}
