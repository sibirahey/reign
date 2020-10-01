package com.sibi.reigntest.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostDao {

    @Query("select * from PostDatabaseEntity order by created_at desc")
    fun getPost(): LiveData<List<PostDatabaseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(postDatabaseEntity: List<PostDatabaseEntity>)
}