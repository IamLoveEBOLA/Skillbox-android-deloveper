package com.skillbox.reddit.presentation.screens.subreddits

import androidx.fragment.app.viewModels
import com.skillbox.reddit.presentation.screens.AbstractSubredditsFragment
import com.skillbox.reddit.presentation.viewModels.subreddits.PopularSubredditsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularSubredditsFragment : AbstractSubredditsFragment() {
    
    override val request = "popular"
    
    override val viewModel: PopularSubredditsViewModel by viewModels()
    
}