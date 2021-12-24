package com.example.paging.data.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging.data.models.RedditPost
import com.example.paging.data.network.RedditService
import retrofit2.HttpException
import java.io.IOException

class RedditPagingSource(private val service: RedditService) : PagingSource<String, RedditPost>() {

    override fun getRefreshKey(state: PagingState<String, RedditPost>): String? {
        return null
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, RedditPost> {
        return try {
            val response = service.fetchPosts(loadSize = params.loadSize)
            val listing = response.body()?.data
            val posts = listing?.children?.map { it.data }

            LoadResult.Page(posts ?: emptyList(), listing?.before, listing?.after)

        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}