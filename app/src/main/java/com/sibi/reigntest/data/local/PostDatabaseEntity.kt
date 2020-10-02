package com.sibi.reigntest.data.local

import androidx.room.ColumnInfo
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
    val url: String?,
    val is_delete: Boolean = false
)

@Entity
data class PostDatabaseEntityUpdate(
    @ColumnInfo(name = "story_id")
    val story_id: Long,
    @ColumnInfo(name = "is_delete")
    val is_delete: Boolean = true
)