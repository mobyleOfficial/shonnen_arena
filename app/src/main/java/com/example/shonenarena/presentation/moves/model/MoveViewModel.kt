package com.example.shonenarena.presentation.moves.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoveViewModel(
    val id: String,
    val name: String,
    val imageUrl: String,
    val description: String,
) : Parcelable