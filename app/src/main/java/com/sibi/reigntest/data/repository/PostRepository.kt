package com.sibi.reigntest.data.repository

import androidx.lifecycle.LiveData
import com.sibi.reigntest.data.entities.Post
import com.sibi.reigntest.data.local.PostDao
import com.sibi.reigntest.data.remote.PostRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val remoteDataSource: PostRemoteDataSource,
    private val localDataSource: PostDao
) {

    val posts: LiveData<List<Post>> = localDataSource.getPost()

    suspend fun refreshPosts(query: String) {
        withContext(Dispatchers.IO) {
            val response = remoteDataSource.getPostSearchByDate(query)
            if (response.isSuccessful) {
                response.body()?.let {
                    localDataSource.insertAll(it.hits)
                }
            }
        }
    }


}