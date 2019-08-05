package com.riyad.p5;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        ViewPager.OnPageChangeListener {
    private DrawerLayout drawer;
    private ViewPager myViewPager;
    private NavigationView myNavView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myViewPager = findViewById(R.id.main_vp_articles);
        TabLayout myTabLayout = findViewById(R.id.main_tl);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        myNavView = findViewById(R.id.navigation_view);

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

        Toast.makeText(this, ""+position, Toast.LENGTH_LONG).show();
        switch (position){

            case 0 :

                myNavView.setCheckedItem(R.id.nav_topStories);

                break;

            case 1 :


                myNavView.setCheckedItem(R.id.nav_mostPopular);
                break;
            case 2 :


                myNavView.setCheckedItem(R.id.nav_business);

                break;
            case 3 :

                myNavView.setCheckedItem(R.id.nav_sport);
                break;

        }


    }

    @Override
    public void onPageScrollStateChanged(int state) {



    }


}
