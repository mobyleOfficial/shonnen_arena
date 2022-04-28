package com.example.shonenarena.presentation.match

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.domain.model.Character
import com.example.shonenarena.databinding.FragmentMatchBinding
import com.example.shonenarena.presentation.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchFragment : BaseFragment() {
    lateinit var binding: FragmentMatchBinding
    override val viewModel: MatchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMatchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeEvents()
    }

    private fun setUserCharacters(characterList: List<Character>) {
        with(binding.userChosenCharacters) {
            repeat(characterList.size) {
                setCharacterData(
                    firstCharacter.characterImage,
                    firstCharacter.lifeProgressBar,
                    firstCharacter.progressBar,
                    characterList[it]
                ) {
                    viewModel.navigateToCharacterInformationBottomSheet(characterList[it].moves)
                }
                setCharacterData(
                    secondCharacter.characterImage,
                    secondCharacter.lifeProgressBar,
                    secondCharacter.progressBar,
                    characterList[it]
                ) {
                    viewModel.navigateToCharacterInformationBottomSheet(characterList[it].moves)
                }
                setCharacterData(
                    thirdCharacter.characterImage,
                    thirdCharacter.lifeProgressBar,
                    thirdCharacter.progressBar,
                    characterList[it]
                ) {
                    viewModel.navigateToCharacterInformationBottomSheet(characterList[it].moves)
                }
            }
        }
    }

    private fun setEnemyCharacters(characterList: List<Character>) {
        with(binding.enemyChosenCharacters) {
            repeat(characterList.size) {
                setCharacterData(
                    firstCharacter.characterImage,
                    firstCharacter.lifeProgressBar,
                    firstCharacter.progressBar,
                    characterList[it]
                )
                setCharacterData(
                    secondCharacter.characterImage,
                    secondCharacter.lifeProgressBar,
                    secondCharacter.progressBar,
                    characterList[it]
                )
                setCharacterData(
                    thirdCharacter.characterImage,
                    thirdCharacter.lifeProgressBar,
                    thirdCharacter.progressBar,
                    characterList[it]
                )
            }
        }
    }

    private fun observeEvents() {
        with(viewModel) {
            userCharactersLiveData.observe(viewLifecycleOwner, {
                setUserCharacters(it)
            })

            enemyCharactersLiveData.observe(viewLifecycleOwner, {
                setEnemyCharacters(it)
            })

            roundTimerLiveData.observe(viewLifecycleOwner, {
                binding.timeProgressBar.progress = it
            })
        }
    }

    private fun setCharacterData(
        characterImageView: ImageView,
        healthProgressBar: ProgressBar,
        loadingProgressBar: ProgressBar,
        character: Character,
        onClick: (() -> Unit)? = null
    ) {
        healthProgressBar.progress = character.health

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