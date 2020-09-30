package com.sibi.reigntest.data.remote

import com.sibi.reigntest.data.entities.PostList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {

    @GET("search_by_date")
    suspend fun getPostSearchByDate(@Query("query") query: String): Response<PostList>
}