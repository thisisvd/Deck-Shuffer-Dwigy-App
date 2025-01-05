package com.hackerrank.android.testutils

import androidx.annotation.IdRes
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText

fun @receiver:IdRes Int.enterText(text: String) {
    this.wait()
    onView(this).perform(clearText(), replaceText(text))
    closeSoftKeyboard()
}



