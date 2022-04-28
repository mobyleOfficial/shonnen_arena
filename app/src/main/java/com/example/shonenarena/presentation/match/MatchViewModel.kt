package com.example.shonenarena.presentation.match

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.model.Character
import com.example.domain.model.Move
import com.example.domain.usecase.GetMatch
import com.example.shonenarena.presentation.characterinformation.model.CharacterInformationArgs
import com.example.shonenarena.presentation.common.base.BaseViewModel
import com.example.shonenarena.presentation.moves.mapper.toViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(private val getMatchUseCase: GetMatch) : BaseViewModel() {
    init {
        getMatch()
    }

    private var limitRoundTime = 100
    private val _roundTimerLiveData = MutableLiveData<Int>()
    val roundTimerLiveData: LiveData<Int> = _roundTimerLiveData

    private val _enemyCharactersLiveData = MutableLiveData<List<Character>>()
    val enemyCharactersLiveData: LiveData<List<Character>> = _enemyCharactersLiveData

    private val _userCharactersLiveData = MutableLiveData<List<Character>>()
    val userCharactersLiveData: LiveData<List<Character>> = _userCharactersLiveData

    fun navigateToCharacterInformationBottomSheet(moveList: List<Move>) {
        navigate(
            MatchFragmentDirections.actionMatchFragmentToCharacterInformationBottomSheet(
                CharacterInformationArgs(
                    moveList.map {
                        it.toViewModel()
                    },
                    _enemyCharactersLiveData.value?.map {
                        it.toViewModel()
                    } ?: emptyList()
                )
            )
        )
    }

    private fun getMatch() {
        CoroutineScope(Dispatchers.Main).launch {
            runCatching {
                runBlocking {
                    val match = getMatchUseCase.call("")
                    _enemyCharactersLiveData.postValue(match.enemyCharacters)
                    _userCharactersLiveData.postValue(match.userCharacters)
                }
            }
        }

        updateProgress()
    }

    private fun updateProgress() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                playerCountdown()
                    .onEach {
                        _roundTimerLiveData.postValue(it)
                    }
                    .launchIn(this)
            }
        }
    }

    private suspend fun playerCountdown() = flow {
        while (limitRoundTime >= 0) {
            delay(1000)
            emit(limitRoundTime)
            limitRoundTime--
        }
    }
}