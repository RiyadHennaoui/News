package com.riyad.p5.controller

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager

import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.riyad.p5.R


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    ViewPager.OnPageChangeListener/*, DatePickerDialog.OnDateSetListener*/ {
    private var drawer: DrawerLayout? = null
    private var myViewPager: ViewPager? = null
    private var myNavView: NavigationView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myViewPager = findViewById(R.id.main_vp_articles)
        val myTabLayout = findViewById<TabLayout>(R.id.main_tl)
        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        myNavView = findViewById(R.id.navigation_view)


        setSupportActionBar(myToolbar)

        myTabLayout.setupWithViewPager(myViewPager)

        myViewPager!!.adapter = ViewPagerAdapter(supportFragmentManager)
        myViewPager!!.addOnPageChangeListener(this)



        drawer = findViewById(R.id.drawer_layout)

        val toggle = ActionBarDrawerToggle(
            this, drawer, myToolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer!!.addDrawerListener(toggle)
        toggle.syncState()

        myNavView!!.setNavigationItemSelectedListener(this)
        myNavView!!.setCheckedItem(R.id.nav_topStories)


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.nav_topStories -> {
                myViewPager!!.currentItem = 0
                drawer!!.closeDrawer(GravityCompat.START)
            }

            R.id.nav_mostPopular -> {
                myViewPager!!.currentItem = 1
                drawer!!.closeDrawer(GravityCompat.START)
            }

            R.id.nav_business -> {
                myViewPager!!.currentItem = 2
                drawer!!.closeDrawer(GravityCompat.START)
            }

            R.id.nav_sport -> {
                myViewPager!!.currentItem = 3
                drawer!!.closeDrawer(GravityCompat.START)
            }

            R.id.nav_search -> {
                myViewPager!!.currentItem = 4
                startActivity(Intent(this, SearchActivity::class.java))

            }

            R.id.nav_notification -> {

                startActivity(Intent(this, NotificationActivity::class.java))
            }
        }

        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.articles_menu, menu)


        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {


            R.id.menu_notification -> {
                startActivity(Intent(this, NotificationActivity::class.java))
            }

            R.id.menu_about -> {
            }

            R.id.menu_help -> {
            }

            R.id.search_btn -> {
                startActivity(Intent(this, SearchActivity::class.java))
            }

        }


        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawer!!.isDrawerOpen(GravityCompat.START)) {

            drawer!!.closeDrawer(GravityCompat.START)
        } else if (myViewPager?.currentItem != 0) {

            myViewPager?.currentItem = 0

        } else {
            super.onBackPressed()
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {

    }

    override fun onPageScrollStateChanged(state: Int) {


    }





}
