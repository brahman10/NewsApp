package com.example.newsappassignment.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ParentResponse(
    @SerializedName("status") var status: String?= null,
    @SerializedName("totalResults") var totalResults : Int?= null,
    @SerializedName("articles") var articles: ArrayList<Articles> = arrayListOf()
)

@Entity(tableName = "news")
data class Articles (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int?=null,

    @ColumnInfo(name = "author")
    @SerializedName("author")
    var author: String? = null,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    var title: String? = null,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    var description: String? = null,

    @ColumnInfo(name = "url")
    @SerializedName("url")
    var url: String? = null,

    @ColumnInfo(name = "urlToImage")
    @SerializedName("urlToImage")
    var urlToImage: String? = null,

    @ColumnInfo(name = "publishedAt")
    @SerializedName("publishedAt")
    var publishedAt: String? = null,

    @ColumnInfo(name = "content")
    @SerializedName("content")
    var content: String? = null

)

//@Entity(tableName = "source")
//data class Source (
//
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "sourceId")
//    var sourceId: Int?=null,
//
//    @ColumnInfo(name = "id")
//    @SerializedName("id"   )
//    var id   : String? = null,
//
//    @ColumnInfo(name = "name")
//    @SerializedName("name" )
//    var name : String? = null
//)


