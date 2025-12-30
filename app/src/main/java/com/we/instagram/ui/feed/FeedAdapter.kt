package com.we.instagram.ui.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.we.instagram.R
import com.we.instagram.data.feed.local.PostEntity

class FeedAdapter(
    private val onLikeClick: (String) -> Unit
) : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    private val posts = mutableListOf<PostEntity>()

    fun submitList(newPosts: List<PostEntity>) {
        posts.clear()
        posts.addAll(newPosts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return FeedViewHolder(view, onLikeClick)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        // Corrected: Use FeedViewHolder and post data
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int = posts.size

    class FeedViewHolder(
        view: View,
        private val onLikeClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        private val userImage: ImageView = view.findViewById(R.id.ivUserImage)
        private val username: TextView = view.findViewById(R.id.tvUsername)
        private val postImage: ImageView = view.findViewById(R.id.ivPost)
        private val likes: TextView = view.findViewById(R.id.tvLikes)
        private val btnLike: ImageButton = view.findViewById(R.id.btnLike)

        fun bind(post: PostEntity) {
            username.text = post.userName
            likes.text = "${post.likeCount} likes"

            // Load User Avatar
            userImage.load(post.userImage) {
                crossfade(true)
                placeholder(R.drawable.ic_user_placeholder)
                error(R.drawable.ic_user_placeholder)
            }

            // Load Main Post Image
            postImage.load(post.postImage) {
                crossfade(true)
            }

            // Update Like UI
            btnLike.setImageResource(
                if (post.likedByUser)
                    R.drawable.ic_heart_filled
                else
                    R.drawable.ic_heart_outline
            )

            btnLike.setOnClickListener {
                onLikeClick(post.postId)
            }
        }
    }
}