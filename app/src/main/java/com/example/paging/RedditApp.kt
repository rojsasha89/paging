package com.example.paging

import android.app.Application
import com.example.paging.di.redditModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RedditApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RedditApp)
            redditModules
        }
    }
}