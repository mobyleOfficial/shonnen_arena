package com.example.domain.usecase

import com.example.domain.model.Match
import com.example.domain.repository.MatchRepository
import javax.inject.Inject

class GetMatch @Inject constructor(private val repository: MatchRepository) :
    UseCase<String, Match>() {
    override suspend fun getRawSuspendFunction(params: String): Match = repository.getMatch(params)
}