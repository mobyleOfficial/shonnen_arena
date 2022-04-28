package com.example.shonenarena.presentation.characterlist

import com.example.shonenarena.presentation.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(): BaseViewModel() {
    fun navigateToMatch() {
        navigate(
            CharacterListFragmentDirections.actionCharacterListFragmentToMatchFragment()
        )
    }
}