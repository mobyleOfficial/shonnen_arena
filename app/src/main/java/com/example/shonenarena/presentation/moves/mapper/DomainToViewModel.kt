package com.example.shonenarena.presentation.moves.mapper

import com.example.domain.model.Character
import com.example.domain.model.Move
import com.example.shonenarena.presentation.common.model.CharacterViewModel
import com.example.shonenarena.presentation.moves.model.MoveViewModel

fun Move.toViewModel() = MoveViewModel(
    id, name, imageUrl, description
)

fun Character.toViewModel() = CharacterViewModel(
    id, name, imageUrl
)