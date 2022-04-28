package com.example.domain.model

data class Match(
    val id: String,
    val userCharacters: List<Character>,
    val enemyCharacters: List<Character>
)