package com.hackerrank.android.testutils

import android.view.View
import android.widget.GridView
import androidx.annotation.IdRes
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

fun @receiver:IdRes Int.clickNthItemInGrid(n: Int, itemId: Long) {
    onView(this).perform(
        GridViewCustomActions.clickOnItem(n, itemId)
    )
}

internal object GridViewCustomActions {
    fun clickOnItem(position: Int, itemid: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return ViewMatchers.isAssignableFrom(GridView::class.java)
            }

            override fun getDescription(): String {
                return ""
            }

            override fun perform(uiController: UiController, view: View) {
                val grid = view as GridView
                grid.performItemClick(grid.getChildAt(position), position, itemid)
            }
        }
    }
}