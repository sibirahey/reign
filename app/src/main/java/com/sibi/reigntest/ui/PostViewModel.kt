package com.sibi.reigntest.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibi.reigntest.data.repository.PostRepository
import kotlinx.coroutines.launch
import java.io.IOException

class PostViewModel @ViewModelInject constructor(
    private val postRepository: PostRepository
) :
    ViewModel() {

    private val networkResponseMutableLiveData = MutableLiveData<Boolean>()
    val networkResponseLiveData: LiveData<Boolean> = networkResponseMutableLiveData

    val posts = postRepository.posts

    fun getPosts(query: String) {
        viewModelScope.launch {
            try {
                postRepository.refreshPosts(query)
                networkResponseMutableLiveData.value = true
            } catch (networkError: IOException) {
                networkResponseMutableLiveData.value = false
            }
        }
    }

}