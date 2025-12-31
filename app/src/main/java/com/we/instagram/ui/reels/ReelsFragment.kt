package com.we.instagram.ui.reels

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.material.snackbar.Snackbar
import com.we.instagram.R
import kotlinx.coroutines.launch

class ReelsFragment : Fragment(R.layout.fragment_reels) {

    private val viewModel: ReelsViewModel by viewModels {
        ReelsViewModelFactory(requireContext())
    }

    private lateinit var adapter: ReelsAdapter
    private lateinit var player: ExoPlayer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = view.findViewById<ViewPager2>(R.id.vpReels)

        player = ExoPlayer.Builder(requireContext()).build().apply {
            volume = 0f // 🔇 muted by default
        }

        adapter = ReelsAdapter { reel ->
            viewModel.onLikeClicked(reel)
        }

        adapter.attachPlayer(player)
        viewPager.adapter = adapter

        // 🔥 Collect reels
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.reels.collect { list ->
                    adapter.submitList(list)

                    if (list.isNotEmpty()) {
                        viewPager.post {
                            val rv = viewPager.getChildAt(0) as RecyclerView
                            adapter.onPageSelected(0, rv)
                        }
                    }
                }
            }
        }

        // ❌ Error snackbar
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.error.collect { message ->
                    Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
                }
            }
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val recyclerView = viewPager.getChildAt(0) as RecyclerView
                adapter.onPageSelected(position, recyclerView)
            }
        })
    }

    override fun onStop() {
        super.onStop()
        player.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        player.release()
    }
}