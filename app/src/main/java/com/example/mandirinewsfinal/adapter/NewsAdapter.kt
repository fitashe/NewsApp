package com.example.mandirinewsfinal.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mandirinewsfinal.databinding.AllNewsBinding
import com.example.mandirinewsfinal.model.Article

class NewsAdapter : ListAdapter<Article, NewsAdapter.NewsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = AllNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
            holder.itemView.context.startActivity(intent)
        }
    }

    class NewsViewHolder(private val binding: AllNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.newsTitle.text = article.title
            binding.newsSource.text = article.source.name
            binding.newsAuthor.text = article.author
            Glide.with(binding.newsImage.context).load(article.urlToImage).into(binding.newsImage)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article) = oldItem.url == newItem.url
        override fun areContentsTheSame(oldItem: Article, newItem: Article) = oldItem == newItem
    }
}