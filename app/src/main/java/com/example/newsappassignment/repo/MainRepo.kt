package com.example.newsappassignment.repo

import com.example.newsappassignment.data.Articles
import com.example.newsappassignment.data.DataState
import com.example.newsappassignment.db.NewsDao
import com.example.newsappassignment.network.RetrofitInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepo constructor(
    private val newsDao: NewsDao,
    private val retrofitInterface: RetrofitInterface
) {

    suspend fun getAllNews(query:String): Flow<DataState<ArrayList<Articles>>> = flow {
        emit(DataState.Loading)
        try {
            val response = retrofitInterface.getAllFeed("dd3901d332f6455fb51be6cb4c6c5048","all")
            val articles = response.articles
//            for(article in articles)
//            {
//                newsDao.insert(article)
//            }
//            val articlesCache = newsDao.getAllNews()
            emit(DataState.Success(articles))
        }
        catch (e:Exception)
        {
            emit(DataState.Error(e))
        }
    }

    suspend fun insertSavedNews(article: Articles){
        newsDao.insert(article)
    }

    suspend fun removeArticle(article: String){
        newsDao.removeArticle(article)
    }

    suspend fun getSavedNews():Flow<DataState<ArrayList<Articles>>> = flow{
        emit(DataState.Loading)
        try {
            val articlesCache = newsDao.getAllSavedNews() as ArrayList<Articles>
            emit(DataState.Success(articlesCache))
        }
        catch (e:Exception)
        {
            emit(DataState.Error(e))
        }
    }
}