package com.we.instagram.ui.reels

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.exoplayer2.ExoPlayer
import com.we.instagram.R
import kotlinx.coroutines.flow.collectLatest

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

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.reels.collectLatest {
                adapter.submitList(it)
                if (it.isNotEmpty()) {
                    adapter.playAt(0)
                }
            }
        }

        viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    adapter.playAt(position)
                }
            }
        )
    }

    override fun onPause() {
        super.onPause()
        player.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        player.release()
    }
}