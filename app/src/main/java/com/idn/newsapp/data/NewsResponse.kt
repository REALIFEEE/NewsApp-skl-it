package com.idn.newsapp.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import javax.xml.transform.Source

@JsonClass(generateAdapter = true)
data class NewsResponse(
    @Json(name = "totalResult")
    val totalResult: Int? = null,

    @Json(name = "articles")
    var articles: List<ArticlesItems>? = null,

    @Json(name = "status")
    var status: String? = null
)

@JsonClass(generateAdapter = true)
@Parcelize
data class Source(
    @Json(name = "name")
    val name: String? = null
):Parcelable

@JsonClass(generateAdapter = true)
@Parcelize

data class ArticlesItems (
    @Json(name = "publishedAt")
    val publishedAt: String? = null,

    @Json(name = "author")
    val author: String? = null,

    @Json(name = "urlToImage")
    val urlToImage: String? = null,

    @Json(name = "desc")
    val desc: String? = null,

    @Json(name = "source")
    val source: com.idn.newsapp.data.Source? = null,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "url")
    val url: String? = null,

    @Json(name = "content")
    val content: String? = null
):Parcelable