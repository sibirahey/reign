package com.sibi.reigntest.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibi.reigntest.data.entities.Post
import com.sibi.reigntest.data.remote.PostRemoteDataSource
import kotlinx.coroutines.launch

class PostViewModel @ViewModelInject constructor(
    private val postRemoteDataSource: PostRemoteDataSource
) :
    ViewModel() {

    private val postMutableLiveData = MutableLiveData<List<Post>>()
    val postLiveData: LiveData<List<Post>> = postMutableLiveData

    fun getPosts(query: String) {
        viewModelScope.launch {
            val response = postRemoteDataSource.getPostSearchByDate(query)
            if (response.isSuccessful) {
                response.body()?.let {
                    postMutableLiveData.value = it.hits
                }
            }
        }
    }

}