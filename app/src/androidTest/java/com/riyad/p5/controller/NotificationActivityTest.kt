package com.riyad.p5.controller

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.riyad.p5.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class NotificationActivityTest {


    @Rule
    @JvmField
    val mActivityRule = ActivityScenarioRule<NotificationActivity>(NotificationActivity::class.java)


    @Test
    fun notificationActivity_isDisplayed() {

        onView(withId(R.id.notification_activity))
            .check(matches(isDisplayed()))

    }

    @Test
    fun Checkboxs_isChecked() {

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