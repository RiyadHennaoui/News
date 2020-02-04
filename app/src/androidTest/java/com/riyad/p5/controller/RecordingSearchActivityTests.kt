package com.riyad.p5.controller


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.riyad.p5.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class RecordingSearchActivityTests {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun recordingSearchActivityTests() {
        val actionMenuItemView = onView(
            allOf(
                withId(R.id.search_btn), withContentDescription("search"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.toolbar),
                        2
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        actionMenuItemView.perform(click())

        val appCompatImageView = onView(
            allOf(
                withId(R.id.search_button), withContentDescription("Search"),
                childAtPosition(
                    allOf(
                        withId(R.id.search_bar),
                        childAtPosition(
                            withId(R.id.et_search),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageView.perform(click())

        val searchAutoComplete = onView(
            allOf(
                withId(R.id.search_src_text),
                childAtPosition(
                    allOf(
                        withId(R.id.search_plate),
                        childAtPosition(
                            withId(R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete.perform(replaceText("Iowa"), closeSoftKeyboard())

        val appCompatTextView = onView(
            allOf(
                withId(R.id.begin_date),
                childAtPosition(
                    allOf(
                        withId(R.id.search_activity),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatTextView.perform(click())

        val appCompatButton = onView(
            allOf(
                withId(android.R.id.button1), withText("OK"),
                childAtPosition(
                    allOf(
                        withClassName(`is`("android.widget.LinearLayout")),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            3
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        val appCompatTextView2 = onView(
            allOf(
                withId(R.id.end_date),
                childAtPosition(
                    allOf(
                        withId(R.id.search_activity),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatTextView2.perform(click())

        val appCompatButton2 = onView(
            allOf(
                withId(android.R.id.button1), withText("OK"),
                childAtPosition(
                    allOf(
                        withClassName(`is`("android.widget.LinearLayout")),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            3
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatButton2.perform(click())

        val appCompatCheckBox = onView(
            allOf(
                withId(R.id.checkbox_1), withText("Business"),
                childAtPosition(
                    allOf(
                        withId(R.id.search_activity),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatCheckBox.perform(click())

        val appCompatCheckBox2 = onView(
            allOf(
                withId(R.id.checkbox_3), withText("Technology"),
                childAtPosition(
                    allOf(
                        withId(R.id.search_activity),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        appCompatCheckBox2.perform(click())

        Thread.sleep(6000)

        val appCompatButton3 = onView(
            allOf(
                withId(R.id.btn_search), withText("Search"),
                childAtPosition(
                    allOf(
                        withId(R.id.search_activity),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        appCompatButton3.perform(click())
        Thread.sleep(6000)

        val appCompatButton4 = onView(
            allOf(
                withId(R.id.btn_search), withText("Search"),
                childAtPosition(
                    allOf(
                        withId(R.id.search_activity),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        appCompatButton4.perform(pressImeActionButton())
        Thread.sleep(6000)

        val linearLayout = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.rv_search_article),
                        childAtPosition(
                            withId(R.id.search_activity),
                            8
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        linearLayout.perform(click())

        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(
                        withId(R.id.tb_web_view),
                        childAtPosition(
                            withId(R.id.web_view_activity),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())
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
