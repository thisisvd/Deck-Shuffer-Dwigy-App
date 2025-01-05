# 1599024-Android-Kotlin-Deck-Shuffler


Hey Hacker!

Knowledge about a Deck of playing cards has always been a part of mathematics problems which are fun solving. Today we're taking it a notch above to create an interactive experience of shuffling and swapping.



Problem Statement:

Create an app to display a grid of 6 cards also referred to as dealt cards and a horizontally scrollable set of 8 cards referred to as deck. The app should offer the following functionalities:

1. Shuffle the dealt cards randomly when SHUFFLE button is clicked.

2. Swap a dealt card with a card from the deck by tapping on card to be swapped followed by the card to swap with. Example: If the second dealt card is tapped and then the first deck card is tapped, the 2nd card from dealt cards must be swapped with 1st card from the deck. If an already selected dealt card is tapped again, it must be unselected.



The project already has boilerplate code to create the required UI according to the functionality specified. Your task is to connect the dots and code the missing pieces for the app to work as described above.



The classes needing code completion: MainActivity.kt, DeckAdapter.kt, DealtCardsAdapter.kt are open in the editor by default. You have to complete the code in the following methods and lines where comments are written:



setupListeners() [MainActivity.kt]

Write code to shuffle the dealt cards and update the grid in the UI.



setupDealtCards() [MainActivity.kt]

Write code to attach the adapter to the dealtCards GridView and update the selected card in the dealtCards using the position provided by the OnItemClickListener callback.



setupDeck() [MainActivity.kt]

Write code to attach the adapter to the deck RecyclerView.



swapDealtCardWithDeckCard(deckPosition: Int) [MainActivity.kt]

Write code to swap the selected card in the dealtCards with the selected card from the deck.



bindImageAndSetListener(binding: DeckItemBinding, position: Int, drawableId: Int) [DeckAdapter.kt]

Write code to bind the image to the ViewHolder and invoke the itemTapListener when any item is tapped.

