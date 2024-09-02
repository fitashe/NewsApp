package com.example.mandirinewsfinal

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mandirinewsfinal.adapter.NewsAdapter
import com.example.mandirinewsfinal.api.RetrofitInstance
import com.example.mandirinewsfinal.databinding.ActivityMainBinding
import com.example.mandirinewsfinal.repository.NewsRepository
import com.example.mandirinewsfinal.repository.NewsViewModel
import com.example.mandirinewsfinal.repository.NewsViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var searchResultsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = NewsRepository(RetrofitInstance.api)
        val viewModelFactory = NewsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewsViewModel::class.java)

        setupRecyclerViews()
        observeViewModel()

        viewModel.fetchTopHeadlines("jp", "f4dbf633d8cd441cb9be18f06d053597")
        viewModel.fetchAllNews("health", "f4dbf633d8cd441cb9be18f06d053597")

        // Setup search functionality
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    performSearch(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun setupRecyclerViews() {
        newsAdapter = NewsAdapter()
        searchResultsAdapter = NewsAdapter()

        binding.rvNewsAll.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = newsAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.topHeadlines.observe(this) { article ->
            if (article != null) {
                binding.headlineTitle.text = article.title
                binding.headlineSource.text = article.source.name
                Glide.with(binding.ivHeadlineImage.context).load(article.urlToImage).into(binding.ivHeadlineImage)
                binding.cvHeadlineNews.setOnClickListener{
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                    startActivity(intent)
                }
            }
        }

        viewModel.allNews.observe(this, { articles ->
            Log.d("MainActivity", "All News: ${articles.size}")
            newsAdapter.submitList(articles)
        })

        viewModel.searchedArticles.observe(this) { articles ->
            articles?.let {
                // Tampilkan searchResultsLayout dan update RecyclerView hasil pencarian
                newsAdapter.submitList(it)
            }
        }
    }

    private fun performSearch(query: String) {
        viewModel.searchArticles(query, "f4dbf633d8cd441cb9be18f06d053597")
    }
}
