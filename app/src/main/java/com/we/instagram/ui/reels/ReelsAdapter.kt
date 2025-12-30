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
        // Only bind non-video UI elements here
        holder.bindData(reels[position], onLikeClick)
    }

    override fun getItemCount(): Int = reels.size

    // This handles the transfer of the player to the new view
    fun onPageSelected(position: Int, recyclerView: RecyclerView) {
        val holder = recyclerView.findViewHolderForAdapterPosition(position) as? ReelViewHolder

        // 1. Reset player state
        player?.stop()
        player?.clearMediaItems()

        // 2. Attach the player to the CURRENT ViewHolder's PlayerView
        holder?.setPlayer(player)

        // 3. Prepare and play the new video
        if (position < reels.size) {
            val reel = reels[position]
            player?.apply {
                setMediaItem(MediaItem.fromUri(reel.videoUrl))
                prepare()
                playWhenReady = true
            }
        }
    }

    class ReelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val playerView: PlayerView = view.findViewById(R.id.playerView)
        private val likeBtn: ImageButton = view.findViewById(R.id.btnLike)

        fun bindData(reel: ReelEntity, onLikeClick: (ReelEntity) -> Unit) {
            likeBtn.setImageResource(
                if (reel.isLiked) R.drawable.ic_heart_filled else R.drawable.ic_heart_outline
            )
            likeBtn.setOnClickListener { onLikeClick(reel) }
        }

        fun setPlayer(player: ExoPlayer?) {
            playerView.player = player
        }
    }
}