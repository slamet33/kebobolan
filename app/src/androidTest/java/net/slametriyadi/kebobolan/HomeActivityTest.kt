package net.slametriyadi.kebobolan


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeLeft
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
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
class HomeActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun homeActivityTest() {
        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.teams_menu), withContentDescription("Teams"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_navigation),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        val _LinearLayout = onView(
            allOf(
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("org.jetbrains.anko._RelativeLayout")),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        _LinearLayout.perform(click())

        val tabView = onView(
            allOf(
                withContentDescription("Players"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tabsDetailTeam),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        tabView.perform(click())

        val viewPager = onView(
            allOf(
                withId(R.id.containerDetailTeam),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        viewPager.perform(swipeLeft())

        val recyclerView = onView(
            allOf(
                withId(R.id.list_player), childAtPosition(withClassName(`is`("android.widget.FrameLayout")),
                    0)))
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(8, click()))
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
