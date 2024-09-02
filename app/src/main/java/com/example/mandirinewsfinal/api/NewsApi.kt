package com.example.mandirinewsfinal.api

import com.example.mandirinewsfinal.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse

    @GET("everything")
    suspend fun getAllNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse

    @GET("everything")
    suspend fun searchArticles(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse
}