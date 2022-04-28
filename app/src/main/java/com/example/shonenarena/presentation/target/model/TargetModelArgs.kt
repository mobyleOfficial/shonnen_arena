package com.example.shonenarena.presentation.target.model

import android.os.Parcelable
import com.example.shonenarena.presentation.common.model.CharacterViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TargetModelArgs(
    val targetList: List<CharacterViewModel>
) : Parcelable