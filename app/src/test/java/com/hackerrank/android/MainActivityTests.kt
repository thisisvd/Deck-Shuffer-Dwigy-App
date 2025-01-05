package com.hackerrank.android

import android.widget.GridView
import android.widget.ImageView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTests {
    private lateinit var scenario: ActivityScenario<MainActivity>
    private val dealtCardsList = mutableListOf(
        R.drawable.ten_clubs,
        R.drawable.nine_clubs,
        R.drawable.eight_clubs,
        R.drawable.seven_clubs,
        R.drawable.six_clubs,
        R.drawable.five_clubs
    )
    private val deckCardsList = mutableListOf(R.drawable.king_of_clubs, R.drawable.king_of_diamonds, R.drawable.king_of_hearts, R.drawable.king_of_spades, R.drawable.ace_of_clubs, R.drawable.ace_of_diamonds, R.drawable.ace_of_hearts, R.drawable.ace_of_spades)
    private val dealtCardsRobot = DealtCardsRobot()
    private val deckCardsRobot = DeckCardsRobot()

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun cleanUp() {
        scenario.moveToState(Lifecycle.State.DESTROYED)
    }

    @Test
    fun testInitialLoad() {
        scenario.onActivity {
            with(it.binding) {
                assertDealtCardsCount(dealtCards.adapter as DealtCardsAdapter, 6)
                assertDealtCards(dealtCards, dealtCardsList)
                assertDeckCardsCount(deck.adapter as DeckAdapter, 8)
                assertDeckCards(deck.adapter as DeckAdapter, deckCardsList)
            }
        }
    }

    @Test
    fun selectionOfDealtCardsUpdatesColorFilter() {
        scenario.onActivity {
            with(it.binding) {
                dealtCardsRobot.clickOnCard(2, (dealtCards.adapter as DealtCardsAdapter).getItemId(2))
                assertDealtCardAtPosition(dealtCards, 2, true)
            }
        }
    }

    @Test
    fun doubleSelectionOfDealtCardsResetsColorFilter() {
        scenario.onActivity {
            with(it.binding) {
                dealtCardsRobot.clickOnCard(1, (dealtCards.adapter as DealtCardsAdapter).getItemId(1))
                dealtCardsRobot.clickOnCard(1, (dealtCards.adapter as DealtCardsAdapter).getItemId(1))
                assertDealtCardAtPosition(dealtCards, 1, false)
            }
        }
    }

    @Test
    fun replaceDealtCardFromDeckVariant1() {
        checkCardReplacement(1,2)
    }

    @Test
    fun replaceDealtCardFromDeckVariant2() {
        checkCardReplacement(5,5)
    }

    @Test
    fun testShuffleDealtCards() {
        scenario.onActivity {
            dealtCardsRobot.clickOnShuffle()
            var count = 0
            for (i in 0 until dealtCardsList.size) {
                val cardChanged = it.binding.dealtCards.getChildAt(i).findViewById<ImageView>(R.id.grid_item_image)
                    .getTag(R.string.drawable_identifer) != dealtCardsList[i]
                if (cardChanged)
                    count++
            }
            assertThat(count).isGreaterThan(1)
        }
    }

    @Test
    fun replaceDealtCardFromDeckAndReselectDeckCard() {
        scenario.onActivity {
            with(it.binding) {
                dealtCardsRobot.clickOnCard(5, (dealtCards.adapter as DealtCardsAdapter).getItemId(5))
                deckCardsRobot.clickOnCard(1)
                val newDealtCards = dealtCardsList.toMutableList().apply { set(5, deckCardsList[1]) }
                val newDeckCards = deckCardsList.toMutableList().apply { set(1, dealtCardsList[5]) }
                assertDealtCards(dealtCards, newDealtCards)
                deckCardsRobot.clickOnCard(2)
                assertDealtCards(dealtCards, newDealtCards)
                assertDeckCards(deck.adapter as DeckAdapter, newDeckCards)
            }
        }
    }

    private fun checkCardReplacement(dealtCardPosition: Int, deckCardPosition: Int) {
        scenario.onActivity {
            with(it.binding) {
                dealtCardsRobot.clickOnCard(dealtCardPosition, (dealtCards.adapter as DealtCardsAdapter).getItemId(dealtCardPosition))
                deckCardsRobot.clickOnCard(deckCardPosition)
                val newDealtCards = dealtCardsList.toMutableList().apply { set(dealtCardPosition, deckCardsList[deckCardPosition]) }
                val newDeckCards = deckCardsList.toMutableList().apply { set(deckCardPosition, dealtCardsList[dealtCardPosition]) }
                assertDealtCards(dealtCards, newDealtCards)
                assertDeckCards(deck.adapter as DeckAdapter, newDeckCards)
            }
        }
    }

    private fun assertDealtCardsCount(adapter: DealtCardsAdapter, count: Int) {
        assertThat(adapter.count).isEqualTo(count)
    }

    private fun assertDealtCardAtPosition(grid: GridView, position: Int, highlighted: Boolean) {
        assertThat(grid.getChildAt(position).findViewById<ImageView>(R.id.grid_item_image).colorFilter != null).isEqualTo(highlighted)
    }

    private fun assertDealtCards(grid: GridView, cards: List<Int>) {
        cards.forEachIndexed { index, cardDrawableId ->
            assertThat(
                grid.getChildAt(index).findViewById<ImageView>(R.id.grid_item_image)
                    .getTag(R.string.drawable_identifer)
            ).isEqualTo(cardDrawableId)
        }
    }

    private fun assertDeckCards(deckAdapter: DeckAdapter, cards: List<Int>) {
        cards.forEachIndexed { index, cardDrawableId ->
            assertThat(deckAdapter.currentList[index]).isEqualTo(cardDrawableId)
        }
    }

    private fun assertDeckCardsCount(adapter: DeckAdapter, count: Int) {
        assertThat(adapter.itemCount).isEqualTo(count)
    }
}