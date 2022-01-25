package com.example.newsappassignment.di

import com.example.newsappassignment.network.RetrofitInterface
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGsonBuilder():Gson{
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson:Gson):Retrofit.Builder{
        return Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideRetrofitInterface(retrofit: Retrofit.Builder):RetrofitInterface
    {
        return retrofit.build().create(RetrofitInterface::class.java)
    }
}