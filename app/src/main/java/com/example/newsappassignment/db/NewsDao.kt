package com.example.newsappassignment.db

import androidx.room.*
import com.example.newsappassignment.data.Articles

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(articles: Articles):Long

    @Query("DELETE FROM news WHERE url like :articleId")
    suspend fun removeArticle(articleId: String)

//    @Query("SELECT * FROM news ")
//    suspend fun getAllNews():ArrayList<Articles>

    @Query("SELECT * FROM news WHERE isSaved = 1")
    suspend fun getAllSavedNews():List<Articles>
}