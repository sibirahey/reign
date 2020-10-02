package com.sibi.reigntest.data.remote

data class PostRemoteDTO(
    val objectID: Long,
    val story_title: String?,
    val title: String?,
    val author: String,
    val created_at: String,
    val story_url: String?,
    val url: String?
)
