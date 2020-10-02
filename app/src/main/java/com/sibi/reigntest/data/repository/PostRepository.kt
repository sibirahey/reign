package com.sibi.reigntest.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.sibi.reigntest.data.local.PostDao
import com.sibi.reigntest.data.local.PostDatabaseEntityUpdate
import com.sibi.reigntest.data.remote.PostRemoteDataSource
import com.sibi.reigntest.util.asDatabaseEntity
import com.sibi.reigntest.util.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val remoteDataSource: PostRemoteDataSource,
    private val localDataSource: PostDao
) {

    val posts: LiveData<List<PostDomainModel>> =
        Transformations.map(localDataSource.getPost()) { localPosts ->
            localPosts.map {
                it.asDomainModel()
            }
        }

    suspend fun refreshPosts(query: String) {
        withContext(Dispatchers.IO) {
            val response = remoteDataSource.getPostSearchByDate(query)
            if (response.isSuccessful) {
                response.body()?.let {
                    localDataSource.insertAll(it.hits.map { postRemoteDTO -> postRemoteDTO.asDatabaseEntity() })
                }
            }
        }
    }

    suspend fun deleteItem(id: Long) {
        withContext(Dispatchers.IO) {
            localDataSource.update(PostDatabaseEntityUpdate(id))
        }
    }


}