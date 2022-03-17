package com.farid.proyekfootballeague.ui.searchmatch

import android.widget.AutoCompleteTextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.farid.proyekfootballeague.R
import com.farid.proyekfootballeague.testing.SingleFragmentActivity
import com.farid.proyekfootballeague.utils.RecyclerViewItemCountAssertion
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SearchMatchFragmentTest {

    @Rule
    @JvmField
    val activityTestRule: ActivityTestRule<SingleFragmentActivity> =
        ActivityTestRule(SingleFragmentActivity::class.java)

    private val fragmentSearch: SearchMatchFragment = SearchMatchFragment()

    @Before
    fun setUp() {
        activityTestRule.activity.setFragment(fragmentSearch)
    }

    @Test
    fun loadSearch() {
        onView(withId(R.id.rv_search_match)).check(RecyclerViewItemCountAssertion(25))
        onView(withId(R.id.search)).perform(click())
        onView(isAssignableFrom(AutoCompleteTextView::class.java)).perform(typeText("juventus"))
        onView(withId(R.id.rv_search_match)).check(RecyclerViewItemCountAssertion(3))
    }
}