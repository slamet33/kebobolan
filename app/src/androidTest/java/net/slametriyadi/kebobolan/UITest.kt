package net.slametriyadi.kebobolan

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import net.slametriyadi.kebobolan.R.id.*
import net.slametriyadi.kebobolan.match.last.LastFragment.Companion.list_last_event
import net.slametriyadi.kebobolan.match.last.LastFragment.Companion.spinner_last_event
import net.slametriyadi.kebobolan.match.next.NextFragment.Companion.list_next_match
import net.slametriyadi.kebobolan.teams.TeamsFragment.Companion.list_team
import net.slametriyadi.kebobolan.teams.TeamsFragment.Companion.spinner_team
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UITest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun matchFunction() {
        Thread.sleep(4000)
        onView(withId(main_container)).check(matches(isDisplayed()))
        onView(withId(bottom_layout)).check(matches(isDisplayed()))
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(tabs_main)).check(matches(isDisplayed()))
        onView(withId(viewpager_main)).check(matches(isDisplayed()))
        onView(withId(list_last_event)).check(matches(isDisplayed()))


        onView(withId(spinner_last_event)).check(matches(isDisplayed()))
        onView(withId(spinner_last_event)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("German Bundesliga"))).perform(click())

        Thread.sleep(4000)

        onView(withId(list_last_event)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
        onView(withId(list_last_event)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                9,
                click()
            )
        )
        Thread.sleep(4000)
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(ViewActions.click())
        Thread.sleep(4000)

        Espresso.pressBack()

        Thread.sleep(4000)

        onView(withId(action_search)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(typeText("Man U"), pressImeActionButton())
        Thread.sleep(3000)

        Espresso.pressBack()

        Thread.sleep(4000)
        Espresso.pressBack()

        onView(withId(viewpager_main)).perform(swipeLeft())

        onView(withId(list_next_match)).check(matches(isDisplayed()))
        onView(withId(list_next_match)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
        onView(withId(list_next_match)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                9,
                click()
            )
        )
        Thread.sleep(4000)
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        Thread.sleep(4000)
    }

    @Test
    fun teamFunction() {
        Thread.sleep(4000)
        onView(withId(teams_menu)).check(matches(isDisplayed()))
        onView(withId(teams_menu)).perform(click())
        Thread.sleep(4000)
        onView(withId(action_search)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(typeText("Arsenal"), pressImeActionButton())
        Thread.sleep(3000)
        Espresso.pressBack()
        Espresso.pressBack()
        onView(withId(spinner_team)).check(matches(isDisplayed()))
        onView(withId(spinner_team)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("Spanish La Liga"))).perform(click())
        Thread.sleep(3000)
        onView(withId(list_team)).check(matches(isDisplayed()))
        onView(withId(list_team)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                3,
                click()
            )
        )
        Thread.sleep(3000)
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        Thread.sleep(3000)

        onView(withId(containerDetailTeam)).perform(swipeLeft())

        Thread.sleep(3000)

        onView(withId(R.id.list_player)).check(matches(isDisplayed()))
        onView(withId(R.id.list_player)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        Thread.sleep(4000)
        Espresso.pressBack()
        Espresso.pressBack()
        Thread.sleep(2000)
    }

    @Test
    fun favoriteFunction() {
        Thread.sleep(4000)
        onView(withId(favorite_menu)).check(matches(isDisplayed()))
        onView(withId(favorite_menu)).perform(click())
        Thread.sleep(4000)

        onView(withId(viewpager_main)).perform(swipeLeft())

    }

}