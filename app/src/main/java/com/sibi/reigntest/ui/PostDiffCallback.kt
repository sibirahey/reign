package com.sibi.reigntest.ui

import androidx.recyclerview.widget.DiffUtil
import com.sibi.reigntest.data.repository.PostDomainModel

class PostDiffCallback : DiffUtil.ItemCallback<PostDomainModel>() {
    override fun areItemsTheSame(oldItem: PostDomainModel, newItem: PostDomainModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostDomainModel, newItem: PostDomainModel): Boolean {
        return oldItem == newItem
    }
}