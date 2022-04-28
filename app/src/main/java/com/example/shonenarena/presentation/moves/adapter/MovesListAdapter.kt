package com.example.shonenarena.presentation.moves.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Move
import com.example.shonenarena.databinding.InformationBoxLayoutBinding
import kotlinx.android.synthetic.main.information_box_layout.view.*

class MovesListAdapter(private val onClick: (move: Move) -> Unit) :
    RecyclerView.Adapter<MoveItemViewHolder>() {
    var moveList = emptyList<Move>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                MoveDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoveItemViewHolder {
        val binding =
            InformationBoxLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MoveItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoveItemViewHolder, position: Int) {
        with(holder) {
            val move = moveList[position]
            holder.bind(move)

            itemView.image.setOnClickListener {
                onClick(move)
            }
        }

    }

    override fun getItemCount(): Int = moveList.size
}