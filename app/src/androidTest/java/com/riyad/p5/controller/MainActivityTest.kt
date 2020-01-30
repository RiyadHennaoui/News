package com.riyad.p5.controller

import android.view.Gravity
import androidx.test.core.app.ActivityScenario
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

    // TODO Tester si dans l'onglet home la liste des articles n'est pas vide.

// TODO test

    @Rule
    @JvmField
    val mActivityRule = IntentsTestRule<MainActivity>(MainActivity::class.java)
//    val mActivityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)


//    @Before
//
//    fun setUp() {
//
//        val mActivity = mActivityRule.getActivity()
//
//        assertThat(mActivity, notNullValue())
//
//    }

//     @Test
//     fun myListShouldNotBeEmpty() {
//
//         val activityScenario = ActivityScenario.launch(MainActivity::class.java)
//         Thread.sleep(1000)
//         onView(
//             allOf(
//                 withId(R.id.rv_article),
//                 isDisplayed()
//             )
//         )
//             .check(matches(hasMinimumChildCount(1)))
//
//     }


    //TODO Tester l'ouverture de la SearchActivity quand on click sur le bouton search

//    @Test
//    fun test_isSearchActivityInView() {
//
//        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
//
//        onView(withId(R.id.search_btn)).perform(click())
//        onView(withId(R.id.search_activity))
//            .check(matches(isDisplayed()))
//
//    }

//    @Test
//    fun test_navNotificationActivity(){
//
//        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
//        onView(withId(R.id.menu_notification)).perform(click())
//        onView(withId(R.id.notification_activity)).check(matches(isDisplayed()))
//
//    }

//     @Test
//     fun test_navSearchActivity() {
//         val activityScenario = ActivityScenario.launch(MainActivity::class.java)
//
//         onView(withId(R.id.search_btn)).perform(click())
//
//         onView(withId(R.id.search_activity)).check(matches(isDisplayed()))
//
//     }

    @Test
    fun test_backPress_toMainActivity() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.search_btn)).perform(click())

        onView(withId(R.id.search_activity)).check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    //TODO Lorsqu'on clique sur un article qu'il lance la WebViewActivity

       @Test
        fun test_articleClicked_toWebView() {

       val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        Thread.sleep(1000)
         onView(
               allOf(
                 withId(R.id.rv_article),
                   isDisplayed()
             )
           )

               .perform(actionOnItemAtPosition<MainAdapter.ViewHolder>(0, click()))


//         Thread.sleep(1000)
//
//         onView(withId(R.id.web_view)).check(matches(isDisplayed()))

//           Thread.sleep(1000)
//             pressBack()
//         onView(withId(R.id.main)).check(matches(isDisplayed()))
     }


    //TODO Tester l'ouverture des autres fragements >>> ne pas oublier le NavDrawer


    @Test
    fun test_isNavDrawerVisible() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

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
            .perform(NavigationViewActions.navigateTo(R.id.nav_notification))
//        onView(withId(R.id.nav_notification))
//            .perform(click())
       Thread.sleep(1000)
         onView(withId(R.id.notification_activity)).check(matches(isDisplayed()))
        intended(IntentMatchers.hasComponent(NotificationActivity::class.java.name))


    }

     @Test
     fun test_changeFragmentAfterSwipe(){

         val activityScenario = ActivityScenario.launch(MainActivity::class.java)

         Thread.sleep(500)
         onView(withId(R.id.main_vp_articles)).perform(swipeLeft())
         onView(withId(R.id.main_tl)).check(matches(hasDescendant(withText("Most Popular"))))

     }


}
