package com.hackerrank.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.hackerrank.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val dealtCards = mutableListOf(R.drawable.ten_clubs, R.drawable.nine_clubs, R.drawable.eight_clubs, R.drawable.seven_clubs, R.drawable.six_clubs, R.drawable.five_clubs)
    private lateinit var deckAdapter: DeckAdapter
    private val deck = mutableListOf(R.drawable.king_of_clubs, R.drawable.king_of_diamonds, R.drawable.king_of_hearts, R.drawable.king_of_spades, R.drawable.ace_of_clubs, R.drawable.ace_of_diamonds, R.drawable.ace_of_hearts, R.drawable.ace_of_spades)
    private lateinit var dealtCardsAdapter: DealtCardsAdapter

    private var selectedDealtPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_logo)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupDealtCards()
        setupDeck()
        setupListeners()
    }

    private fun setupListeners() {
        binding.shuffleBtn.setOnClickListener {
            // Write code to shuffle the dealt cards.
            dealtCards.shuffle()
            dealtCardsAdapter.notifyDataSetChanged()
        }
    }

    private fun setupDealtCards() {
        dealtCardsAdapter = DealtCardsAdapter(this, dealtCards)
        with(binding.dealtCards) {
            // Write code to set the adapter to dealtCards
            adapter = dealtCardsAdapter
            setOnItemClickListener { _, _, position, _ ->
                /** Write code here to update the selected card in the dealt cards using the position provided by the OnItemClickListener **/
                if (selectedDealtPosition == position) {
                    selectedDealtPosition = -1
                    dealtCardsAdapter.notifyDataSetChanged()
                } else {
                    selectedDealtPosition = position
                    dealtCardsAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun setupDeck() {
        deckAdapter = DeckAdapter { deckPosition ->
            if (selectedDealtPosition != -1) {
                swapDealtCardWithDeckCard(deckPosition)
            }
        }
        with(binding.deck) {
            // Write code to set the adapter to deck of cards
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            deckAdapter.updateDeck(deck)
            addItemDecoration(HorizontalDecoration(50))
            adapter = deckAdapter
        }
    }

    private fun swapDealtCardWithDeckCard(deckPosition: Int) {
        /** Write code to complete the functionality of this method.
         * It must swap the selected card in the dealt cards with the selected card from the deck **/
        if (selectedDealtPosition != -1) {
            val temp = dealtCards[selectedDealtPosition]
            dealtCards[selectedDealtPosition] = deck[deckPosition]
            deck[deckPosition] = temp
            dealtCardsAdapter.notifyDataSetChanged()
            deckAdapter.notifyItemChanged(deckPosition)
            selectedDealtPosition = -1
        }
    }
}