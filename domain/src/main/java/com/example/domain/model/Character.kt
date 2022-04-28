package com.example.domain.model

data class Character(
    val id: String,
    val health: Int,
    val name: String,
    val imageUrl: String,
    val description: String,
    val moves: List<Move>
)
