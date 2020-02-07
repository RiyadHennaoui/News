package com.riyad.p5.controller

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.riyad.p5.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class WebViewActivityTest{


    @Rule // third parameter is set to false which means the activity is not started automatically
    @JvmField
    var mActivityRule: ActivityTestRule<WebViewActivity> =
        ActivityTestRule<WebViewActivity>(WebViewActivity::class.java, false, false)

    @Test
    fun webViewActivity_isDisplyed(){

        val intent = Intent()
        intent.putExtra(WebViewActivity.EXTRA_ARTICLE_URL, "https://stackoverflow.com/questions/31752303/espresso-startactivity-that-depends-on-intent/45502924")

        mActivityRule.launchActivity(intent)

        Thread.sleep(3000)
        onView(withId(R.id.web_view_activity)).check(matches(isDisplayed()))

    }


}
