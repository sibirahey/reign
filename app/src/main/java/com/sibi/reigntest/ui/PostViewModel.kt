package com.sibi.reigntest.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibi.reigntest.data.repository.PostRepository
import com.sibi.reigntest.util.NetworkStatus
import kotlinx.coroutines.launch
import java.io.IOException

class PostViewModel @ViewModelInject constructor(
    private val postRepository: PostRepository
) :
    ViewModel() {

    private val networkResponseMutableLiveData = MutableLiveData<NetworkStatus>()
    val networkResponseLiveData: LiveData<NetworkStatus> = networkResponseMutableLiveData

    val posts = postRepository.posts

    fun getPosts(query: String) {
        viewModelScope.launch {
            try {
                postRepository.refreshPosts(query)
                networkResponseMutableLiveData.value = NetworkStatus.SUCCESSFUL
            } catch (networkError: IOException) {
                networkResponseMutableLiveData.value = NetworkStatus.FAILURE
            }
        }
    }

    fun deleteItem(id: Long){
        viewModelScope.launch {
            postRepository.deleteItem(id)
        }
    }

}