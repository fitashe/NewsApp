package com.example.mandirinewsfinal.repository

import androidx.lifecycle.*
import com.example.mandirinewsfinal.model.Article
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    private val _topHeadlines = MutableLiveData<Article>()
    val topHeadlines: LiveData<Article> get() = _topHeadlines

    private val _allNews = MutableLiveData<List<Article>>()
    val allNews: LiveData<List<Article>> get() = _allNews

    private val _searchedArticles = MutableLiveData<List<Article>>()
    val searchedArticles: LiveData<List<Article>> get() = _searchedArticles

    fun fetchTopHeadlines(country: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = repository.getTopHeadlines(country, apiKey)
                if (response.articles.isNotEmpty()) {
                    _topHeadlines.postValue(response.articles[0])
                } else {
                    _topHeadlines.postValue(null)
                }
            } catch (e: Exception) {
                // Handle exception
                _topHeadlines.postValue(null)
            }
        }
    }

    fun fetchAllNews(query: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = repository.getAllNews(query, apiKey)
                _allNews.postValue(response.articles)
            } catch (e: Exception) {
                // Handle exception
                _allNews.postValue(emptyList())
            }
        }
    }

    fun searchArticles(query: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = repository.searchArticles(query, apiKey)
                _searchedArticles.postValue(response.articles)
            } catch (e: Exception) {
                // Handle exception
                _searchedArticles.postValue(emptyList())
            }
        }
    }
}

class NewsViewModelFactory(private val repository: NewsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}