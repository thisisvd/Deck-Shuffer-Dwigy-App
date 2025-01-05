package com.hackerrank.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hackerrank.android.databinding.DeckItemBinding

class DeckAdapter(private val itemTapListener: (Int) -> Unit): ListAdapter<Int, DeckAdapter.DeckViewHolder>(GenericDiffUtil<Int>()) {

    fun updateDeck(drawables: List<Int>) {
        submitList(drawables)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckViewHolder {
        return DeckViewHolder(DeckItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: DeckViewHolder, position: Int) {
        val drawableId = getItem(position)
        with(holder.binding) {
            bindImageAndSetListener(this, position, drawableId)
            // This line is used for the test cases to work correctly. It must not be removed or modified. Doing so will result in zero score.
            itemImage.setTag(R.string.drawable_identifer, drawableId)
        }
    }

    private fun bindImageAndSetListener(binding: DeckItemBinding, position: Int, drawableId: Int) {
        /** Write code to complete the functionality of this method.
         It must bind the image to the ViewHolder and invoke the [itemTapListener] when any item is tapped **/
        binding.itemImage.setImageResource(drawableId)
        binding.root.setOnClickListener {
            itemTapListener.invoke(position)
        }
    }

    class DeckViewHolder(val binding: DeckItemBinding) : RecyclerView.ViewHolder(binding.root)
}

