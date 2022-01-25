package com.example.newsappassignment.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsappassignment.data.Articles

@Database(entities = [Articles::class], version = 1)
abstract class NewsDataBase : RoomDatabase()
{
    abstract fun newsDao():NewsDao

    companion object{
        val DATABASE_NAME:String = "news_db"
    }
}