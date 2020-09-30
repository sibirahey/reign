package com.sibi.reigntest.data.remote

import javax.inject.Inject

class PostRemoteDataSource @Inject constructor(private val postService: PostService) {

    suspend fun getPostSearchByDate(query: String) = postService.getPostSearchByDate(query)
}