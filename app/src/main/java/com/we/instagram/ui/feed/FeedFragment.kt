package com.we.instagram.ui.feed

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.we.instagram.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FeedFragment : Fragment(R.layout.fragment_feed) {

    private val viewModel: FeedViewModel by viewModels {
        FeedViewModelFactory(requireContext())
    }

    private lateinit var adapter: FeedAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val offlineText = view.findViewById<TextView>(R.id.tvOffline)
        val swipeRefresh = view.findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvFeed)

        adapter = FeedAdapter { postId ->
            viewModel.onLikeClicked(postId)
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }

        // Observe feed
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.feed.collectLatest {
                adapter.submitList(it)
                swipeRefresh.isRefreshing = false
            }
        }

        // 🔥 Observe offline state (FIX)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isOffline.collectLatest { offline ->
                offlineText.visibility =
                    if (offline) View.VISIBLE else View.GONE
            }
        }
    }
}