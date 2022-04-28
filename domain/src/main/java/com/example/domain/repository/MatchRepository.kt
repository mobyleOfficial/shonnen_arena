package com.example.domain.repository

import com.example.domain.model.Character
import com.example.domain.model.Match

interface MatchRepository {
    suspend fun getMatch(userId: String) : Match
}