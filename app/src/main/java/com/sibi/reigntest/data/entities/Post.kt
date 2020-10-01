package com.sibi.reigntest.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @PrimaryKey
    val story_id: Long,
    val story_title: String?,
    val title: String?,
    val author: String,
    val created_at: String,
    val story_url: String?,
    val url: String?
)
