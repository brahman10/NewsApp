package com.example.newsappassignment.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsappassignment.data.Articles

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(articles: Articles):Long

    @Query("SELECT * FROM news ")
    suspend fun getAllSavedNews():List<Articles>
}