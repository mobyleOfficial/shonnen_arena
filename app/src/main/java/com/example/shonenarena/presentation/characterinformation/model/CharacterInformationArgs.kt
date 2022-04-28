package com.example.shonenarena.presentation.characterinformation.model

import android.os.Parcelable
import com.example.shonenarena.presentation.common.model.CharacterViewModel
import com.example.shonenarena.presentation.moves.model.MoveViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterInformationArgs(
    val moveList: List<MoveViewModel>,
    val enemyList: List<CharacterViewModel>
) : Parcelable