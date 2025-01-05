package com.hackerrank.android.testutils

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.ViewFinder
import org.hamcrest.Matcher

class ViewIdlingResource(
    private val viewMatcher: Matcher<View>,
    private val idleMatcher: Matcher<View>,
    private val waitTimeInMillis: Long
): IdlingResource {
    private val startTime = System.currentTimeMillis()
    private lateinit var resourceCallback: IdlingResource.ResourceCallback

    override fun getName(): String {
        return "${hashCode()}"
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        resourceCallback = callback
    }

    override fun isIdleNow(): Boolean {
        val view = getViewUsingReflection(viewMatcher)
        val elapsedTime = System.currentTimeMillis() - startTime
        val isIdle = idleMatcher.matches(view)
        if (isIdle || elapsedTime > waitTimeInMillis) resourceCallback.onTransitionToIdle()
        return isIdle
    }

    private fun getViewUsingReflection(viewMatcher: Matcher<View>): View? {
        return try {
            val viewInteraction = Espresso.onView(viewMatcher)
            val finderField = viewInteraction.javaClass.getDeclaredField("viewFinder")
            finderField.isAccessible = true
            val finder = finderField.get(viewInteraction) as ViewFinder
            finder.view
        } catch (_: Exception) {
            null
        }
    }
}