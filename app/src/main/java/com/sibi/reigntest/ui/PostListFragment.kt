package com.sibi.reigntest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sibi.reigntest.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.post_list_fragment.*


@AndroidEntryPoint
class PostListFragment : Fragment() {

    companion object {
        fun newInstance() = PostListFragment()
    }

    private val viewModel by viewModels<PostViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.post_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.postLiveData.observe(viewLifecycleOwner) { list ->
            list?.let {
                message.text = it.size.toString()
            }
        }
        viewModel.getPosts()
    }

}