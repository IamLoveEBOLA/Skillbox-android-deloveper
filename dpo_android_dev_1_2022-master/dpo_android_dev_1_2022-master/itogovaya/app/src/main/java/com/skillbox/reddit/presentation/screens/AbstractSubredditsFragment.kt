package com.skillbox.reddit.presentation.screens

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.skillbox.reddit.R
import com.skillbox.reddit.databinding.FragmentSubredditsGenericBinding
import com.skillbox.reddit.domain.ApiResult
import com.skillbox.reddit.domain.model.SubredditEntity
import com.skillbox.reddit.presentation.UiText
import com.skillbox.reddit.presentation.adapters.MarginItemDecoration
import com.skillbox.reddit.presentation.adapters.PageLoadStateAdapter
import com.skillbox.reddit.presentation.adapters.SubredditsPageAdapter
import com.skillbox.reddit.presentation.viewModels.subreddits.AbstractSubredditsViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class AbstractSubredditsFragment : CommonAbstractFragment() {
    
    protected abstract val request: String
    protected abstract val viewModel: AbstractSubredditsViewModel
    private var _binding: FragmentSubredditsGenericBinding? = null
    private val binding get() = _binding!!
    private val subredditsPageAdapter = SubredditsPageAdapter(
        onShareClick = { onShareClick(it) },
        onItemClick = { onSubredditClick(it) },
        onSubscribeClick = { name, isSubscribed, position ->
            viewModel.subscribeUnsubscribe(
                name,
                isSubscribed,
                position
            )
        }
    )
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSubredditsGenericBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setUpPageAdapter()
        setupLoadStates()
        observePagerFlow()
        observeSubscribeResult()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    
    fun setUpPageAdapter() {
        binding.recyclerView.apply {
            adapter = subredditsPageAdapter.withLoadStateFooter(PageLoadStateAdapter())
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimensionPixelSize(
                        R.dimen.recyclerview_dimen
                    )
                )
            )
        }
    }
    
    fun setupLoadStates() {
        binding.swipeRefresh.setOnRefreshListener {
            subredditsPageAdapter.refresh()
        }
        subredditsPageAdapter.loadStateFlow.onEach {
            binding.swipeRefresh.isRefreshing = it.refresh == LoadState.Loading
            listOf(it.append, it.prepend, it.refresh).forEach { loadState ->
                if (loadState is LoadState.Error) {
                    showToast(
                        loadState.error.localizedMessage
                            ?: UiText.ResourceString(R.string.loading_error)
                                .asString(requireContext())
                    )
                }
            }
            if (it.source.refresh is LoadState.NotLoading && it.append.endOfPaginationReached && subredditsPageAdapter.itemCount < 1) {
                binding.notFoundItem.root.visibility = View.VISIBLE
                binding.notFoundItem.backButton.setOnClickListener {
                    findNavController().popBackStack()
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
    
    fun observePagerFlow() {
        viewModel.getPagedSubreddits(request).onEach {
            subredditsPageAdapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
    
    fun observeSubscribeResult() {
        lifecycleScope.launchWhenStarted {
            viewModel.subscribeChannel.collect { result ->
                if (result is ApiResult.Error) {
                    showToast(
                        UiText.ResourceString(R.string.something_went_wrong)
                            .asString(requireContext())
                    )
                } else {
                    subredditsPageAdapter.updateElement(result)
                }
            }
        }
    }
    
    fun onShareClick(url: String) {
        val intent = Intent(Intent.ACTION_SEND).also {
            it.putExtra(Intent.EXTRA_TEXT, url)
            it.type = "text/plain"
        }
        try {
            requireContext().startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            showToast(e.message)
        }
    }
    
    fun onSubredditClick(subreddit: SubredditEntity) {
        viewModel.saveCurrentSubreddit(subreddit)
        findNavController().navigate(
            R.id.from_subreddits_to_posts,
            Bundle().also { it.putString("subredditName", subreddit.displayName) }
        )
    }
}