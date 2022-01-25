package com.example.newsappassignment.di

import com.example.newsappassignment.db.NewsDao
import com.example.newsappassignment.network.RetrofitInterface
import com.example.newsappassignment.repo.MainRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Singleton
    @Provides
    fun providesMainRepo(
        newsDao: NewsDao,
        retrofitInterface: RetrofitInterface
    ):MainRepo
    {
        return MainRepo(newsDao,retrofitInterface)
    }
}