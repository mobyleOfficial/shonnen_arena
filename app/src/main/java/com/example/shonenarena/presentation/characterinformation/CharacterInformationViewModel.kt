package com.example.shonenarena.presentation.characterinformation

import com.example.shonenarena.presentation.common.base.BaseViewModel
import com.example.shonenarena.presentation.common.model.CharacterViewModel
import com.example.shonenarena.presentation.target.model.TargetModelArgs
import javax.inject.Inject


class CharacterInformationViewModel @Inject constructor(): BaseViewModel() {
    fun navigateToTargets(targetsList: List<CharacterViewModel>) {
        navigate(
            CharacterInformationBottomSheetDirections.actionCharacterInformationBottomSheetToTargetsDialog(
                TargetModelArgs(targetsList)
            )
        )
    }
}