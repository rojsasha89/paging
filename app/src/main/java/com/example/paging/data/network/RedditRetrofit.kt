package com.example.paging.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RedditRetrofit {

    fun buildRetrofit(): RedditService {
        return Retrofit.Builder()
            .baseUrl("http://www.reddit.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()
            .create(RedditService::class.java)
    }

    fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .build()
    }
}