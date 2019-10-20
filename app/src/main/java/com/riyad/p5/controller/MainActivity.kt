package com.riyad.p5.controller

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
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

import java.text.DateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener, DatePickerDialog.OnDateSetListener {
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

        val toggle = ActionBarDrawerToggle(this, drawer, myToolbar,
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
        }

        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.articles_menu, menu)


        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {


            R.id.menu_min -> {
            }

            R.id.menu_about -> {
            }

            R.id.menu_help -> {
            }

            R.id.search_btn -> {
                startActivity(Intent(this, SearchActivity::class.java))
            }

        }

        if (item.itemId == R.id.menu_min) {

            // TODO afficher la page de notifications faire un switch

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawer!!.isDrawerOpen(GravityCompat.START)) {

            drawer!!.closeDrawer(GravityCompat.START)
        } else if (myViewPager?.currentItem != 0) {

            myViewPager?.currentItem = 0

        }else {
            super.onBackPressed()
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {

        Toast.makeText(this, "" + position, Toast.LENGTH_LONG).show()
        when (position) {

            0 ->

                myNavView!!.setCheckedItem(R.id.nav_topStories)

            1 ->


                myNavView!!.setCheckedItem(R.id.nav_mostPopular)
            2 ->


                myNavView!!.setCheckedItem(R.id.nav_business)
            3 ->

                myNavView!!.setCheckedItem(R.id.nav_sport)
        }


    }

    override fun onPageScrollStateChanged(state: Int) {


    }


    override fun onDateSet(datePicker: DatePicker, year: Int, month: Int, day: Int) {
        val calendar = Calendar.getInstance()

        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        val currentDayString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.time)
    }


}
