package com.sibi.reigntest.ui

import androidx.recyclerview.widget.DiffUtil
import com.sibi.reigntest.data.entities.Post

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.story_id == newItem.story_id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}