package com.we.instagram.ui.feed

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.we.instagram.R

class FeedFragment : Fragment(R.layout.fragment_feed) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvFeed = view.findViewById<TextView>(R.id.tvFeed)
        tvFeed.text = "Welcome to Feed 🎉"
    }
}