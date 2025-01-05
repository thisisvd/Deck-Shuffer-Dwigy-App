package com.hackerrank.android

import com.hackerrank.android.testutils.clickNthItem

class DeckCardsRobot {
    fun clickOnCard(position: Int) {
        R.id.deck.clickNthItem(position)
    }
}