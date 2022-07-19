package com.example.skymoviestask

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.example.skymoviestask.utils.BaseUITest
import org.hamcrest.CoreMatchers.not
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieFeature :BaseUITest() {

    @Test
    fun displayScreenTitle() {
        assertDisplayed("SkyMoviesTask")
    }

    @Test
    fun displayListOfMovies() {

        Thread.sleep(4000)

        assertRecyclerViewItemCount(R.id.movie_list, 25)

        onView(
            allOf(
                withId(R.id.movie_title),
                isDescendantOfA(nthChildOf(withId(R.id.movie_list), 0))
            )
        )
            .check(matches(withText("Dunkirk")))
            .check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.movie_year),
                isDescendantOfA(nthChildOf(withId(R.id.movie_list), 0))
            )
        )
            .check(matches(withText("2017")))
            .check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.movie_genre),
                isDescendantOfA(nthChildOf(withId(R.id.movie_list), 0))
            )
        )
            .check(matches(withText("History")))
            .check(matches(isDisplayed()))

    }

    @Test
    fun displayProgressLoaderWhileFetchingMovieList(){
        Thread.sleep(4000)

        assertDisplayed(R.id.loader)
    }

    @Test
    fun hideLoader() {
        assertNotDisplayed(R.id.loader)
    }

}