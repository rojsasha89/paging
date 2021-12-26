package com.example.paging.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.paging.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    private val adapter by lazy { RedditAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecycler()
        setupViewModel()
        setupListeners()
    }

    private fun setupListeners() {
        binding.swipeRefresh.setOnRefreshListener {
            adapter.refresh()
        }
    }

    private fun setupRecycler() {
        binding.recycler.adapter = adapter
        binding.recycler.adapter = adapter.withLoadStateFooter(
            footer = RedditStateAdapter { adapter.retry() }
        )
    }

    private fun setupViewModel() {
        lifecycleScope.launch {
            viewModel.loadPosts().collectLatest {
                adapter.submitData(it)
                binding.swipeRefresh.isRefreshing = false
            }
        }
    }
}