package com.example.shonenarena.presentation.moves.adapter

import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.domain.model.Move
import com.example.shonenarena.databinding.InformationBoxLayoutBinding

class MoveItemViewHolder(
    private val binding: InformationBoxLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(move: Move) {
        with(binding) {
            title.text = move.name
            description.text = move.description
            progressBar.visibility = View.VISIBLE

            Glide.with(root)
                .load(move.imageUrl)
                //.error(R.drawable.ic_round_account_circle)
                .listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable?>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable?>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }
                })
                .centerCrop()
                .into(image)
        }
    }
}