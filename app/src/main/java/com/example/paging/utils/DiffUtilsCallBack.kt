package com.example.paging.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.paging.data.models.RedditPost

class DiffUtilsCallBack: DiffUtil.ItemCallback<RedditPost>() {

    override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
        return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
        return oldItem.key == newItem.key
                && oldItem.title == newItem.title
                && oldItem.score == newItem.score
                && oldItem.author == newItem.author
                && oldItem.commentCount == newItem.commentCount
    }
}