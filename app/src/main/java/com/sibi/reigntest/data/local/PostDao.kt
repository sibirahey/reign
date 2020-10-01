package com.sibi.reigntest.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sibi.reigntest.data.entities.Post

@Dao
interface PostDao {

    @Query("select * from post order by created_at desc")
    fun getPost(): LiveData<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<Post>)
}