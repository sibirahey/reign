package com.sibi.reigntest.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PostDao {

    @Query("select * from PostDatabaseEntity where is_delete = 0 order by created_at desc")
    fun getPost(): LiveData<List<PostDatabaseEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(postDatabaseEntity: List<PostDatabaseEntity>)

    @Update(entity = PostDatabaseEntity::class)
    suspend fun update(post: PostDatabaseEntityUpdate)
}