package com.hackerrank.android.testutils

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.allOf

fun onView(@IdRes id: Int): ViewInteraction = Espresso.onView(withId(id))

fun onView(text: String): ViewInteraction = Espresso.onView(withText(text))

fun onView(@IdRes id: Int, text: String): ViewInteraction = Espresso.onView(allOf(withId(id), withText(text)))