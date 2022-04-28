package com.example.shonenarena.infrastructure.main

import com.example.domain.repository.MatchRepository
import com.example.shonenarena.data.remote.repository.match.MatchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {
    @Provides
    @Singleton
    fun getMatchRepository(): MatchRepository = MatchRepositoryImpl()
}