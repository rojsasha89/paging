package com.example.paging.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.paging.R

class RedditStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<LoadStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_load_state, parent, false)

        //ItemLoadStateBinding.inflate(LayoutInflater.from(parent.context))

        return LoadStateViewHolder(view, retry)
    }
}


class LoadStateViewHolder(view: View, private val retry: () -> Unit) :
    RecyclerView.ViewHolder(view) {

    private val progressBar: ProgressBar = view.findViewById(R.id.progress_bar)
    private val btnRetry: Button = view.findViewById(R.id.btnRetry)

    init {
        btnRetry.setOnClickListener {
            retry.invoke()
        }
    }

    fun bind(loadState: LoadState) {
        progressBar.isVisible = loadState is LoadState.Loading
        btnRetry.isVisible = loadState !is LoadState.Loading
    }
}