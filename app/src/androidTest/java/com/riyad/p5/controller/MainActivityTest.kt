package com.riyad.p5.controller

import android.view.Gravity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.riyad.p5.R
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {


    @Rule
    @JvmField
    val mActivityRule = IntentsTestRule<MainActivity>(MainActivity::class.java)


//    @Test
//    fun test_isSearchActivityInView() {
//
//
//        onView(withId(R.id.search_btn)).perform(click())
//        onView(withId(R.id.search_activity))
//            .check(matches(isDisplayed()))
//
//    }


    @Test
    fun test_backPress_toMainActivity() {


        onView(withId(R.id.search_btn)).perform(click())

        onView(withId(R.id.search_activity)).check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }


    @Test
    fun test_articleClicked_toWebView() {

        Thread.sleep(3000)
        onView(
            allOf(
                withId(R.id.rv_article),
                isDisplayed()
            )
        )

            .perform(actionOnItemAtPosition<MainAdapter.ViewHolder>(0, click()))
        intended(IntentMatchers.hasComponent(WebViewActivity::class.java.name))


    }


    @Test
    fun test_isNavDrawerVisible() {

        // Open Drawer to click on navigation
        onView(withId(R.id.drawer_layout))
            .check(matches(isClosed(Gravity.LEFT)))
            .perform(DrawerActions.open())


        onView(withId(R.id.navigation_view))
            .perform(NavigationViewActions.navigateTo(R.id.nav_business))
        Thread.sleep(1000)

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())
        onView(withId(R.id.navigation_view))
            .perform(NavigationViewActions.navigateTo(R.id.nav_mostPopular))
        Thread.sleep(1000)

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())
        onView(withId(R.id.navigation_view))
            .perform(NavigationViewActions.navigateTo(R.id.nav_topStories))
        Thread.sleep(1000)

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())
        onView(withId(R.id.navigation_view))
            .perform(NavigationViewActions.navigateTo(R.id.nav_sport))
        Thread.sleep(1000)

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())
        onView(withId(R.id.navigation_view))
            .perform(NavigationViewActions.navigateTo(R.id.nav_notification))
        Thread.sleep(1000)
        onView(withId(R.id.notification_activity)).check(matches(isDisplayed()))
        intended(IntentMatchers.hasComponent(NotificationActivity::class.java.name))


    }

    @Test
    fun navToSearchActivity() {

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())
        onView(withId(R.id.navigation_view))
            .perform(NavigationViewActions.navigateTo(R.id.nav_search))
        Thread.sleep(1000)
        onView(withId(R.id.search_activity)).check(matches(isDisplayed()))

    }

    @Test
    fun test_changeFragmentAfterSwipe() {

        Thread.sleep(500)
        onView(withId(R.id.main_vp_articles)).perform(swipeLeft())
        onView(withId(R.id.main_tl)).check(matches(hasDescendant(withText("Most Popular"))))

    }

    @Test
    fun test_SportListisNotEmpty() {

        Thread.sleep(500)
        onView(withId(R.id.main_vp_articles)).perform(swipeLeft())
        onView(withId(R.id.main_tl)).check(matches(hasDescendant(withText("Most Popular"))))
        onView(withId(R.id.main_vp_articles)).perform(swipeLeft())
        onView(withId(R.id.main_tl)).check(matches(hasDescendant(withText("BUSINESS"))))
        onView(withId(R.id.main_vp_articles)).perform(swipeLeft())
        onView(withId(R.id.main_tl)).check(matches(hasDescendant(withText("SPORTS"))))
        Thread.sleep(3000)

    }

//    @Test
//    fun onBackPressedWhenNavDrawerOpen() {
//
//        Thread.sleep(500)
//        onView(withId(R.id.drawer_layout))
//            .check(matches(isClosed(Gravity.LEFT)))
//            .perform(DrawerActions.open())
//
//        pressBack()
//
//        onView(withId(R.id.drawer_layout))
//            .check(matches(isClosed(Gravity.LEFT)))
//
//    }

}
