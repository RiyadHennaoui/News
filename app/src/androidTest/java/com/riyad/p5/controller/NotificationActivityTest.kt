package com.riyad.p5.controller

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.riyad.p5.R
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class NotificationActivityTest  {

    @Test
    fun notificationActivity_isDisplayed(){

        val activityScenario = ActivityScenario.launch(NotificationActivity::class.java)

        onView(withId(R.id.notification_activity))
            .check(matches(isDisplayed()))

    }

    @Test
    fun Checkboxs_isChecked(){
        val activityScenario = ActivityScenario.launch(NotificationActivity::class.java)



        onView(withId(R.id.notification_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.checkBox1))
            .perform(ViewActions.click())
            .check(matches(isChecked()))
        onView(withId(R.id.checkBox2))
            .perform(ViewActions.click())
            .check(matches(isChecked()))
        onView(withId(R.id.checkBox3))
            .perform(ViewActions.click())
            .check(matches(isChecked()))
        onView(withId(R.id.checkBox4))
            .perform(ViewActions.click())
            .check(matches(isChecked()))



    }

}