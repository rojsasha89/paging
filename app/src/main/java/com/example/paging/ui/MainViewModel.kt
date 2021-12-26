package com.example.paging.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paging.data.models.RedditPost
import com.example.paging.data.network.RedditService
import com.example.paging.data.repositories.RedditPagingSource
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val service: RedditService) : ViewModel() {

    fun loadPosts(): Flow<PagingData<RedditPost>> {
        return Pager(
            PagingConfig(pageSize = 10, prefetchDistance = 5),
            pagingSourceFactory = { RedditPagingSource(service) }).flow.cachedIn(viewModelScope)
    }
}