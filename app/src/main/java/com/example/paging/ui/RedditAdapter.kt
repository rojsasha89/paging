package com.example.paging.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.paging.data.models.RedditPost
import com.example.paging.databinding.ItemRowBinding
import com.example.paging.utils.DiffUtilsCallBack

class RedditAdapter : PagingDataAdapter<RedditPost, RedditVH>(DiffUtilsCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditVH {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context))

        return RedditVH(binding)
    }

    override fun onBindViewHolder(holder: RedditVH, position: Int) {
        holder.bind(getItem(position))
    }
}

class RedditVH(private val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: RedditPost?) {
        binding.score.text = item?.score.toString()
        binding.comments.text = item?.commentCount.toString()
        binding.title.text = item?.title.toString()
    }
}