package com.hackerrank.android.testutils

import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

fun @receiver:IdRes Int.click() {
    this.wait()
    onView(this).perform(ViewActions.click())
}

fun String.click() {
    this.wait()
    onView(this).perform(ViewActions.click())
}

fun @receiver:IdRes Int.checkText(matchingText: String) {
    allOf(withId(this), withText(matchingText)).wait()
    onView(this, matchingText).check(matches(isDisplayed()))
}

fun @receiver:IdRes Int.wait(time: Long = 5000) {
    withId(this).wait(time)
}

fun String.wait(time: Long = 5000) {
    withText(this).wait(time)
}

fun Matcher<View>.wait(time: Long = 5000) {
    val idlingResource = ViewIdlingResource(this, isDisplayed(), time)
    try {
        IdlingRegistry.getInstance().register(idlingResource)
        // First call to onView is to trigger the view idler.
        Espresso.onView(withId(-256)).check(doesNotExist())
    } finally {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }
}