package com.sibi.reigntest.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sibi.reigntest.R
import com.sibi.reigntest.data.repository.PostDomainModel

class PostAdapter(context: Context, private val onPostClicked: (PostDomainModel) -> Unit) :
    ListAdapter<PostDomainModel, PostAdapter.PostViewHolder>(PostDiffCallback()) {
    private val layoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = layoutInflater.inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onPostClicked(item)
        }
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val postTitle = itemView.findViewById<TextView>(R.id.story_title_tv)
        private val authorText = itemView.findViewById<TextView>(R.id.author_and_created_tv)

        fun bind(post: PostDomainModel) {
            postTitle.text = post.title ?: itemView.context.getString(R.string.no_title)
            authorText.text =
                itemView.context.getString(
                    R.string.author_and_created,
                    post.author,
                    post.createdAt
                )
        }

    }
}