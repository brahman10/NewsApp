package com.example.newsappassignment.di

import android.content.Context
import androidx.room.Room
import com.example.newsappassignment.db.NewsDao
import com.example.newsappassignment.db.NewsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NewsDataBase {
        return Room.databaseBuilder(context, NewsDataBase::class.java, NewsDataBase.DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideNewsDao(newsDataBase: NewsDataBase):NewsDao
    {
        return newsDataBase.newsDao()
    }
}