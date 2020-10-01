package com.sibi.reigntest.data.remote

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostRemoteDTO(
    @PrimaryKey
    val story_id: Long,
    val story_title: String?,
    val title: String?,
    val author: String,
    val created_at: String,
    val story_url: String?,
    val url: String?
)
