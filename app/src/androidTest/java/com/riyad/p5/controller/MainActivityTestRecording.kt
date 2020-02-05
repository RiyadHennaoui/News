package com.riyad.p5.controller


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.riyad.p5.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTestRecording {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTestRecording() {

        Thread.sleep(4000)
        val imageView = onView(
            allOf(
                withId(R.id.item_main_iv_thumbnail),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.item_view),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val textView = onView(
            allOf(
                withId(R.id.item_main_tv_date_pub), withText("05 February 2020"),
                childAtPosition(
                    allOf(
                        withId(R.id.frame_layout_pub_date),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.widget.RelativeLayout::class.java),
                            2
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("05 February 2020")))

        val textView2 = onView(
            allOf(
                withId(R.id.item_main_tv_section), withText("us"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.item_view),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("us")))

        val textView3 = onView(
            allOf(
                withId(R.id.item_main_tv_title),
                withText("Trump Claims End of ‘American Decline’ While Avoiding Mention of Impeachment"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.item_view),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("Trump Claims End of ‘American Decline’ While Avoiding Mention of Impeachment")))

        val textView4 = onView(
            allOf(
                withId(R.id.item_main_iv_desc),
                withText("The interactions between President Trump and Speaker Nancy Pelosi during the State of the Union address underscored the bitterness the move to remove him from office has caused."),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.item_view),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("The interactions between President Trump and Speaker Nancy Pelosi during the State of the Union address underscored the bitterness the move to remove him from office has caused.")))

        val textView5 = onView(
            allOf(
                withId(R.id.item_main_iv_desc),
                withText("The interactions between President Trump and Speaker Nancy Pelosi during the State of the Union address underscored the bitterness the move to remove him from office has caused."),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.item_view),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("The interactions between President Trump and Speaker Nancy Pelosi during the State of the Union address underscored the bitterness the move to remove him from office has caused.")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
