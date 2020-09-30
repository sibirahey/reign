package com.sibi.reigntest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sibi.reigntest.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.post_list_fragment.*


@AndroidEntryPoint
class PostListFragment : Fragment() {

    private lateinit var adapter: PostAdapter
    private val viewModel by viewModels<PostViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.post_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(view)
        setupObservers()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getPosts(getString(R.string.query))
    }

    private fun setupRecyclerView(view: View) {
        adapter = PostAdapter(view.context)
        post_recycler_view.adapter = adapter
        post_recycler_view.layoutManager = LinearLayoutManager(view.context)
    }

    private fun setupObservers() {
        viewModel.postLiveData.observe(viewLifecycleOwner) { list ->
            list?.let {
                adapter.submitList(it)
            }
        }
    }

    companion object {
        fun newInstance() = PostListFragment()
    }

}