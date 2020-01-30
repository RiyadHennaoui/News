package com.riyad.p5.controller

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.riyad.p5.R
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class WebViewActivityTest{


    @Test
    fun webViewActivity_isDisplyed(){

        val activityScenario = ActivityScenario.launch(WebViewActivity::class.java)


        onView(withId(R.id.web_view_activity)).check(matches(isDisplayed()))

    }


}