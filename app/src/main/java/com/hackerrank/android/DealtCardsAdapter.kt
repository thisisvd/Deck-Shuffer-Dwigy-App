package com.hackerrank.android

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView


class DealtCardsAdapter(context: Context, drawables: List<Int>): ArrayAdapter<Int>(context, 0, drawables) {
    private var selectedPosition = UNDEFINED

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        listItemView ?: run {
            listItemView = LayoutInflater.from(context).inflate(R.layout.selected_card_item, parent, false)
        }
        val image = listItemView?.findViewById<ImageView>(R.id.grid_item_image)
        getItem(position)?.let {
            image?.setImageResource(it)
            // This line is used for the test cases to work correctly. It must not be removed or modified. Doing so will result in zero score.
            image?.setTag(R.string.drawable_identifer, it)
        }
        image?.let {
            if (selectedPosition == position) {
                it.setColorFilter(R.color.black, android.graphics.PorterDuff.Mode.SRC_ATOP)
            } else {
                it.colorFilter = null
            }
        }
        return listItemView!!
    }
}