package com.example.paging.di

import com.example.paging.data.network.RedditRetrofit
import com.example.paging.data.repositories.RedditPagingSource
import com.example.paging.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val redditModules by lazy {
    loadKoinModules(
        listOf(
            viewModelModules,
            networkModule,
            repositoryModule
        )
    )
}

val viewModelModules = module {
    viewModel { MainViewModel(get()) }
}

val networkModule = module {
    single { RedditRetrofit.buildRetrofit() }
}

val repositoryModule = module {
    single { RedditPagingSource(get()) }
}