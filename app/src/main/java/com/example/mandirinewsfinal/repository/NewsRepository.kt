package com.example.mandirinewsfinal.repository

import com.example.mandirinewsfinal.api.NewsApi
import com.example.mandirinewsfinal.model.NewsResponse

class NewsRepository(private val api: NewsApi) {
    suspend fun getTopHeadlines(country: String, apiKey: String): NewsResponse {
        return api.getTopHeadlines(country, apiKey)
    }

    suspend fun getAllNews(query: String, apiKey: String): NewsResponse {
        return api.getAllNews(query, apiKey)
    }

    // Metode untuk melakukan pencarian artikel
    suspend fun searchArticles(query: String, apiKey: String): NewsResponse {
        return api.searchArticles(query, apiKey)
    }
}