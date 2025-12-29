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
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int = posts.size

    class FeedViewHolder(
        view: View,
        private val onLikeClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        private val username: TextView = view.findViewById(R.id.tvUsername)
        private val caption: TextView = view.findViewById(R.id.tvCaption)
        private val likes: TextView = view.findViewById(R.id.tvLikes)
        private val image: ImageView = view.findViewById(R.id.ivPost)
        private val btnLike: ImageButton = view.findViewById(R.id.btnLike)

        fun bind(post: PostEntity) {
            username.text = post.username
            caption.text = post.caption
            likes.text = "${post.likes} likes"
            image.load(post.imageUrl)

            btnLike.setImageResource(
                if (post.isLiked)
                    R.drawable.ic_heart_filled
                else
                    R.drawable.ic_heart_outline
            )

            btnLike.setOnClickListener {
                onLikeClick(post.id)
            }
        }
    }
}