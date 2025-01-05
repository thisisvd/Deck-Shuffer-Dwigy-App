package com.hackerrank.android

import com.hackerrank.android.testutils.click
import com.hackerrank.android.testutils.clickNthItemInGrid

class DealtCardsRobot {
    fun clickOnCard(position: Int, itemId: Long) {
        R.id.dealt_cards.clickNthItemInGrid(position, itemId)
    }

    fun clickOnShuffle() {
        R.id.shuffle_btn.click()
    }
}