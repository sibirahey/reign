package com.sibi.reigntest.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostDatabaseEntity(
    @PrimaryKey
    val story_id: Long,
    val story_title: String?,
    val title: String?,
    val author: String?,
    val created_at: String,
    val story_url: String?,
    val url: String?
)