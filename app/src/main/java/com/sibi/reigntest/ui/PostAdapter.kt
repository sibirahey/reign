package com.sibi.reigntest.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sibi.reigntest.R
import com.sibi.reigntest.data.entities.Post
import com.sibi.reigntest.util.PostUtil
import com.sibi.reigntest.util.toDate

class PostAdapter(context: Context) :
    ListAdapter<Post, PostAdapter.PostViewHolder>(PostDiffCallback()) {
    private val layoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = layoutInflater.inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val postTitle = itemView.findViewById<TextView>(R.id.story_title_tv)
        private val authorText = itemView.findViewById<TextView>(R.id.author_and_created_tv)

        fun bind(post: Post) {

            val title = when {
                post.story_title?.isNotEmpty() == true -> {
                    post.story_title
                }
                post.title?.isNotEmpty() == true -> {
                    post.title
                }
                else -> {
                    itemView.context.getString(R.string.no_title)
                }
            }

            val createdAt = PostUtil.dateDiff(post.created_at.toDate())

            postTitle.text = title
            authorText.text =
                itemView.context.getString(
                    R.string.author_and_created,
                    post.author,
                    createdAt
                )
        }

    }
}