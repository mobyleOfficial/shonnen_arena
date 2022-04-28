package com.example.shonenarena.presentation.target

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.shonenarena.databinding.TargetsDialogBinding
import com.example.shonenarena.presentation.common.model.CharacterViewModel

class TargetsDialog : DialogFragment() {
    private lateinit var binding: TargetsDialogBinding
    private val args: TargetsDialogArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TargetsDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            val enemyList = args.characterInformation.targetList
            repeat(enemyList.size) {
                setCharacterData(
                    firstCharacter.characterImage,
                    firstCharacter.lifeProgressBar,
                    firstCharacter.progressBar,
                    enemyList[it]
                )
                setCharacterData(
                    secondCharacter.characterImage,
                    secondCharacter.lifeProgressBar,
                    secondCharacter.progressBar,
                    enemyList[it]
                )
                setCharacterData(
                    thirdCharacter.characterImage,
                    thirdCharacter.lifeProgressBar,
                    thirdCharacter.progressBar,
                    enemyList[it]
                )
            }
        }
    }

    private fun setCharacterData(
        characterImageView: ImageView,
        healthProgressBar: ProgressBar,
        loadingProgressBar: ProgressBar,
        character: CharacterViewModel,
        onClick: (() -> Unit)? = null
    ) {
        healthProgressBar.visibility = View.GONE

        Glide
            .with(this)
            .load(character.imageUrl)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    isFirstResource: Boolean
                ): Boolean {
                    loadingProgressBar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    loadingProgressBar.visibility = View.GONE
                    return false
                }
            })
            .fitCenter()
            .into(characterImageView)

        onClick?.let {
            characterImageView.setOnClickListener {
                it()
            }
        }
    }
}