package com.we.instagram.ui.reels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import com.we.instagram.R
import com.we.instagram.data.reels.local.ReelEntity

class ReelsAdapter(
    private val onLikeClick: (ReelEntity) -> Unit
) : RecyclerView.Adapter<ReelsAdapter.ReelViewHolder>() {

    private val reels = mutableListOf<ReelEntity>()

    // 🔥 Shared ExoPlayer (BEST PRACTICE)
    private var player: ExoPlayer? = null

    fun submitList(list: List<ReelEntity>) {
        reels.clear()
        reels.addAll(list)
        notifyDataSetChanged()
    }

    fun attachPlayer(player: ExoPlayer) {
        this.player = player
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reel, parent, false)
        return ReelViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReelViewHolder, position: Int) {
        holder.bind(reels[position], player, onLikeClick)
    }

    override fun getItemCount(): Int = reels.size

    fun playAt(position: Int) {
        val reel = reels[position]
        player?.apply {
            setMediaItem(MediaItem.fromUri(reel.videoUrl))
            prepare()
            playWhenReady = true
        }
    }

    fun pause() {
        player?.playWhenReady = false
    }

    class ReelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val playerView: PlayerView = view.findViewById(R.id.playerView)
        private val likeBtn: ImageButton = view.findViewById(R.id.btnLike)

        fun bind(
            reel: ReelEntity,
            player: ExoPlayer?,
            onLikeClick: (ReelEntity) -> Unit
        ) {
            playerView.player = player

            likeBtn.setImageResource(
                if (reel.isLiked)
                    R.drawable.ic_heart_filled
                else
                    R.drawable.ic_heart_outline
            )

            likeBtn.setOnClickListener {
                onLikeClick(reel)
            }
        }
    }
}