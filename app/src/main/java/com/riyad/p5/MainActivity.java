package com.riyad.p5;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager myViewPager = findViewById(R.id.main_vp_articles);
        TabLayout myTabLayout = findViewById(R.id.main_tl);

        myTabLayout.setupWithViewPager(myViewPager);

        myViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));


    }

}
