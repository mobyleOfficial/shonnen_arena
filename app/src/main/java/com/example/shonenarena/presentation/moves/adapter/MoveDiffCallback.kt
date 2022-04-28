package com.example.shonenarena.presentation.moves.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.model.Move

class MoveDiffCallback (
    private val oldList: List<Move>,
    private val newList: List<Move>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}