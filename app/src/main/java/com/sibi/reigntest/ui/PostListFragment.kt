package com.sibi.reigntest.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sibi.reigntest.R
import com.sibi.reigntest.util.NetworkStatus
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
        swiperefresh.setOnRefreshListener {
            getPosts()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getPosts()
    }

    private fun getPosts() {
        viewModel.getPosts(getString(R.string.query))
    }

    private fun setupRecyclerView(view: View) {
        adapter = PostAdapter(view.context) { post ->

            val url = when {
                post.story_url?.isNotEmpty() == true -> {
                    post.story_url
                }
                post.url?.isNotEmpty() == true -> {
                    post.url
                }
                else -> {
                    Toast.makeText(context, getString(R.string.not_valid_url), Toast.LENGTH_SHORT)
                        .show()
                    return@PostAdapter
                }
            }

            CustomTabsIntent.Builder().apply {
                setStartAnimations(requireActivity(), R.anim.slide_in_from_bottom, 0)
                setExitAnimations(requireActivity(), 0, R.anim.slide_out_to_bottom)
                setShowTitle(true)
                setToolbarColor(getColor(requireActivity(), R.color.colorPrimary))
                build().launchUrl(requireActivity(), Uri.parse(url))
            }

        }
        post_recycler_view.adapter = adapter
        post_recycler_view.layoutManager = LinearLayoutManager(view.context)
    }

    private fun setupObservers() {
        viewModel.posts.observe(viewLifecycleOwner) { list ->
            list?.let {
                adapter.submitList(it)
            }
        }
        viewModel.networkResponseLiveData.observe(viewLifecycleOwner) { status ->
            swiperefresh.isRefreshing = false
            if (status == NetworkStatus.FAILURE) {
                Toast.makeText(context, getString(R.string.network_error), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    companion object {
        fun newInstance() = PostListFragment()
    }

}