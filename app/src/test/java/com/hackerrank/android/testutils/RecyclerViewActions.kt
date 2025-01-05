package com.hackerrank.android.testutils

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import com.hackerrank.android.testutils.RecyclerViewCustomActions.clickChild
import org.hamcrest.Matcher

fun @receiver:IdRes Int.clickNthItem(n: Int) {
    onView(this).perform(
        RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(n),
        RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(n,
            ViewActions.click()
        )
    )
}

fun @receiver:IdRes Int.clickNthItemChild(n: Int, @IdRes childId: Int) {
    onView(this).perform(
        RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(n),
        RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(n,
            clickChild(childId)
        )
    )
}

object RecyclerViewCustomActions {
    fun clickChild(@IdRes id: Int) = object : ViewAction {
        override fun getDescription(): String {
            return "Click on immediate child with matching id"
        }

        override fun getConstraints(): Matcher<View> {
            return isAssignableFrom(View::class.java)
        }

        override fun perform(uiController: UiController?, view: View) {
            view.findViewById<View>(id).performClick()
        }
    }
}

