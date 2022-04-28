package com.example.shonenarena.presentation.common.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterViewModel(
    val id: String,
    val name: String,
    val imageUrl: String,
): Parcelable
