package com.riyad.p5.controller

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.riyad.p5.R
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class SearchActivityTest{


    @Test
fun searchActivity_isDisplayed(){

        val activityScenario = ActivityScenario.launch(SearchActivity::class.java)

        onView(withId(R.id.search_activity)).check(matches(isDisplayed()))

    }

    @Test
    fun checkboxs_isChecked(){
        val activityScenario = ActivityScenario.launch(SearchActivity::class.java)


        onView(withId(R.id.search_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.checkbox_1))
            .perform(click())
            .check(matches(isChecked()))
        onView(withId(R.id.checkbox_2))
            .perform(click())
            .check(matches(isChecked()))
        onView(withId(R.id.checkbox_3))
            .perform(click())
            .check(matches(isChecked()))
        onView(withId(R.id.checkbox_4))
            .perform(click())
            .check(matches(isChecked()))



    }


}